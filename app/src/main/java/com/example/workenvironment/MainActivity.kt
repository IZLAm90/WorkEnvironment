package com.example.workenvironment

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.work.*
import com.example.workenvironment.datastore.ReposUserData
import com.example.workenvironment.navgrave.SetupNavGraph
import com.example.workenvironment.service.LocationCheckWorker
import com.example.workenvironment.service.MediaService
import com.example.workenvironment.ui.theme.WorkEnvironmentTheme
import com.example.workenvironment.utils.PLAY
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import kotlin.math.log

private val PREFERENCES_NAME_USER =  "sample_datastore_prefs"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var mediaServiceConnection: MediaServiceConnection
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private val Context.prefsDataStore by preferencesDataStore(name = PREFERENCES_NAME_USER)
    var userRepo : ReposUserData ?=null
    inner class MediaServiceConnection(private val mediaService: MediaService) : ServiceConnection {
        var isBound = false

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isBound = true
            mediaService.setMediaTitle("")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }
    lateinit var navController:NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaServiceConnection = MediaServiceConnection(MediaService())
        val intent = Intent(this, MediaService::class.java)
        bindService(intent, mediaServiceConnection, Context.BIND_AUTO_CREATE)
        Log.d("media", "onCreate: activtiy ")
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWorkRequest =
            PeriodicWorkRequest.Builder(
                LocationCheckWorker::class.java,
                12, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "location_check_worker",
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWorkRequest)
        setContent {
            WorkEnvironmentTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray
                ) {
                    Scaffold(topBar = { TopAppBar(backgroundColor = MaterialTheme.colors.primary,
                        title = { Text(stringResource(id = R.string.app_name)) }
                    )}) {
                        val scope= rememberCoroutineScope()
                        navController= rememberNavController()
                        userRepo = ReposUserData(prefsDataStore)

                        val intentPlay = Intent(this, MediaService::class.java).apply {
                            action = PLAY
                            putExtra("mediaTitle", "mediaTitle")
                            putExtra("mediaUrl", "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3")
                        }
                        startService(intentPlay)
                        Log.d("media", "onCreate: starttopplay ")
                        SetupNavGraph(navController, userRepo!!,scope)
//                        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(navController.context)
//                        getLocation(this,mFusedLocationClient)
                    }


                }
            }
        }
    }
}
fun getLocation(context: Activity,mFusedLocationClient :FusedLocationProviderClient){
    if (ActivityCompat.checkSelfPermission(context,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(context,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(context, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
        return
    }
    Log.d("islam", "getLocation: ")
    val location = mFusedLocationClient.lastLocation
    Log.d("islam", "getLocation: ${location.isSuccessful}")
    location.addOnSuccessListener {
        if (it !=null){
            Log.d("islam", "getLocation: ${it.longitude}")
            Log.d("islam", "getLocation: ${it.latitude}")
        }
    }.addOnFailureListener {
        Log.d("islam", "getLocation fal: ${it.message}")

    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    lateinit var navController:NavHostController
//    WorkEnvironmentTheme {
//        navController= rememberNavController()
//        SetupNavGraph(navController)    }
}
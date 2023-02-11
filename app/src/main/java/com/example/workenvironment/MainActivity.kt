package com.example.workenvironment

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.workenvironment.datastore.ReposUserData
import com.example.workenvironment.navgrave.SetupNavGraph
import com.example.workenvironment.ui.theme.WorkEnvironmentTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

private val PREFERENCES_NAME_USER =  "sample_datastore_prefs"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private val Context.prefsDataStore by preferencesDataStore(name = PREFERENCES_NAME_USER)
    var userRepo : ReposUserData ?=null

    lateinit var navController:NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkEnvironmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray
                ) {
                    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(navController.context)
                    val scope= rememberCoroutineScope()
                    navController= rememberNavController()
                    userRepo = ReposUserData(prefsDataStore)
                    SetupNavGraph(navController, userRepo!!,scope)
                    getLocation(this,mFusedLocationClient)
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
    val location = mFusedLocationClient.lastLocation
    location.addOnSuccessListener {
        if (it !=null){
            Log.d("islam", "getLocation: ${it.longitude}")
            Log.d("islam", "getLocation: ${it.latitude}")
        }
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
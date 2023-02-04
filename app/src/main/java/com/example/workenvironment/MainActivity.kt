package com.example.workenvironment

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.workenvironment.datastore.ReposUserData
import com.example.workenvironment.navgrave.SetupNavGraph
import com.example.workenvironment.ui.theme.WorkEnvironmentTheme
import dagger.hilt.android.AndroidEntryPoint

private val PREFERENCES_NAME_USER =  "sample_datastore_prefs"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                    val scope= rememberCoroutineScope()
                    navController= rememberNavController()
                    userRepo = ReposUserData(prefsDataStore)
                    SetupNavGraph(navController, userRepo!!,scope)
                }
            }
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
package com.example.workenvironment.navgrave

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.workenvironment.datastore.ReposUserData
import com.example.workenvironment.presentation.homeuser.HomeUSer
import com.example.workenvironment.presentation.login.Login
import kotlinx.coroutines.CoroutineScope

fun NavGraphBuilder.LoginHostGraph (navController : NavHostController,userRepo: ReposUserData,scope: CoroutineScope){
    navigation(startDestination = Screen.Login.route, route = LOGIN_ROUTE){
        composable(route = Screen.Login.route) {
            Login(navController = navController, userRepo = userRepo,scope)
        }
        composable(route = Screen.HomeUser.route){
            HomeUSer(navController= navController)
            Log.d("islam", "LoginHostGraph: ${it.arguments?.get("username")}")
        }
    }
}
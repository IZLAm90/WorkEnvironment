package com.example.workenvironment.navgrave

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.workenvironment.datastore.ReposUserData
import kotlinx.coroutines.CoroutineScope

@Composable
fun SetupNavGraph(navController: NavHostController,userRepo: ReposUserData, scope:CoroutineScope){
    NavHost(
        navController = navController,
        startDestination = LOGIN_ROUTE,
        route = ROOT_GRAPH_ROUTE
    ) {
        LoginHostGraph(navController = navController,userRepo,scope)
    }
}
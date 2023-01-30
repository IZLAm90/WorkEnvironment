package com.example.workenvironment.navgrave

const val LOGIN_ROUTE = "Login"
const val HOME_USER_ROUTE = "HomeUser"
const val HOME_ADMIN_ROUTE = "HomeAdmin"
const val ROOT_GRAPH_ROUTE = "root"

sealed class Screen(val route :String){
    object Login :Screen(route = "login" )
    object HomeUser :Screen(route = "homeuser?username={username}" ){
        fun passUSerName(name:String):String{return "homeuser?username=$name"}
    }
    object HomeAdmin :Screen(route = "homeadmin" )

}
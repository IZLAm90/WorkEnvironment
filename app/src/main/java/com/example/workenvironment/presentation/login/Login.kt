package com.example.workenvironment.presentation.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.workenvironment.R
import com.example.workenvironment.model.UserData
import com.example.workenvironment.datastore.ReposUserData
import com.example.workenvironment.navgrave.Screen
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Login(navController: NavController,userRepo:ReposUserData,scope:CoroutineScope,loginViewModel: LoginViewModel = hiltViewModel()) {
   scope.launch {
       userRepo.getUserData().collect { islam->
           withContext(Dispatchers.Main){
               if (islam.password.isNotEmpty()&&islam.userName.isNotEmpty())
                   navController.navigate(route =Screen.HomeAdmin.route)
               else
                   Log.d("islam", "Login: ${islam}")
           }
       }
   }


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//        CircularProgressIndicator(progress = 1F,color =Color.Blue, strokeWidth = 3.dp, modifier = Modifier.fillMaxSize())
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            modifier = Modifier.padding(40.dp,40.dp,40.dp,10.dp),
            painter = painterResource(id = R.drawable.logoo),
            contentDescription = "islam"
        )
        Spacer(modifier = Modifier.height(50.dp))
        var userName by remember { mutableStateOf("") }
        OutlinedTextField(value =userName , onValueChange = {userName=it}, modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 0.dp, 40.dp, 0.dp),
            placeholder = { Text(text = "USerName")}, label = { Text(text = "UserName")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        var password by  rememberSaveable { mutableStateOf("") }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            var passeordVisibility by remember { mutableStateOf(false) }
            val icon = if (passeordVisibility)
                painterResource(id = com.example.workenvironment.R.drawable.ic_visibilit)
            else
                painterResource(id =com.example.workenvironment.R.drawable.ic_visibility_off)
            OutlinedTextField(value = password, onValueChange ={password=it} , modifier = Modifier.fillMaxWidth(), placeholder = { Text(text = "password")},
                label = { Text(text ="Password")},
                trailingIcon = { IconButton(onClick = { passeordVisibility = !passeordVisibility}) {
                    Icon(painter = icon, contentDescription = "icon for password")
                }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passeordVisibility) VisualTransformation.None else PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Bottom(navController,userRepo,scope, userName =userName, password =password,loginViewModel  )
    }

}


@Composable
fun Bottom(navController: NavController,userRepo: ReposUserData,scope: CoroutineScope,userName:String,password:String,loginViewModel: LoginViewModel= hiltViewModel()){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(40.dp, 0.dp, 40.dp, 0.dp)
        .clip(RoundedCornerShape(20.dp)),
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Button(onClick = {
                Log.d("islam", "Bottom: ${userName}  ok password ${password} ")
                if (userName.equals("islam")&&password.equals("islam")){
                    navController.navigate(route =Screen.HomeAdmin.route)
                }else
                scope.launch {
//                    withContext(Dispatchers.IO){
//                        val responce= loginViewModel.userLogin(UserData(userName = userName, password))
//                        withContext(Dispatchers.Main){
//                            if (responce.success){
//                                Log.d("islam", "Bottom: okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")
//                            }else{
//                                Log.d("islam", "Bottom: nooooooooooooo${responce.message} ")
//                            }
//                        }
//                    }

                    userRepo.setUserData(UserData(userName = userName, password = password))
                }
//                navController.navigate(route = Screen.HomeUser.passUSerName(userName))

                             },
                shape =RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Blue) ){
                Text(
                    fontWeight = FontWeight.Bold ,
                    color = Color.White, text = "Login" )
            }

        }

    }
}
//JANUARY69
//
@Composable
@Preview(showBackground = true)
fun Preview() {
//    Login(navController = rememberNavController())
}
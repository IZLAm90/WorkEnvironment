package com.example.workenvironment.model.responce


data class LoginResponse(
    val success: Boolean,
    val message:String
)
data class ForGetPassActive(val serial:Int?=null,val mobile:String?=null,val activationCode:String?=null,val activated:Boolean)

data class ForgetSucces(val successFlag:Int?=null)
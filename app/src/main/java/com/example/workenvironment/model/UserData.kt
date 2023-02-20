package com.example.workenvironment.model

import androidx.compose.ui.graphics.painter.Painter

data class UserData(val userName:String,val password:String)
data class User(val id: Int,
                val name: String,
                val description: String,
                val imageRes: Painter
)

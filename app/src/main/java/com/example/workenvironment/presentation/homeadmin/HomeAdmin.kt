package com.example.workenvironment.presentation.homeadmin

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeAdmin(){
    val list = listOf(
        "A", "B", "C", "D"
    ) + ((0..100).map { it.toString() })
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween) {
            LazyRow(modifier = Modifier.fillMaxWidth()){
                items(items=list, itemContent = {item ->
                    Spacer(modifier = Modifier.width(2.dp))
                    Box(modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Red)){
                        Text(text = item, style = TextStyle(fontSize = 20.sp), modifier = Modifier.align(alignment = Alignment.Center))
                    }

                })
            }
        }


        LazyColumn(modifier = Modifier.fillMaxHeight().padding(start = 10.dp, end = 10.dp, top = 10.dp)){
            items(items = list, itemContent = {item ->
                Spacer(modifier = Modifier.height(5.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .height(70.dp)
                    .clip(CircleShape)
                    , horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(imageVector = Icons.Default.Home , contentDescription = "user",
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray)
                    )
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = item, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 10.dp))
                        Text(text = item, style = TextStyle(fontSize = 12.sp), modifier = Modifier.padding(start = 15.dp))
                        Text(text = item, style = TextStyle(fontSize = 12.sp), modifier = Modifier.padding(start = 15.dp))

                    }

                }


            })
        }
    }



}

fun USsersItem(){


}
@Preview (showBackground = true)
@Composable
fun PreviewHomeAdmin(){
    HomeAdmin()
}




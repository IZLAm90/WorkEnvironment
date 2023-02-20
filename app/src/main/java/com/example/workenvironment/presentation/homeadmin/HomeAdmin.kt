package com.example.workenvironment.presentation.homeadmin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workenvironment.model.User

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeAdmin(){
    val list = listOf(
        "A", "B", "C", "D"
    ) + ((0..100).map { it.toString() })
    val Users = listOf(User(0,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.logoo)),
        User(1,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.flower1)),
        User(2,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.flower2)),
        User(3,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.flower3)),
        User(4,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.flower4)),
        User(5,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.logoo)),
        User(6,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.flower1)),
        User(7,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.flower2)),
        User(8,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.flower3)),
        User(9,"islam","assssssssssssssssssssssssssssssdas",painterResource(id = com.example.workenvironment.R.drawable.flower4)),
        )
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


        LazyColumn(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(5.dp)){
            items(items =Users , itemContent = {item ->
                Row(modifier = Modifier
                    .wrapContentHeight()
                    .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    PlantCard(item.name,item.description,item.imageRes)
//                    Spacer(modifier = Modifier.width(10.dp))
//                    Image(imageVector = Icons.Default.Home , contentDescription = "user",
//                        modifier = Modifier
//                            .height(50.dp)
//                            .width(50.dp)
//                            .clip(RoundedCornerShape(10.dp))
//                            .background(Color.LightGray)
//                    )
//                    Column(modifier = Modifier.fillMaxWidth()) {
//                        Text(text = item, style = TextStyle(fontSize = 15.sp), modifier = Modifier.padding(start = 10.dp))
//                        Text(text = item, style = TextStyle(fontSize = 12.sp), modifier = Modifier.padding(start = 15.dp))
//                        Text(text = item, style = TextStyle(fontSize = 12.sp), modifier = Modifier.padding(start = 15.dp))
//
//                    }

                }


            })
        }
    }
}

@Composable
fun PlantCard(name: String, description: String, image: Painter) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter =  image,
                contentDescription = null,
                modifier = Modifier.size(130.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}
@Preview (showBackground = true)
@Composable
fun PreviewHomeAdmin(){
    HomeAdmin()
}




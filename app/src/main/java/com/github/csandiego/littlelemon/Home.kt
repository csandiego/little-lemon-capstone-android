package com.github.csandiego.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController? = null) {
    val context = LocalContext.current
    val menuItems by MenuDatabase.getInstance(context).menuItemDao().getAll().observeAsState()
    Column(Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(50.dp)
                    .clickable {
                        navController?.navigate(Profile.route)
                    }
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57))
                .padding(12.dp)
        ) {
            Text(text = "Little Lemon", color = Color(0xFFF4CE14), fontSize = 48.sp)
            Row(Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1.0f)) {
                    Text(text = "Chicago", color = Color.White, fontSize = 24.sp)
                    Text(
                        text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                        color = Color.White
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "Enter search phrase") },
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
            )
        }
        MenuItems(items = menuItems ?: emptyList())
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}
package com.github.csandiego.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController? = null) {
    val context = LocalContext.current
    var searchPhrase by rememberSaveable {
        mutableStateOf("")
    }
    var category by rememberSaveable {
        mutableStateOf("")
    }
    val dao = MenuDatabase.getInstance(context).menuItemDao()
    val menuItems by dao.getAll().observeAsState()
    val categories by dao.getCategories().observeAsState()
    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 24.dp)
                    .align(Alignment.Center)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .padding(24.dp)
                    .clickable {
                        navController?.navigate(Profile.route)
                    }
                    .align(Alignment.CenterEnd)
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
                    Text(
                        text = "Chicago",
                        color = Color.White,
                        fontSize = 24.sp,
                    )
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
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                placeholder = { Text(text = "Enter search phrase") },
                colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
            )
        }
        Text(
            text = "ORDER FOR DELIVERY!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp)
        )
        LazyRow() {
            items(categories ?: emptyList()) {
                Button(
                    onClick = { category = if (category == it) "" else it },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (category == it) Color(0xFF495E57) else Color.LightGray,
                        contentColor = if (category == it) Color.LightGray else Color(0xFF495E57)
                    ),
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(text = it.replaceFirstChar { it.uppercase() })
                }
            }
        }
        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 12.dp))
        MenuItems(items = menuItems?.filter {
            (searchPhrase.isBlank() || it.title.contains(
                searchPhrase,
                true
            )) && (category.isBlank() || it.category == category)
        } ?: emptyList())
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}
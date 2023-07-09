package com.github.csandiego.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Profile(
    navController: NavHostController? = null,
    sharedPreferences: SharedPreferences? = null
) {
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
        }
        Text(
            text = "Personal information",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 72.dp, bottom = 12.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .weight(1.0f)
        ) {
            Text(
                text = "First name", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
            Text(
                text = sharedPreferences?.getString("firstName", null) ?: "John",
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, SolidColor(Color.Gray), RoundedCornerShape(4.dp))
                    .padding(8.dp)
            )
            Text(
                text = "Last name", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
            Text(
                text = sharedPreferences?.getString("lastName", null) ?: "Doe",
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, SolidColor(Color.Gray), RoundedCornerShape(4.dp))
                    .padding(8.dp)

            )
            Text(
                text = "Email", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
            Text(
                text = sharedPreferences?.getString("email", null) ?: "some@where.com",
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, SolidColor(Color.Gray), RoundedCornerShape(4.dp))
                    .padding(8.dp)
            )
        }
        Button(
            onClick = {
                sharedPreferences?.edit()?.clear()?.apply()
                navController?.navigate(Onboarding.route)
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4CE14),
                contentColor = Color(0xFF000000)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 24.dp)
        ) {
            Text(text = "Log out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile()
}
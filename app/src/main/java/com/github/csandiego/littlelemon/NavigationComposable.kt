package com.github.csandiego.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, sharedPreferences: SharedPreferences) {
    NavHost(
        navController = navController,
        startDestination = if (sharedPreferences.getString("firstName", null)
                .isNullOrBlank()
        ) Onboarding.route else Home.route
    ) {
        composable(Home.route) {
            Home(navController)
        }
        composable(Profile.route) {
            Profile(navController = navController, sharedPreferences = sharedPreferences)
        }
        composable(Onboarding.route) {
            com.github.csandiego.littlelemon.composables.Onboarding(
                navController = navController,
                sharedPreferences = sharedPreferences
            )
        }
    }
}
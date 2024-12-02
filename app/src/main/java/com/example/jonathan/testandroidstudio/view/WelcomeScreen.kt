package com.example.jonathan.testandroidstudio.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column {
        Text(
            text = "Welcome to TestAndroidStudio"
        )

        Button(onClick = { navController.navigate("next") }) {
            Text("Go to next screen")
        }
    }
}

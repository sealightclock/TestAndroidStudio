package com.example.jonathan.testandroidstudio.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jonathan.testandroidstudio.presentation.viewmodel.NextViewModel
import com.example.jonathan.testandroidstudio.presentation.viewmodel.WelcomeViewModel

@Composable
fun AppNavigation(welcomeViewModel: WelcomeViewModel, nextViewModel: NextViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController, welcomeViewModel) }
        composable("next") { NextScreen(navController, nextViewModel) }
    }
}

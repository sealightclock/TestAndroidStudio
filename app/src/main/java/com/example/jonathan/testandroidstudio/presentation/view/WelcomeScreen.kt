package com.example.jonathan.testandroidstudio.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jonathan.testandroidstudio.presentation.viewmodel.WelcomeViewModel

@Composable
fun WelcomeScreen(navController: NavHostController, viewModel: WelcomeViewModel) {
    val counterState = viewModel.counter.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            text = "Welcome to TestAndroidStudio - Welcome Screen"
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Counter to be updated by a button"

        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Counter: ${counterState.value}"
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = { viewModel.incrementCounterByUi() })
        {
            Text(
                text = "Increment counter"
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = { viewModel.fetchCounterFromRemoteServer() })
        {
            Text(
                text = "Fetch counter from remote server"
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = { navController.navigate("next") })
        {
            Text(
                text = "Go to next screen"
            )
        }
    }
}

package com.example.jonathan.testandroidstudio.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jonathan.testandroidstudio.stringFromJNI

@Composable
fun HelloJniScreen(navController: NavHostController) {
    var text by remember { mutableStateOf("Tap to see JNI message") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { text = stringFromJNI() }) {
            Text(text = "Call JNI")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = text)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("welcome") })
        {
            Text(
                text = "Go to welcome screen"
            )
        }
    }
}



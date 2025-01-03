package com.example.jonathan.testandroidstudio.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jonathan.testandroidstudio.presentation.viewmodel.NextViewModel

@Composable
fun NextScreen(navController: NavHostController, nextViewModel: NextViewModel) {
    val noteState = nextViewModel.note.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            text = "Welcome to TestAndroidStudio - NextScreen"
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Enter your note:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            value = noteState.value,
            onValueChange = { nextViewModel.updateNote(it) },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = { navController.navigate("welcome") })
        {
            Text(
                text = "Go to welcome screen"
            )
        }
    }
}

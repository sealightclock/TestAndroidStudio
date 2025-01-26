package com.example.jonathan.testandroidstudio.presentation.view

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

// This must be a public domain URL to avoid CORS issues:
private const val PUBLIC_URL_STR = "https://www.example.com"

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        AndroidView(
            factory = {
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    webViewClient = WebViewClient() // Handles navigation inside the WebView
                    webChromeClient = WebChromeClient() // Handles events like loading progress
                    loadUrl(PUBLIC_URL_STR)
                }
            }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = { navController.navigate("handwriting") })
        {
            Text(
                text = "Go to handwriting screen"
            )
        }
    }
}



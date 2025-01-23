package com.example.jonathan.testandroidstudio.presentation.view

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

// This must be a public domain URL to avoid CORS issues:
private const val PUBLIC_URL_STR = "https://www.example.com"

@Composable
fun WebViewScreen() {
    val context = LocalContext.current

    AndroidView(
        factory = {
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                webViewClient = WebViewClient() // Handles navigation inside the WebView
                webChromeClient = WebChromeClient() // Handles events like loading progress
                loadUrl(PUBLIC_URL_STR)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}



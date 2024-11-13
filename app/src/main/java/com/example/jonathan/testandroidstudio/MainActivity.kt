package com.example.jonathan.testandroidstudio

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jonathan.testandroidstudio.ui.theme.TestAndroidStudioTheme
import com.example.jonathan.utillib.ExternalInterfaceImpl
import com.example.jonathan.utillib.InternalInterfaceImpl


private const val TAG = "TAS: MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestAndroidStudioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        // Test internal interface

        // Same module, internal interface -> class:
        val dog = Dog()
        dog.run()

        // Another module, external interface -> class there:
        val externalInterfaceImpl = ExternalInterfaceImpl()
        externalInterfaceImpl.doSomething()

        // Another module, internal interface -> class there:
        val interInterfaceImpl = InternalInterfaceImpl()
        interInterfaceImpl.doSomething()

        // Another module, external interface -> class here:
        val externalInterfaceImpl2 = ExternalInterfaceImpl2()
        externalInterfaceImpl2.doSomething()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAndroidStudioTheme {
        Greeting("Android")
    }
}
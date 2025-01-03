package com.example.jonathan.testandroidstudio

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.jonathan.testandroidstudio.ui.theme.TestAndroidStudioTheme
import com.example.jonathan.testandroidstudio.presentation.view.AppNavigation
import com.example.jonathan.testandroidstudio.presentation.viewmodel.WelcomeViewModel
import com.example.jonathan.testandroidstudio.testcase.internalinterface.Dog
import com.example.jonathan.utillib.ExternalInterfaceImpl
import com.example.jonathan.utillib.InternalInterfaceImpl


private const val TAG = "TAS: MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)

        // This insures that the ViewModel is created only once, and rotation works:
        val welcomeViewModel: WelcomeViewModel by viewModels()

        setContent {
            TestAndroidStudioTheme {
                // Navigation Compose with start screen:
                AppNavigation(welcomeViewModel)
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

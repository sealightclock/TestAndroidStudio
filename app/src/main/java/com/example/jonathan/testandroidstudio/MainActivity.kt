package com.example.jonathan.testandroidstudio

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.jonathan.testandroidstudio.data.localdb.AppDatabase
import com.example.jonathan.testandroidstudio.data.repository.KeyIntValueRepository
import com.example.jonathan.testandroidstudio.data.repository.KeyStringValueRepository
import com.example.jonathan.testandroidstudio.presentation.view.AppNavigation
import com.example.jonathan.testandroidstudio.presentation.viewmodel.NextViewModel
import com.example.jonathan.testandroidstudio.presentation.viewmodel.NextViewModelFactory
import com.example.jonathan.testandroidstudio.presentation.viewmodel.WelcomeViewModel
import com.example.jonathan.testandroidstudio.presentation.viewmodel.WelcomeViewModelFactory
import com.example.jonathan.testandroidstudio.testcase.internalinterface.Dog
import com.example.jonathan.testandroidstudio.testcase.internalinterface.ExternalInterfaceImpl2
import com.example.jonathan.testandroidstudio.ui.theme.TestAndroidStudioTheme
import com.example.jonathan.utillib.ExternalInterfaceImpl
import com.example.jonathan.utillib.InternalInterfaceImpl


private const val TAG = "TAS: MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)

        val keyIntValueRepository = KeyIntValueRepository(database.keyIntValueDao())
        val welcomeViewModel = ViewModelProvider(this, WelcomeViewModelFactory(keyIntValueRepository))[WelcomeViewModel::class.java]
        welcomeViewModel.fetchKeyIntValue("counter") // Fetch value on app launch

        val keyStringValueRepository = KeyStringValueRepository(database.keyStringValueDao())
        val nextViewModel = ViewModelProvider(this, NextViewModelFactory(keyStringValueRepository))[NextViewModel::class.java]
        nextViewModel.fetchKeyStringValue("note") // Fetch value on app launch

        setContent {
            TestAndroidStudioTheme {
                // Navigation Compose with start screen:
                AppNavigation(welcomeViewModel, nextViewModel)
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

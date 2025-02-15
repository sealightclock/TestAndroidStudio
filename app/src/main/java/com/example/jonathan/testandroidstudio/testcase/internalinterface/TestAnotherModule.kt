package com.example.jonathan.testandroidstudio.testcase.internalinterface

import android.util.Log
import com.example.jonathan.utillib.ExternalInterface

private const val TAG = "TAS: TestAnotherModule"

class ExternalInterfaceImpl2 : ExternalInterface {
    override fun doSomething() {
        Log.d(TAG, "ExternalInterfaceImpl2: doSomething")
    }
}

// This won't compile: InternalInterface is not visible:
/*class InternalInterfaceImpl2 : InternalInterface {
    override fun doSomething() {
        Log.d(TAG, "InternalInterfaceImpl2: doSomething")
    }
}*/

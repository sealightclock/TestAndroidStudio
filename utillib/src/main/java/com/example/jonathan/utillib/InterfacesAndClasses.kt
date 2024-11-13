package com.example.jonathan.utillib

import android.util.Log

private const val TAG = "TAS: UTILLIB"

interface ExternalInterface {
    fun doSomething()
}

class ExternalInterfaceImpl : ExternalInterface{
    override fun doSomething() {
        Log.d(TAG, "ExternalInterfaceImpl: doSomething")
    }
}

internal interface InternalInterface {
    fun doSomething()
}

class InternalInterfaceImpl : InternalInterface {
    override fun doSomething() {
        Log.d(TAG, "InternalInterfaceImpl: doSomething")
    }
}


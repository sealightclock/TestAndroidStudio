package com.example.jonathan.testandroidstudio

import android.util.Log

private const val TAG = "TAS: Dog"

class Dog : Animal {
    override fun run() {
        Log.d(TAG, "run")
    }
}
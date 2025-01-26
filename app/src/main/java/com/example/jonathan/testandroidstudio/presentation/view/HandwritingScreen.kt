package com.example.jonathan.testandroidstudio.presentation.view

import android.graphics.Bitmap
import android.graphics.Canvas as AndroidCanvas
import android.os.Environment
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.io.File
import java.io.FileOutputStream

@Composable
fun HandwritingScreen(navController: NavHostController) {
    val context = LocalContext.current
    val canvasWidth = 800
    val canvasHeight = 1200

    // Path to store the handwriting
    val path by remember { mutableStateOf(Path()) }
    // Bitmap for saving the canvas
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Canvas for drawing
        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.White)
                .fillMaxWidth()
                .border(
                    width = 2.dp, // Border width
                    color = Color.Black, // Border color
                    shape = RoundedCornerShape(8.dp) // Optional: Rounded corners
                )
        ) {
            Canvas(
                modifier = Modifier
                    .matchParentSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { startPoint ->
                                path.moveTo(startPoint.x, startPoint.y)
                            },
                            onDrag = { change, _ ->
                                path.lineTo(change.position.x, change.position.y)
                            }
                        )
                    }
            ) {
                drawPath(path, Color.Black, style = Stroke(width = 4f))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                bitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888).apply {
                    val androidCanvas = AndroidCanvas(this)
                    androidCanvas.drawColor(android.graphics.Color.WHITE) // White background
                    androidCanvas.drawPath(path.asAndroidPath(), android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        style = android.graphics.Paint.Style.STROKE
                        strokeWidth = 8f
                    })
                    saveBitmapToFile(this, "handwriting.png", context)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

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

fun saveBitmapToFile(bitmap: Bitmap, fileName: String, context: android.content.Context) {
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
    FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    }
}

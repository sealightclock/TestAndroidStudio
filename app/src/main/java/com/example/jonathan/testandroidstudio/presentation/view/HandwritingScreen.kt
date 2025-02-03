package com.example.jonathan.testandroidstudio.presentation.view

import android.graphics.Bitmap
import android.os.Environment
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.AndroidPaint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withSave
import androidx.navigation.NavHostController
import java.io.File
import java.io.FileOutputStream
import android.graphics.Canvas as AndroidCanvas

@Composable
fun HandwritingScreen(navController: NavHostController) {
    val context = LocalContext.current
    val canvasWidth = 800
    val canvasHeight = 1200

    // List to store points for drawing
    val points = remember { mutableStateListOf<Offset>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Drawing Canvas
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
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { offset ->
                                points.add(offset) // Start drawing
                            },
                            onDrag = { change, _ ->
                                points.add(change.position) // Add points to the list
                            }
                        )
                    }
            ) {
                // Create a path from the points and draw it
                val path = Path().apply {
                    if (points.isNotEmpty()) {
                        moveTo(points.first().x, points.first().y)
                        for (point in points) {
                            lineTo(point.x, point.y)
                        }
                    }
                }
                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(width = 4f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Save the path as a bitmap image
                val bitmap = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888)
                val canvas = AndroidCanvas(bitmap)
                val paint = AndroidPaint().apply {
                    //color = android.graphics.Color.BLACK
                    //style = AndroidPaint.Style.STROKE
                    strokeWidth = 8f
                }

                // Draw the points onto the bitmap
                canvas.withSave {
                    drawColor(android.graphics.Color.WHITE) // Set background color to white
                    val androidPath = android.graphics.Path().apply {
                        if (points.isNotEmpty()) {
                            moveTo(points.first().x, points.first().y)
                            for (point in points) {
                                lineTo(point.x, point.y)
                            }
                        }
                    }
                    drawPath(androidPath, paint.asFrameworkPaint())
                }

                // Save the bitmap to a file
                saveBitmapToFile(bitmap, "handwriting.png", context)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("hellojni") })
        {
            Text(
                text = "Go to hellojni screen"
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

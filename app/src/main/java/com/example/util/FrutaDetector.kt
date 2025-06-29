package com.example.util

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.json.JSONObject

class FrutaDetector(context: Context) {
    private val interpreter: Interpreter
    private val labels: List<String>

    init {
        val model = FileUtil.loadMappedFile(context, "modelo_frutas.tflite")
        interpreter = Interpreter(model)

        // CORREGIDO: Leer labels.json como texto
        val inputStream = context.assets.open("labels.json")
        val jsonStr = inputStream.bufferedReader().use { it.readText() }

        val jsonObj = JSONObject(jsonStr)
        labels = (0 until jsonObj.length()).map { jsonObj.getString(it.toString()) }
    }


    fun detectar(bitmap: Bitmap): String {
        val input = convertBitmapToInput(bitmap)
        val output = Array(1) { FloatArray(labels.size) }

        interpreter.run(input, output)

        val predictionIndex = output[0].withIndex().maxByOrNull { it.value }!!.index
        return labels[predictionIndex]
    }

    private fun convertBitmapToInput(bitmap: Bitmap): Array<Array<Array<FloatArray>>> {
        val resized = Bitmap.createScaledBitmap(bitmap, 100, 100, true)
        val input = Array(1) { Array(100) { Array(100) { FloatArray(3) } } }

        for (y in 0 until 100) {
            for (x in 0 until 100) {
                val pixel = resized.getPixel(x, y)
                input[0][y][x][0] = android.graphics.Color.red(pixel) / 255.0f
                input[0][y][x][1] = android.graphics.Color.green(pixel) / 255.0f
                input[0][y][x][2] = android.graphics.Color.blue(pixel) / 255.0f
            }
        }
        return input
    }
}
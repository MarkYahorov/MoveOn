package com.moveon.search.bytitle.presentation.recognizer

import android.graphics.Rect
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@OptIn(ExperimentalGetImage::class)
class TextRecognizitionAnalyzer(
    private val onTextRecognized: (Text.TextBlock?, imageWidth: Int, imageHeight: Int) -> Unit
) : ImageAnalysis.Analyzer {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    override fun analyze(image: ImageProxy) {
        scope.launch {
            val mediaImage = image.image ?: return@launch
            val inputImage = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)

            suspendCoroutine {
                textRecognizer.process(inputImage)
                    .addOnSuccessListener {
                        val biggestBlock =
                            it.textBlocks.maxByOrNull { getSquareOfRectangle(it.boundingBox) }
                        onTextRecognized.invoke(biggestBlock, inputImage.width, inputImage.height)
                    }
                    .addOnCompleteListener { image.close() }
            }
        }
    }

    private fun getSquareOfRectangle(rect: Rect?): Int {
        return rect?.let {
            (it.bottom - it.top) * (it.right - it.left)
        } ?: 0
    }
}


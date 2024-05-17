package com.moveon.search.bytitle.presentation.view

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.text.Text
import com.moveon.search.bytitle.presentation.data.ScreenState
import com.moveon.search.bytitle.presentation.recognizer.TextRecognizitionAnalyzer
import com.moveon.search.bytitle.presentation.viewmodel.SearchByTitleViewModel
import java.util.concurrent.Executors

@Composable
fun SearchByTitleView(viewModel: SearchByTitleViewModel = hiltViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (viewModel.screenState) {
            ScreenState.Scanner -> SearchByTitleScanner(viewModel = viewModel)
            ScreenState.List -> SearchByTitleListContent(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun SearchByTitleListContent(viewModel: SearchByTitleViewModel) {
    val permission = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val requirePermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) {
                Log.e("TAG23", "dsf")
                viewModel.screenState = ScreenState.Scanner
            }
        }
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            painter = painterResource(id = com.moveon.ui_core.R.drawable.search_bottom_icon),
            contentDescription = null,
            modifier = Modifier.clickable {
                if (permission.status.isGranted) {
                    Log.e("TAG23", "dsf")
                    viewModel.screenState = ScreenState.Scanner
                } else {
                    requirePermission.launch(Manifest.permission.CAMERA)
                }
            }
        )
    }
}

@Composable
private fun SearchByTitleScanner(viewModel: SearchByTitleViewModel) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val localConfig = LocalConfiguration.current
    var imageWidth by remember { mutableStateOf(0) }
    var imageHeight by remember { mutableStateOf(0) }
    val screenWidth by remember { mutableStateOf(localConfig.screenWidthDp) }
    val screenHeight = remember { mutableStateOf(localConfig.screenHeightDp) }
    var blocks by remember { mutableStateOf<Text.TextBlock?>(null) }
    Box(modifier = Modifier.fillMaxSize()) {
        CameraView(
            context = context,
            lifecycleOwner = lifecycleOwner,
            analyzer = TextRecognizitionAnalyzer(
                onTextRecognized = { block, width, height ->
                    blocks = block
                    viewModel.onTextRecognized(block?.text.orEmpty())
                    imageWidth = width
                    imageHeight = height
                }
            )
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            val scannerSize = (screenWidth - screenWidth / 3).dp
            drawRect(Color.Black.copy(alpha = 0.8f))
            drawRect(
                Color.Transparent,
                
            )
        }


        Text(
            text = viewModel.recognizedText,
            modifier = Modifier.align(Alignment.Center),
            color = androidx.compose.ui.graphics.Color.Green
        )

//        blocks?.boundingBox?.let {
//            Box(modifier = Modifier
//                .fillMaxSize()
//                .drawWithContent {
//                    val boundingBox = it.toComposeRect()
//                    val topLeft = adjustPoint(
//                        PointF(boundingBox.topLeft.x, boundingBox.topLeft.y),
//                        imageWidth,
//                        imageHeight,
//                        screenWidth.value,
//                        screenHeight.value
//                    )
//                    val size = adjustSize(
//                        boundingBox.size,
//                        imageWidth,
//                        imageHeight,
//                        screenWidth.value,
//                        screenHeight.value
//                    )
//
//                    drawRect(
//                        color = androidx.compose.ui.graphics.Color.Red,
//                        size = size,
//                        topLeft = Offset(topLeft.x, topLeft.y),
//                        style = Stroke(width = 10f)
//                    )
//                }
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.Gray.copy(alpha = 30f))
//                )
//            }
//        }
    }
}

@Composable
fun CameraView(
    context: Context,
    analyzer: ImageAnalysis.Analyzer,
    lifecycleOwner: LifecycleOwner
) {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    var preview by remember { mutableStateOf<Preview?>(null) }
    val executor = ContextCompat.getMainExecutor(context)
    val cameraProvider = cameraProviderFuture.get()
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            cameraProviderFuture.addListener({
                val imageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .setOutputImageRotationEnabled(true)
                    .build()
                    .apply {
                        setAnalyzer(cameraExecutor, analyzer)
                    }
                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    imageAnalysis,
                    preview
                )
            }, executor)
            preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }
            previewView
        }
    )
}

package com.moveon.ui_core

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ProgressIndicator() {
    val lottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.progress_indicator)
    )

    val localConfig = LocalConfiguration.current

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        LottieAnimation(
            composition = lottieComposition,
            isPlaying = true,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.padding(horizontal = (localConfig.screenWidthDp / 4).dp)
        )
    }
}
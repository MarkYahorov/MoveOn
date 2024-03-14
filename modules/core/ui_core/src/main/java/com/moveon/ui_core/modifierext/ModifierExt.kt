package com.moveon.ui_core.modifierext

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

fun Modifier.roundedShape8Dp() = clip(RoundedCornerShape(8.dp))

fun Modifier.roundedShape10Dp() = clip(RoundedCornerShape(10.dp))

fun Modifier.roundedShape12Dp() = clip(RoundedCornerShape(12.dp))

fun Modifier.roundedShape16Dp() = clip(RoundedCornerShape(16.dp))

fun Modifier.bottomPadding6Dp() = padding(bottom = 6.dp)

fun Modifier.bottomPadding12Dp() = padding(bottom = 12.dp)

fun Modifier.bottomPadding18Dp() = padding(bottom = 18.dp)

fun Modifier.topPadding4Dp() = padding(top = 4.dp)

fun Modifier.endPadding4Dp() = padding(end = 4.dp)

fun Modifier.endPadding6Dp() = padding(end = 6.dp)

fun Modifier.endPadding16Dp() = padding(end = 16.dp)

fun Modifier.verticalPadding4Dp() = padding(vertical = 4.dp)

fun Modifier.verticalPadding8Dp() = padding(vertical = 8.dp)

fun Modifier.horizontalPadding6Dp() = padding(horizontal = 6.dp)

fun Modifier.horizontalPadding12Dp() = padding(horizontal = 12.dp)
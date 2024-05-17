package com.moveon.ui_core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moveon.ui_core.modifierext.horizontalPadding12Dp
import com.moveon.ui_core.modifierext.roundedShape12Dp
import com.moveon.ui_core.modifierext.verticalPadding4Dp

@Composable
fun BoxWithText(text: String, onItemClicked: (() -> Unit)? = null) {

    val clickableModifier = if (onItemClicked != null) {
        Modifier.clickable { onItemClicked.invoke() }
    } else Modifier
    Box(
        modifier = Modifier
            .roundedShape12Dp()
            .background(
                Color.DarkGray.copy(alpha = 0.3f),
                RoundedCornerShape(12.dp)
            )
            .then(clickableModifier)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .verticalPadding4Dp()
                .horizontalPadding12Dp()
        )
    }
}
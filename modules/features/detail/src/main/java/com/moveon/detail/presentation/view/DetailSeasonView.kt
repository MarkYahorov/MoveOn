package com.moveon.detail.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moveon.detail.presentation.data.DetailSeasonPresentation
import com.moveon.ui_core.BoxWithText
import com.moveon.ui_core.R
import com.moveon.ui_core.modifierext.bottomPadding2Dp
import com.moveon.ui_core.modifierext.bottomPadding6Dp
import com.moveon.ui_core.modifierext.endPadding4Dp
import com.moveon.ui_core.modifierext.horizontalPadding12Dp
import com.moveon.ui_core.modifierext.roundedShape12Dp
import com.moveon.ui_core.modifierext.verticalPadding8Dp

@Composable
fun DetailSeasonView(seasonPresentation: DetailSeasonPresentation) {
    var isExpanded: Boolean by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier
                .roundedShape12Dp()
                .background(
                    Color.DarkGray.copy(alpha = 0.3f),
                    RoundedCornerShape(12.dp)
                )
                .clickable { isExpanded = !isExpanded }
                .horizontalPadding12Dp()
                .verticalPadding8Dp(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = seasonPresentation.title)
                Spacer(modifier = Modifier.bottomPadding2Dp())
                Text(text = seasonPresentation.years)
            }

            Spacer(modifier = Modifier.endPadding4Dp())

            Icon(
                painter = painterResource(
                    id = if (isExpanded) {
                        R.drawable.arrow_up
                    } else R.drawable.arrow_down
                ),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(10.dp),
            )
        }

        Spacer(modifier = Modifier.bottomPadding6Dp())

        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandHorizontally(),
            exit = fadeOut() + shrinkHorizontally(),
        ) {
            LazyRow {
                items(seasonPresentation.episodes) { episode ->
                    BoxWithText(text = episode)
                    if (episode != seasonPresentation.episodes.last()) {
                        Spacer(modifier = Modifier.endPadding4Dp())
                    }
                }
            }
        }
    }
}
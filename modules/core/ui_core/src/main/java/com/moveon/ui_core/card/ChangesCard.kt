package com.moveon.ui_core.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.moveon.ui_core.ImageLoadView
import com.moveon.ui_core.data.ChangesItemPresentation
import com.moveon.ui_core.modifierext.bottomPadding12Dp
import com.moveon.ui_core.modifierext.bottomPadding6Dp
import com.moveon.ui_core.modifierext.endPadding6Dp
import com.moveon.ui_core.modifierext.horizontalPadding12Dp
import com.moveon.ui_core.modifierext.roundedShape12Dp
import com.moveon.ui_core.modifierext.verticalPadding4Dp

@Composable
fun ChangesCard(
    item: ChangesItemPresentation,
    streamingLightThemeImage: String?,
    streamingDarkThemeImage: String?
) {
    var isExpanded: Boolean by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .roundedShape12Dp()
            .padding(8.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                var imageSize by remember { mutableStateOf(IntSize.Zero) }
                Text(
                    text = if (item.show.title == item.show.originalTitle) {
                        item.show.title
                    } else "${item.show.title} (${item.show.originalTitle})",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(end = with(LocalDensity.current) {
                            imageSize.width.toDp()
                        } + 8.dp)
                )

                ImageLoadView(
                    data = if (isSystemInDarkTheme()) {
                        streamingDarkThemeImage
                    } else streamingLightThemeImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(45.dp)
                        .align(Alignment.CenterEnd)
                        .onSizeChanged { imageSize = it }
                )
            }

            Spacer(modifier = Modifier.bottomPadding6Dp())

            item.show.year?.let {
                Text(text = item.show.year)

                Spacer(modifier = Modifier.bottomPadding12Dp())
            }

            AnimatedVisibility(visible = isExpanded) {
                Row {
                    item.show.seriesInfo.forEach {
                        Box(
                            modifier = Modifier
                                .roundedShape12Dp()
                                .background(
                                    Color.DarkGray.copy(alpha = 0.3f),
                                    RoundedCornerShape(12.dp)
                                )
                        ) {
                            Text(
                                text = it,
                                modifier = Modifier
                                    .verticalPadding4Dp()
                                    .horizontalPadding12Dp()
                            )
                        }
                        if (it != item.show.seriesInfo.last()) {
                            Spacer(modifier = Modifier.endPadding6Dp())
                        }
                    }
                }
            }

            Text(text = item.show.genres.joinToString(", ") { it.name })

            AnimatedVisibility(visible = isExpanded) {
                Spacer(modifier = Modifier.bottomPadding12Dp())

                item.show.overview?.let {
                    Text(text = it)
                }
            }
            val lastChangeOfFirstService = item.changes.first()

            Text(text = "Last change: ${lastChangeOfFirstService.time}")
        }

        Text(
            text = if (!isExpanded) {
                "show more"
            } else "hide",
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .align(Alignment.BottomEnd)
                .bottomPadding6Dp()
                .endPadding6Dp()
        )
    }
}
package com.moveon.search.byfilter.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.moveon.search.byfilter.presentation.data.SearchByFilterItemPresentation
import com.moveon.ui_core.ImageLoadView
import com.moveon.ui_core.modifierext.bottomPadding12Dp
import com.moveon.ui_core.modifierext.bottomPadding6Dp
import com.moveon.ui_core.modifierext.endPadding6Dp
import com.moveon.ui_core.modifierext.roundedShape12Dp

@Composable
fun ChangesCard(
    item: SearchByFilterItemPresentation,
    onCardClicked: (SearchByFilterItemPresentation) -> Unit
) {
    var isExpanded: Boolean by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .roundedShape12Dp()
            .padding(8.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
            .clickable { onCardClicked.invoke(item) }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                var imageSize by remember { mutableStateOf(IntSize.Zero) }
                Text(
                    text = item.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(end = with(LocalDensity.current) {
                            imageSize.width.toDp()
                        } + 8.dp)
                )
            }

            Spacer(modifier = Modifier.bottomPadding6Dp())

            item.year?.let {
                Text(text = item.year)

                Spacer(modifier = Modifier.bottomPadding12Dp())
            }

            Text(text = item.genres.joinToString(", ") { it.name })

            AnimatedVisibility(visible = isExpanded) {
                Spacer(modifier = Modifier.bottomPadding12Dp())

                item.overview?.let {
                    Text(text = it)
                }
            }
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
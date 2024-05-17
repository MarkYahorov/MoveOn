package com.moveon.ui_core.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.moveon.ui_core.R
import com.moveon.ui_core.data.DropDownItem
import com.moveon.ui_core.modifierext.endPadding4Dp
import com.moveon.ui_core.modifierext.horizontalPadding6Dp
import com.moveon.ui_core.modifierext.verticalPadding4Dp

@Composable
fun <T : DropDownItem> ViewWithDropDown(
    title: String,
    dropDownItems: List<T>,
    onItemClicked: (T) -> Unit
) {
    var isServiceDropDownMenuExpanded by remember { mutableStateOf(false) }
    var blockSize by remember { mutableStateOf(IntSize.Zero) }
    Surface(
        modifier = Modifier
            .border(
                1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
            .offset(x = 1.dp)
            .clickable {
                isServiceDropDownMenuExpanded = true
            }
            .onSizeChanged { blockSize = it }
    ) {
        Row(
            modifier = Modifier
                .horizontalPadding6Dp()
                .verticalPadding4Dp(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title)

            Spacer(modifier = Modifier.endPadding4Dp())

            Icon(
                painter = painterResource(
                    id = if (!isServiceDropDownMenuExpanded) {
                        R.drawable.arrow_down
                    } else R.drawable.arrow_up
                ),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(RoundedCornerShape(12.dp))
        ) {
            DropdownMenu(
                expanded = isServiceDropDownMenuExpanded,
                onDismissRequest = { isServiceDropDownMenuExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) {
                        blockSize.width.toDp()
                    })
                    .background(Color.White)
                    .height(120.dp),
                offset = DpOffset(0.dp, 8.dp),
            ) {
                dropDownItems.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.title) },
                        onClick = {
                            onItemClicked.invoke(it)
                            isServiceDropDownMenuExpanded = false
                        })
                }
            }
        }
    }
}
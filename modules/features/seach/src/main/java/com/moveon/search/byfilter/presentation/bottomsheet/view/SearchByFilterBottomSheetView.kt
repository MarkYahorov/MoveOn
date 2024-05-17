package com.moveon.search.byfilter.presentation.bottomsheet.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.moveon.search.byfilter.presentation.bottomsheet.container.SearchByFilterBottomSheetContainer
import com.moveon.search.byfilter.presentation.data.SearchByFilterType
import com.moveon.ui_core.dropdown.ViewWithDropDown
import com.moveon.ui_core.modifierext.bottomPadding12Dp
import com.moveon.ui_core.modifierext.bottomPadding6Dp
import com.moveon.ui_core.modifierext.endPadding6Dp
import com.moveon.ui_core.modifierext.horizontalPadding12Dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchByFilterBottomSheetView(
    container: SearchByFilterBottomSheetContainer,
    sheetState: SheetState
) {
    val windowInsets = WindowInsets.navigationBars
    ModalBottomSheet(
        onDismissRequest = {
            container.hideBottomSheet()
        },
        sheetState = sheetState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        windowInsets = windowInsets,
    ) {
        val scope = rememberCoroutineScope()
        Column(modifier = Modifier.horizontalPadding12Dp()) {
            Spacer(modifier = Modifier.bottomPadding6Dp())
            TextField(
                value = container.keyword,
                onValueChange = { container.onKeywordChanged(it) },
                placeholder = { Text(text = "Enter the keyword") },
                modifier = Modifier.fillMaxWidth()
            )

            Row {
                ViewWithDropDown(
                    title = container.selectedCountry?.title.orEmpty(),
                    dropDownItems = container.countriesList,
                    onItemClicked = { container.onCountryChanged(it) }
                )

                Spacer(modifier = Modifier.endPadding6Dp())

                ViewWithDropDown(
                    title = container.selectedService?.title.orEmpty(),
                    dropDownItems = container.services,
                    onItemClicked = { container.onServiceChanged(it) }
                )

                Spacer(modifier = Modifier.endPadding6Dp())

                ViewWithDropDown(
                    title = container.selectedType.title,
                    dropDownItems = SearchByFilterType.values().toList(),
                    onItemClicked = { container.onSelectedTypeChanged(it) }
                )
            }

            Spacer(modifier = Modifier.bottomPadding12Dp())

            Button(onClick = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    container.hideBottomSheet()
                }
                container.onBtnClicked()
            }) {
                Text("search")
            }
        }
    }
}
package com.moveon.changes.presentation.ui

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moveon.changes.presentation.data.ChangeTypePresentation
import com.moveon.changes.presentation.data.TargetTypePresentation
import com.moveon.changes.presentation.viewmodel.ChangesBottomSheetStateContainer
import com.moveon.ui_core.dropdown.ViewWithDropDown
import com.moveon.ui_core.modifierext.bottomPadding12Dp
import com.moveon.ui_core.modifierext.bottomPadding18Dp
import com.moveon.ui_core.modifierext.horizontalPadding12Dp
import com.moveon.ui_core.modifierext.verticalPadding8Dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangesFilterBottomSheet(viewModel: ChangesBottomSheetStateContainer, sheetState: SheetState) {
    val windowInsets = WindowInsets.navigationBars
    ModalBottomSheet(
        onDismissRequest = {
            viewModel.hideBottomSheet()
        },
        sheetState = sheetState,
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        windowInsets = windowInsets,
    ) {
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier.horizontalPadding12Dp()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ViewWithDropDown(
                    title = viewModel.selectedService?.title.orEmpty(),
                    dropDownItems = viewModel.services,
                    onItemClicked = { viewModel.onServiceChanged(it) }
                )

                ViewWithDropDown(
                    title = viewModel.selectedCountry?.title.orEmpty(),
                    dropDownItems = viewModel.countriesList,
                    onItemClicked = {
                        viewModel.onCountryChanged(it)
                    }
                )
            }

            Spacer(modifier = Modifier.bottomPadding18Dp())

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomPadding12Dp(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                ViewWithDropDown(
                    title = viewModel.targetType.name,
                    dropDownItems = TargetTypePresentation.values().toList(),
                    onItemClicked = { viewModel.onTargetTypeChanged(it) }
                )

                ViewWithDropDown(
                    title = viewModel.changeType.name,
                    dropDownItems = ChangeTypePresentation.values().toList(),
                    onItemClicked = {
                        viewModel.onChangeTypeChanges(it)
                    }
                )
            }

            Spacer(modifier = Modifier.bottomPadding18Dp())

            Button(
                onClick = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        viewModel.hideBottomSheet()
                    }
                    viewModel.onBottomSheetSaveBtnClicked()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .bottomPadding12Dp()
            ) {
                Text(
                    text = "Save",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .verticalPadding8Dp()
                )
            }
        }
    }
}
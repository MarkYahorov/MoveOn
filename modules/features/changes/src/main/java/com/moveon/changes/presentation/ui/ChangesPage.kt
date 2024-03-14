package com.moveon.changes.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.moveon.changes.presentation.viewmodel.ChangesViewModel
import com.moveon.ui_core.ProgressIndicator
import com.moveon.ui_core.R
import com.moveon.ui_core.card.ChangesCard
import com.moveon.ui_core.modifierext.bottomPadding18Dp
import com.moveon.ui_core.modifierext.endPadding16Dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangesPage(viewModel: ChangesViewModel = hiltViewModel()) {
    if (viewModel.bottomSheetContainer.countriesList.isEmpty()) {
        viewModel.getCountries()
    }
    val data = viewModel.changingFlow.collectAsLazyPagingItems()
    val sheetState = rememberModalBottomSheetState()
    val listScrollState = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {

        if (data.itemCount > 0) {
            LazyColumn(content = {
                items(data.itemCount) {
                    data[it]?.let {
                        ChangesCard(
                            item = it,
                            streamingDarkThemeImage = viewModel.bottomSheetContainer.selectedService?.images?.darkThemeImage,
                            streamingLightThemeImage = viewModel.bottomSheetContainer.selectedService?.images?.lightThemeImage
                        )
                    }
                }
            }, state = listScrollState)
        } else {
            if (viewModel.isCountriesErrorVisible) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(text = "Srry countries arn't loaded")

                    OutlinedButton(onClick = { viewModel.getCountries() }) {
                        Text(text = "reload")
                    }
                }
            } else if (data.loadState.refresh != LoadState.Loading) {
                BasicText(text = "EMPTY", modifier = Modifier.align(Alignment.Center))
            }
        }

        if (viewModel.isBottomSheetVisible) {
            ChangesFilterBottomSheet(
                viewModel = viewModel.bottomSheetContainer,
                sheetState = sheetState
            )
        }

        AnimatedVisibility(
            visible = !listScrollState.isScrollInProgress
                    && viewModel.bottomSheetContainer.isRequestFieldsAreCorrect(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .endPadding16Dp()
                .bottomPadding18Dp()
        ) {
            SmallFloatingActionButton(
                onClick = { viewModel.onOpenBottomSheetClicked() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_btn),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp)
                )
            }
        }
    }

    if (viewModel.isProgressVisible) {
        ProgressIndicator()
    }
}
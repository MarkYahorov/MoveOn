package com.moveon.search.byfilter.presentation.view

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
import com.moveon.core.navigation.BaseNavigationController
import com.moveon.search.byfilter.presentation.bottomsheet.view.SearchByFilterBottomSheetView
import com.moveon.search.byfilter.presentation.viewmodel.SearchByFilterViewModel
import com.moveon.ui_core.ProgressIndicator
import com.moveon.ui_core.R
import com.moveon.ui_core.modifierext.bottomPadding18Dp
import com.moveon.ui_core.modifierext.endPadding16Dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchByFilterView(
    viewModel: SearchByFilterViewModel = hiltViewModel(),
    baseNavigationController: BaseNavigationController
) {
    if (viewModel.searchByFilterBottomSheetContainer.countriesList.isEmpty()) {
        viewModel.fetchCountries()
    }
    val sheetState = rememberModalBottomSheetState()
    val listScrollState = rememberLazyListState()
    val data = viewModel.searchPaging.collectAsLazyPagingItems()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        if (data.itemCount != 0) {
            LazyColumn(content = {
                items(data.itemCount) {
                    data[it]?.let { item ->
                        ChangesCard(
                            item
                        ) { baseNavigationController.navigateToDetail(it.imdbId, it.tmdbId) }
                    }
                }
            }, state = listScrollState)
        } else {
            if (viewModel.isCountriesErrorVisible) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Srry countries arn't loaded")

                    OutlinedButton(onClick = { viewModel.fetchCountries() }) {
                        Text(text = "reload")
                    }
                }
            } else if (data.loadState.refresh != LoadState.Loading) {
                BasicText(text = "EMPTY", modifier = Modifier.align(Alignment.Center))
            }
        }

        if (viewModel.isSearchBottomSheetVisible) {
            SearchByFilterBottomSheetView(
                container = viewModel.searchByFilterBottomSheetContainer,
                sheetState = sheetState
            )
        }

        AnimatedVisibility(
            visible = !listScrollState.isScrollInProgress
                    && viewModel.searchByFilterBottomSheetContainer.isRequestFieldsAreCorrect(),
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

        if (viewModel.isProgressVisible) {
            ProgressIndicator()
        }
    }
}
package com.moveon.detail.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.moveon.detail.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(detailViewModel: DetailViewModel = hiltViewModel()) {
    Box(modifier = Modifier.fillMaxSize()) {

    }
}
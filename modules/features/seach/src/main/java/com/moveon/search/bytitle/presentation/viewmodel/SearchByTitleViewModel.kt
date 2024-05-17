package com.moveon.search.bytitle.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moveon.core.viewmodel.CoreViewModel
import com.moveon.search.bytitle.presentation.data.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val SPACER = "/n"

@HiltViewModel
class SearchByTitleViewModel @Inject constructor() : CoreViewModel() {

    var recognizedText: String by mutableStateOf("")

    var screenState: ScreenState by mutableStateOf(ScreenState.List)

    fun onTextRecognized(recognizedText: String) {
        this.recognizedText = recognizedText.replace(SPACER, " ")
    }
}
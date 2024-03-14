package com.moveon.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class CoreViewModel : ViewModel() {

    open fun onOpen() {}

    val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
}


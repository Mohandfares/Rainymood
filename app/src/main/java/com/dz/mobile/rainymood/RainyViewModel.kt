package com.dz.mobile.rainymood

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RainyViewModel : ViewModel() {

    val playState = MutableStateFlow(false)

    fun playStateChange() {
        playState.value = !playState.value
    }
}
package me.i32xlevel.dzonetask.viewmodel

import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    init {
        updateUiState(UiState.LOADING)
    }

    @UiThread
    fun updateUiState(state: UiState) {
        _uiState.value = state
    }
}

enum class UiState {
    EMPTY,
    LOADING,
    SUCCESS,
    ERROR
}
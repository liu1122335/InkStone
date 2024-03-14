package com.example.inkstonedemo1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.inkstonedemo1.model.uistate.DetailInformationScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailInformationScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DetailInformationScreenUiState())
    val uiState : StateFlow<DetailInformationScreenUiState> = _uiState.asStateFlow()

    fun updateIsCollected(isCollected : Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isCollected = isCollected
            )
        }
    }
}
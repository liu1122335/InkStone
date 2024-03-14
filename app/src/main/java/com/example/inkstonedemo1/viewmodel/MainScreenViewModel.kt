package com.example.inkstonedemo1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.inkstonedemo1.model.uistate.MainScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState : StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    fun updateCurrentPageId(currentId : Int){
        _uiState.update {currentState ->
            currentState.copy(
                currentInkStoneId = currentId
            )
        }
    }
}
package com.example.inkstonedemo1.intent.viewmodel

import androidx.lifecycle.ViewModel
import com.example.inkstonedemo1.model.uistate.HistoryKnowledgeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HistoryKnowledgeScreenViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(HistoryKnowledgeScreenUiState())
    val uiState : StateFlow<HistoryKnowledgeScreenUiState> = _uiState.asStateFlow()

    fun updateCurrentHistoryId(historyKnowledgeId : Int){
        _uiState.update {currentState ->
            currentState.copy(
                currentHistoryId = historyKnowledgeId
            )
        }
    }
}
package com.example.inkstonedemo1.intent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inkstonedemo1.MyApplication
import com.example.inkstonedemo1.model.uistate.MainScreenUiState
import com.example.inkstonedemo1.room.inkstone.InkStone
import com.example.inkstonedemo1.room.inkstone.InkStoneDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState : StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    private val inkStoneDao = InkStoneDatabase.getDatabase(MyApplication.context).inkStoneDao()

    val allInkStone : Flow<List<InkStone>> =inkStoneDao.getAllInkStone()
    val allCollectedInkStone : Flow<List<InkStone>> = inkStoneDao.getCollectedInkStone()
    val allARInkStone : Flow<List<InkStone>> = inkStoneDao.getARInkStone()

    fun insertInkStone(inkStone: InkStone){
        viewModelScope.launch {
            inkStone.id = inkStoneDao.insertInkStone(inkStone)
        }
    }

    private fun updateInkStone(inkStone: InkStone){
        viewModelScope.launch {
            inkStoneDao.updateInkStone(inkStone)
        }
    }

    fun updateInkStoneIsCollected(isCollected : Boolean){
        _uiState.update { currentUiState ->
            currentUiState.copy(
                inkStone = currentUiState.inkStone.copy(isCollected = isCollected)
            )
        }
        updateInkStone(inkStone = _uiState.value.inkStone)
    }

    fun updateCurrentUiState(inkStone: InkStone){
        _uiState.update {currentUiState ->
            currentUiState.copy(inkStone = inkStone)
        }
    }

    fun getAllRelevancyInkStone(type : String , id: Long): Flow<List<InkStone>>{
        return inkStoneDao.getAllRelevancyInkStone(type = type,id = id)
    }
}
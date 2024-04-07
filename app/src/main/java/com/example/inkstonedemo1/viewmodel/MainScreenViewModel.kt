package com.example.inkstonedemo1.viewmodel

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inkstonedemo1.MyApplication
import com.example.inkstonedemo1.model.uistate.MainScreenUiState
import com.example.inkstonedemo1.room.InkStone
import com.example.inkstonedemo1.room.InkStoneDatabase
import dev.romainguy.kotlin.math.all
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState : StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    private val inkStoneDao = InkStoneDatabase.getDatabase(MyApplication.context).inkStoneDao()

    val allInkStone : Flow<List<InkStone>> =inkStoneDao.getAllInkStone()
    val allCollectedInkStone : Flow<List<InkStone>> = inkStoneDao.getCollectedInkStone()

    fun getRelevancyList(type : String) : Flow<List<InkStone>>{
        return inkStoneDao.getAllRelevancyInkStone(type)
    }

    fun insertInkStone(inkStone: InkStone){
        viewModelScope.launch {
            inkStoneDao.insertInkStone(inkStone)
        }
    }

    fun updateInkStone(inkStone: InkStone){
        viewModelScope.launch {
            inkStoneDao.updateInkStone(inkStone)
        }
    }

    fun updateCurrentPageId(currentId : Int){
        _uiState.update {currentState ->
            currentState.copy(
                currentInkStoneId = currentId
            )
        }
    }
}
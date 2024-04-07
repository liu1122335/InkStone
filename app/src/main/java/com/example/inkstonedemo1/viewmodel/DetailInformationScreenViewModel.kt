package com.example.inkstonedemo1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.inkstonedemo1.MyApplication
import com.example.inkstonedemo1.model.uistate.DetailInformationScreenUiState
import com.example.inkstonedemo1.room.InkStone
import com.example.inkstonedemo1.room.InkStoneDao
import com.example.inkstonedemo1.room.InkStoneDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailInformationScreenViewModel(inkStone: InkStone) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailInformationScreenUiState(inkStone))
    val uiState : StateFlow<DetailInformationScreenUiState> = _uiState.asStateFlow()

    private val inkStoneDao = InkStoneDatabase.getDatabase(MyApplication.context).inkStoneDao()

    fun getRelevancyInkStoneList(type : String) : Flow<List<InkStone>> {
        return inkStoneDao.getAllRelevancyInkStone(type)
    }

    fun updateInkStoneIsCollected(isCollected : Boolean,mainScreenViewModel: MainScreenViewModel){
        _uiState.update { currentUiState ->
            currentUiState.copy(
                inkStone = currentUiState.inkStone.copy(isCollected = isCollected)
            )
        }
        mainScreenViewModel.updateInkStone(_uiState.value.inkStone)
    }
}
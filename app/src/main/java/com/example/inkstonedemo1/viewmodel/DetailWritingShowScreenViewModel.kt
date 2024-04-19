package com.example.inkstonedemo1.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.inkstonedemo1.model.uistate.DetailWritingShowScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailWritingShowScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DetailWritingShowScreenUiState())
    val uiState : StateFlow<DetailWritingShowScreenUiState> = _uiState.asStateFlow()

    fun updateUiState(bitmap: Bitmap,id : Long){
        _uiState.update { currentUiState ->
            currentUiState.copy(
                currentBitmap = bitmap,
                id = id
            )
        }
    }

}
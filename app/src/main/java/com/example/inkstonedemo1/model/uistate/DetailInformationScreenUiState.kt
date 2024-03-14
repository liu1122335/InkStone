package com.example.inkstonedemo1.model.uistate

import com.example.inkstonedemo1.model.InkStone

data class DetailInformationScreenUiState(
    val inkStone: InkStone = InkStone("","","","",false,false,""),
    val isCollected : Boolean = inkStone.isCollected,
    val isSpeak : Boolean = false
)
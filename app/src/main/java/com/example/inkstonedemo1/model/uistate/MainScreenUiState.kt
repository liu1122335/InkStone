package com.example.inkstonedemo1.model.uistate

import com.example.inkstonedemo1.data.InitInkStoneData
import com.example.inkstonedemo1.room.inkstone.InkStone

data class MainScreenUiState(
    val inkStone: InkStone = InitInkStoneData[0]
)

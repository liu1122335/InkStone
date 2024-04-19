package com.example.inkstonedemo1.model

data class InkStoneTypeKnowledge(
    val type : String,
    val description : String,
    val representativeFigureName : List<String>,
    val representativeFigureAvatar: List<Int>,
    val relevancyInkStoneName : List<String>,
    val relevancyInkStoneImage: List<Int>
)
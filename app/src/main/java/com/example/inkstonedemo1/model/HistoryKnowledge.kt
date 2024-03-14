package com.example.inkstonedemo1.model

data class HistoryKnowledge (
    val title : String, //标题
    val content : String, //内容
    val relevancyImage : List<Int> , //关联砚台
    val relevancyName : List<String>
)
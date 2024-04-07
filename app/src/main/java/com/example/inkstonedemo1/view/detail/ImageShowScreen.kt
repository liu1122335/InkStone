package com.example.inkstonedemo1.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.component.AppToolsBar

@Composable
fun ImageShowScreen(
    imageId : Int,
    inkStoneName : String,
    onBack : () -> Unit
){
    Column {
        AppToolsBar(
            title = inkStoneName,
            onBack = onBack
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
        }
    }
}
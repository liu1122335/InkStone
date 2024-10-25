package com.example.inkstonedemo1.view.user

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.inkstonedemo1.MyApplication.Companion.context
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.utils.BitmapUtils
import com.example.inkstonedemo1.intent.viewmodel.DetailWritingShowScreenViewModel
import com.example.inkstonedemo1.intent.viewmodel.UserScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailWritingShowScreen(
    detailWritingShowScreenViewModel: DetailWritingShowScreenViewModel,
    userScreenViewModel: UserScreenViewModel,
    onBackButtonClicked : () -> Unit
){
    val uiState by detailWritingShowScreenViewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "笔墨预览",fontFamily = FontFamily(Font(R.font.font_1)))
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackButtonClicked() }
                    ){
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            userScreenViewModel.deleteCalligraphy(id = uiState.id)
                            Toast.makeText(context, "笔墨已删除", Toast.LENGTH_SHORT)
                                .show()
                            onBackButtonClicked()
                        }
                    ){
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            contentDescription = "",
                        )
                    }
                    IconButton(
                        onClick = {
                            BitmapUtils.saveBitmapToGallery(context, uiState.currentBitmap, "inkstone")
                            Toast.makeText(context, "笔墨已下载至相册", Toast.LENGTH_SHORT)
                                .show()
                        }
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_download),
                            contentDescription = "",
                        )
                    }
                }
            )
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    bitmap = uiState.currentBitmap.asImageBitmap(),
                    contentDescription = ""
                )
            }
        }
    }
}
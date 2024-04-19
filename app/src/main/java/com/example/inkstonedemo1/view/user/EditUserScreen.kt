package com.example.inkstonedemo1.view.user

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.inkstonedemo1.MyApplication
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.model.EditUserDestination
import com.example.inkstonedemo1.room.user.User
import com.example.inkstonedemo1.utils.rotateIfRequired
import com.example.inkstonedemo1.viewmodel.UserScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserScreen(
    onBackButtonClicked : () -> Unit,
    userScreenViewModel: UserScreenViewModel
){
    val coroutineScope = rememberCoroutineScope()
    var nameText by remember { mutableStateOf(userScreenViewModel.uiState.value.user.name) }
    var labelText by remember { mutableStateOf(userScreenViewModel.uiState.value.user.label) }
    var backgroundBitmap by remember { mutableStateOf(userScreenViewModel.uiState.value.background) }
    var avatarBitmap by remember { mutableStateOf(userScreenViewModel.uiState.value.avatar) }

    var showLoadingScreen by remember { mutableStateOf(false) }

    val galleryLauncher1 = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                val bitmap = MyApplication.context.contentResolver.openFileDescriptor(uri,"r")?.use {
                    rotateIfRequired(
                        bitmap = BitmapFactory.decodeFileDescriptor(it.fileDescriptor),
                        fileDescriptor = it.fileDescriptor
                    )
                }
                backgroundBitmap = bitmap!!
                coroutineScope.launch {
                    showLoadingScreen = true
                    delay(1000)
                    showLoadingScreen= false
                }
            }
        }
    )
    val galleryLauncher2 = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                val bitmap = MyApplication.context.contentResolver.openFileDescriptor(uri,"r")?.use {
                    rotateIfRequired(
                        bitmap = BitmapFactory.decodeFileDescriptor(it.fileDescriptor),
                        fileDescriptor = it.fileDescriptor
                    )
                }
                avatarBitmap = bitmap!!
                coroutineScope.launch {
                    showLoadingScreen = true
                    delay(1000)
                    showLoadingScreen= false
                }
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = EditUserDestination.tabName)
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackButtonClicked
                    ){
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(
                        modifier = Modifier.fillMaxHeight().padding(end = 30.dp),
                        onClick = {
                            userScreenViewModel.updateUiState(
                                name = nameText,
                                label = labelText,
                                background = backgroundBitmap,
                                avatar = avatarBitmap
                            )
                            userScreenViewModel.updateDatabaseData(
                                user = User(name = nameText, label = labelText),
                                background = backgroundBitmap,
                                avatar = avatarBitmap
                            )
                            Toast.makeText(MyApplication.context,"保存成功",Toast.LENGTH_SHORT).show()
                            onBackButtonClicked()
                        }
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_save),
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                //修改图片
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    //改背景图片
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = backgroundBitmap.asImageBitmap(),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )
                        FilledTonalButton(
                            modifier = Modifier.padding(top = 10.dp),
                            onClick = { galleryLauncher1.launch("image/*") }
                        ){
                            Text(text = "更换背景图片")
                        }
                    }
                    //改头像
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = avatarBitmap.asImageBitmap(),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )
                        FilledTonalButton(
                            modifier = Modifier.padding(top = 10.dp),
                            onClick = { galleryLauncher2.launch("image/*") }
                        ){
                            Text(text = "更换头像")
                        }
                    }
                }

                //该名称
                TextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                    value = nameText,
                    onValueChange = { nameText = it },
                    label = {
                        Text("名称")
                    },
                    singleLine = true
                )
                //改签名
                TextField(
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                    value = labelText,
                    onValueChange = { labelText = it },
                    label = {
                        Text("签名")
                    },
                )
            }
            AnimatedVisibility(
                visible = showLoadingScreen,
                modifier = Modifier.fillMaxSize()
            ){
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Dialog(onDismissRequest = {  }) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
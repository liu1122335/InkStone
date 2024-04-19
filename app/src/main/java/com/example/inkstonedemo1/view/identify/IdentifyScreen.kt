package com.example.inkstonedemo1.view.identify

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.utils.identifyPhoto
import com.example.inkstonedemo1.utils.rotateIfRequired
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IdentifyScreen(

){
    val context = LocalContext.current
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden, animationSpec = tween(1000))
    val coroutineScope = rememberCoroutineScope()

    var identifyBitmap by remember { mutableStateOf<Bitmap>(BitmapFactory.decodeResource(context.resources,R.drawable.identify_temp_bitmap)) }

    var identifyResult by remember { mutableStateOf("") }

    var isIdentifyLoading by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        ModalBottomSheetLayout(
            sheetState = modalBottomSheetState,
            sheetContent = {
                IdentifyResultScreen(
                    identifyBitmap = identifyBitmap,
                    identifyResult = identifyResult
                )
            },
            sheetShape = RoundedCornerShape(17.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                PreviewLayout(
                    context = context,
                    takePhotoIdentify = { uri ->
                        val bitmap = context.contentResolver.openFileDescriptor(uri,"r")?.use {
                            rotateIfRequired(
                                bitmap = BitmapFactory.decodeFileDescriptor(it.fileDescriptor),
                                fileDescriptor = it.fileDescriptor
                            )
                        }
                        identifyBitmap = bitmap!!

                        coroutineScope.launch {
                            isIdentifyLoading = true
                            delay(1000)
                            isIdentifyLoading = false
                            identifyResult = identifyPhoto(context,bitmap)
                            modalBottomSheetState.show()
                        }

                    },
                    selectPhotoIdentify = { uri ->
                        val bitmap = context.contentResolver.openFileDescriptor(uri,"r")?.use {
                            rotateIfRequired(
                                bitmap = BitmapFactory.decodeFileDescriptor(it.fileDescriptor),
                                fileDescriptor = it.fileDescriptor
                            )
                        }
                        identifyBitmap = bitmap!!

                        coroutineScope.launch {
                            isIdentifyLoading = true
                            delay(1000)
                            isIdentifyLoading = false
                            identifyResult = identifyPhoto(context,bitmap)
                            modalBottomSheetState.show()
                        }
                    }
                )
            }
        }
        if (isIdentifyLoading){
            Dialog(onDismissRequest = { }) {
                CircularProgressIndicator()
            }
        }
    }
    BackHandler(
        enabled = (modalBottomSheetState.currentValue == ModalBottomSheetValue.HalfExpanded
                || modalBottomSheetState.currentValue == ModalBottomSheetValue.Expanded),
        onBack = {
            coroutineScope.launch {
                modalBottomSheetState.hide()
            }
        }
    )
}


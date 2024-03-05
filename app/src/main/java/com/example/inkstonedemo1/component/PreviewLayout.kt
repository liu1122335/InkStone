package com.example.inkstonedemo1.component

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.FlashMode
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.compose.dark_golden
import com.example.inkstonedemo1.R
import com.example.inkstonedemo1.utils.getCameraProvider
import com.example.inkstonedemo1.utils.startTakePhoto
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterialApi::class)
@Composable
fun PreviewLayout(
    context: Context,
    takePhotoIdentify: (Uri) -> Unit,
    selectPhotoIdentify: (Uri) -> Unit
){
    //相机权限申请
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    )

    LaunchedEffect(Unit){
        permissionState.launchPermissionRequest()
    }

    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = { /*TODO*/ },
        permissionNotAvailableContent = { /*TODO*/ }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PreviewView(
                context = context,
                modifier = Modifier.fillMaxSize(),
                takePhotoClick = {
                    startTakePhoto(
                        imageCapture = it,
                        contentResolver = context.contentResolver,
                        executor = ContextCompat.getMainExecutor(context),
                        onSuccess = { uri ->
                            takePhotoIdentify(uri)
                            Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                        },
                        onError = {

                        }
                    )
                },
                selectPhotoIdentify = selectPhotoIdentify
            )
        }
    }
}

@Composable
fun PreviewView(
    context : Context,
    modifier: Modifier,
    takePhotoClick : (imageCapture : ImageCapture) -> Unit,
    selectPhotoIdentify: (Uri) -> Unit
){
    val lifecycleOwner = LocalLifecycleOwner.current
    val preview = Preview.Builder().build()
    val previewView = PreviewView(context)
    val cameraExecutor = Executors.newSingleThreadExecutor()
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    val imageCapture = remember { ImageCapture.Builder().build() }

    var isLighting by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = CameraSelector.LENS_FACING_BACK){
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }
    DisposableEffect(Unit){
        onDispose {
            cameraExecutor.shutdown()
        }
    }

    Column (
        modifier = modifier
    ){
        //预览界面
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxHeight(0.75f)
        )

        Row (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){

            val flashOnImage = R.drawable.ic_flash_on
            val flashOffImage = R.drawable.ic_flash_off
            var nextImage by remember { mutableStateOf(flashOffImage) }
            val flashImage by animateIntAsState(targetValue = nextImage)

            val galleryLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { uri ->
                    uri?.let {
                        selectPhotoIdentify(uri)
                    }
                }
            )

            //从相册中选择图片
            Image(painter = painterResource(id = R.drawable.ic_album), contentDescription = "",modifier = Modifier.clickable{
                galleryLauncher.launch("image/*")
            })

            //拍照按钮
            FloatingActionButton(
                onClick = {
                    takePhotoClick(imageCapture)
                },
                modifier = Modifier
                    .border(width = 3.dp, color = dark_golden, shape = CircleShape)
                    .size(70.dp),
                containerColor = Color.Transparent,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_photo), contentDescription = "")
            }

            //闪关灯开关
            Image(painter = painterResource(id = flashImage), contentDescription = "",modifier = Modifier.clickable{
                if (!isLighting){
                    imageCapture.flashMode = ImageCapture.FLASH_MODE_ON
                    isLighting = true
                    nextImage = flashOnImage
                    Toast.makeText(context,"闪关灯已打开",Toast.LENGTH_SHORT).show()
                }else{
                    imageCapture.flashMode = ImageCapture.FLASH_MODE_OFF
                    isLighting = false
                    nextImage = flashOffImage
                    Toast.makeText(context,"闪关灯已关闭",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}


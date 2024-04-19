package com.example.inkstonedemo1.view

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.inkstonedemo1.MyApplication.Companion.context
import com.example.inkstonedemo1.R
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize().background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        val currentOnTimeOut by rememberUpdatedState(onTimeout)
        LaunchedEffect(Unit) {
            delay(1500)
            currentOnTimeOut()
        }
        val imgLoader = ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        val mPainter = rememberAsyncImagePainter(R.drawable.ic_loading, imgLoader)

        Image(
            painter = mPainter,
            contentDescription = "123",
            modifier = Modifier.size(200.dp)
        )

    }
}
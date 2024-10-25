package com.example.inkstonedemo1.view.funny

import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.vr.sdk.widgets.pano.VrPanoramaView

@Composable
fun VRShowScreen(
    vrPanoramaImage : Int
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        AndroidView(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f),
            factory = { VrPanoramaView(it) }
        ){
            it.apply {
                val panoramaOptions = VrPanoramaView.Options()
                panoramaOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER
                setInfoButtonEnabled(false)
                /*setStereoModeButtonEnabled(false)
                setEventListener(object : VrPanoramaEventListener(){
                    override fun onClick() {
                        super.onClick()
                        currentId = (currentId+1) % count
                        loadImageFromBitmap(
                            BitmapFactory.decodeResource(
                                resources,
                                allVrImage[currentId]
                            ),panoramaOptions
                        )
                    }
                })*/
                //加载全景图
                loadImageFromBitmap(
                    BitmapFactory.decodeResource(
                        resources,
                        vrPanoramaImage
                    ),panoramaOptions
                )
            }
        }
    }
}
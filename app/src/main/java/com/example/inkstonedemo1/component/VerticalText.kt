package com.example.inkstonedemo1.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import com.example.inkstonedemo1.R

@Composable
fun VerticalText(
    text: String,
    color: Color = Color.Unspecified,
    textStyle: TextStyle = LocalTextStyle.current,
    modifier: Modifier = Modifier
) {
    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = modifier
    ){
        Canvas(modifier = Modifier) {
            text.toCharArray().forEachIndexed { index, it ->
                val textLayoutResult = textMeasurer.measure(it.toString(), textStyle)
                textLayoutResult.lastBaseline
                drawText(
                    textLayoutResult,
                    color,
                    Offset(0f, index * textLayoutResult.lastBaseline)
                )
            }
        }

    }
}


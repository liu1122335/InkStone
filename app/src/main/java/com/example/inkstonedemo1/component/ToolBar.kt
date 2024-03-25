package com.example.inkstonedemo1.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.md_theme_light_onTertiaryContainer
import com.example.compose.md_theme_light_tertiaryContainer

@Composable
fun AppToolsBar(
    title: String,
    onBack: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color = md_theme_light_tertiaryContainer)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 10.dp)
        ) {
            if (onBack != null) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onBack
                        )
                        .padding(start = 15.dp),
                    tint = Color.Black
                )
            }
        }
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            maxLines = 1
        )

    }
}
package com.sunshine.android.util

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay

@Composable
fun TypewriterText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    speed: Long = 50,
    onFinish: () -> Unit = {},
) {
    var textToDisplay by rememberSaveable {
        mutableStateOf("")
    }

    LaunchedEffect(
        key1 = text,
    ) {
        text.forEachIndexed { charIndex, _ ->
            textToDisplay = text.substring(
                startIndex = 0,
                endIndex = charIndex + 1,
            )
            delay(speed)
        }
        onFinish()
    }

    Box {
        Text(
            modifier = modifier,
            text = textToDisplay,
            style = style,
        )
        Text(
            modifier = modifier.alpha(0f),
            text = text,
            style = style,
        )
    }
}
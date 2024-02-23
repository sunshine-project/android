package com.sunshine.android.ui.common.component

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun AnimatedImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    speed: Int = 20000,
) {
    var horizontalState by remember { mutableStateOf(false) }
    var verticalState by remember { mutableStateOf(false) }

    var horizontalCount by remember { mutableStateOf(0) }
    var verticalCount by remember { mutableStateOf(0) }

    val biased = (when {
        horizontalState && verticalState -> BiasAlignment(0f, 0f)
        !horizontalState && verticalState -> BiasAlignment(1f, 0f)
        horizontalState && !verticalState -> BiasAlignment(0f, 1f)
        else -> BiasAlignment(1f, 1f)
    })

    val horizontal by animateFloatAsState(
        biased.horizontalBias,
        label = "horizontalBias",
        animationSpec = tween(durationMillis = speed, easing = LinearEasing),
        finishedListener = {
            if (horizontalCount < 100) {
                horizontalCount++
                horizontalState = !horizontalState
            }
        }
    )

    val vertical by animateFloatAsState(
        biased.verticalBias,
        label = "verticalBias",
        animationSpec = tween(durationMillis = speed, easing = LinearEasing),
        finishedListener = {
            if (verticalCount < 100) {
                verticalCount++
                verticalState = !verticalState
            }
        }
    )

    val alignment by derivedStateOf { BiasAlignment(horizontal, vertical) }

    LaunchedEffect(
        key1 = false,
    ) {
        horizontalState = true
        verticalState = true
    }

    Image(
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alignment = alignment,
        contentDescription = contentDescription,
        painter = painter,
    )
}
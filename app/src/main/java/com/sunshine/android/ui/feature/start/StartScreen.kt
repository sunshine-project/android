package com.sunshine.android.ui.feature.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunshine.android.R
import com.sunshine.android.ui.theme.Typography

@Composable
internal fun StartRoute(
    onScreenClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StartViewModel = hiltViewModel(),
) {
    StartScreen(
        modifier = modifier,
        onScreenClick = onScreenClick,
    )
}

@Composable
internal fun StartScreen(
    modifier: Modifier = Modifier,
    onScreenClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                onScreenClick()
            }
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            contentDescription = "start bg",
            painter = painterResource(id = R.drawable.img_start_bg)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                modifier = modifier
                    .background(color = Color.Black.copy(alpha = 0.25f))
                    .padding(4.dp),
                text = "SUNSHINE",
                style = Typography.titleLarge.copy(
                    fontSize = 64.sp, color = Color.White
                ),
            )
            Text(
                modifier = modifier,
                text = "- TAP TO START -",
                style = Typography.bodyMedium.copy(
                    fontSize = 24.sp, color = Color.White, shadow = Shadow(
                        color = Color.Black, offset = Offset(0f, 0f), blurRadius = 16f
                    )
                ),
            )
        }
    }
}
package com.sunshine.android.ui.feature.start

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunshine.android.R
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.ui.common.component.AnimatedImage

@Composable
internal fun StartRoute(
    onModeSelect: () -> Unit,
    onHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StartViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    StartScreen(
        modifier = modifier,
        uiState = uiState,
        onScreenClick = {
            when (uiState.isRegistered) {
                true -> onHome()
                false -> onModeSelect()
                else -> viewModel.googleLogin(context)
            }
        },
    )
}

@Composable
internal fun StartScreen(
    modifier: Modifier = Modifier,
    uiState: StartUiState,
    onScreenClick: () -> Unit,
) {
    val startVisibilityAnimation = rememberInfiniteTransition(label = "startVisibilityAnimation")
    val startVisibility by startVisibilityAnimation.animateFloat(
        initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ), label = "startVisibilityAnimation"
    )

    Box(modifier = modifier
        .fillMaxSize()
        .clickable {
            onScreenClick()
        }) {
        AnimatedImage(
            modifier = modifier.fillMaxSize(),
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
                text = stringResource(R.string.start_title),
                style = Typography.titleLarge.copy(
                    fontSize = 64.sp, color = Color.White
                ),
            )
            Text(
                modifier = modifier.alpha(startVisibility),
                text = if (uiState.isRegistered != null) stringResource(R.string.start_tap_to_start)
                else stringResource(R.string.start_tap_to_login),
                style = Typography.bodyMedium.copy(
                    fontSize = 24.sp, color = Color.White, shadow = Shadow(
                        color = Color.Black, offset = Offset(0f, 0f), blurRadius = 16f
                    )
                ),
            )
        }
    }
}
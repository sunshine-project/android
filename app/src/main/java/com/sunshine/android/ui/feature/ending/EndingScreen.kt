package com.sunshine.android.ui.feature.ending

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunshine.android.R
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.DarkBrown
import com.sunshine.android.ui.theme.LightBrown
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.ui.common.component.AnimatedImage
import com.sunshine.android.ui.common.component.TypewriterText

@Composable
internal fun EndingRoute(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EndingViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EndingScreen(
        onFinish = onFinish,
        uiState = uiState,
        onShowNextButton = viewModel::showNextButton,
        modifier = modifier,
    )
}

@Composable
internal fun EndingScreen(
    onFinish: () -> Unit,
    uiState: EndingUiState,
    onShowNextButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showEndingDialog by rememberSaveable { mutableStateOf(false) }
    Box {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedImage(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentDescription = "ending image",
                painter = painterResource(id = uiState.currentEnding.imageRes)
            )
            Column(
                modifier = modifier
                    .background(color = LightBrown)
                    .fillMaxWidth()
                    .border(
                        width = 4.dp,
                        color = Brown,
                    )
                    .padding(horizontal = 24.dp, vertical = 36.dp),
                horizontalAlignment = Alignment.End,
            ) {
                TypewriterText(
                    modifier = modifier.fillMaxWidth(),
                    text = uiState.currentEnding.content,
                    style = Typography.bodyMedium.copy(
                        color = colorResource(id = R.color.white),
                        lineHeight = 32.sp,
                        fontSize = 20.sp
                    ),
                    onFinish = onShowNextButton
                )
                Spacer(modifier = modifier.height(24.dp))
                ElevatedButton(
                    modifier = modifier
                        .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                        .alpha(if (uiState.isShowNextButton) 1f else 0f),
                    onClick = { showEndingDialog = true },
                    colors = ButtonColors(
                        contentColor = Color.White,
                        containerColor = DarkBrown,
                        disabledContainerColor = DarkBrown,
                        disabledContentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(0.dp),
                ) {
                    Text(
                        text = stringResource(R.string.common_next),
                        style = Typography.bodyMedium.copy(fontSize = 20.sp)
                    )
                }
            }
        }
        if (showEndingDialog) {
            EndingDialog(jobList = uiState.job, onDismiss = {
                showEndingDialog = false
                onFinish()
            })
        }
    }
}
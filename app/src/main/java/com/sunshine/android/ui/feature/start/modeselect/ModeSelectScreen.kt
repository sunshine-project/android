package com.sunshine.android.ui.feature.start.modeselect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunshine.android.R
import com.sunshine.android.ui.feature.start.StartViewModel
import com.sunshine.android.ui.theme.Typography

@Composable
internal fun ModeSelectRoute(
    onNormalModeClick: () -> Unit,
    onFreeModeClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StartViewModel = hiltViewModel(),
) {
    ModeSelectScreen(
        modifier = modifier,
        onNormalModeClick = onNormalModeClick,
        onFreeModeClick = onFreeModeClick,
    )
}

@Composable
internal fun ModeSelectScreen(
    modifier: Modifier = Modifier,
    onNormalModeClick: () -> Unit,
    onFreeModeClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable {

            }
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            contentDescription = "start bg",
            painter = painterResource(id = R.drawable.img_start_bg)
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                modifier = modifier
                    .background(color = Color.Black.copy(alpha = 0.25f))
                    .padding(4.dp),
                text = "SELECT MODE",
                style = Typography.titleLarge.copy(
                    fontSize = 42.sp, color = Color.White
                ),
            )
            Column {
                Column(
                    modifier = modifier
                        .clickable {
                            onNormalModeClick()
                        }
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .padding(24.dp),
                ) {
                    Text(
                        modifier = modifier
                            .background(color = Color.Black)
                            .padding(2.dp),
                        text = "CHALLENGE MODE",
                        style = Typography.bodyLarge.copy(
                            fontSize = 24.sp, color = Color.White,
                        ),
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    Text(
                        modifier = modifier,
                        text = "70일 내에 검을 뽑지 못하면\n" +
                                "왕국은 멸망합니다.",
                        style = Typography.bodyMedium.copy(
                            fontSize = 20.sp, color = Color.Black,
                            lineHeight = 28.sp
                        ),
                    )
                }
                Spacer(modifier = modifier.height(24.dp))
                Column(
                    modifier = modifier
                        .clickable {
                            onFreeModeClick()
                        }
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .padding(24.dp),
                ) {
                    Text(
                        modifier = modifier
                            .background(color = Color.Gray)
                            .padding(2.dp),
                        text = "FREE MODE",
                        style = Typography.bodyLarge.copy(
                            fontSize = 24.sp, color = Color.White,
                        ),
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    Text(
                        modifier = modifier,
                        text = "시간 제한이 없고, 원하는 \n" +
                                "퀘스트를 골라 수행할 수 \n" +
                                "있습니다. 챌린지 모드를 \n" +
                                "클리어하면 해금됩니다.",
                        style = Typography.bodyMedium.copy(
                            fontSize = 20.sp, color = Color.Gray,
                            lineHeight = 28.sp
                        ),
                    )
                }
            }
        }
    }
}
package com.sunshine.android.ui.feature.start.modeselect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunshine.android.R
import com.sunshine.android.ui.feature.start.StartViewModel
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.ui.common.component.AnimatedImage

@Composable
internal fun ModeSelectRoute(
    onNormalModeClick: () -> Unit,
    onFreeModeClick: () -> Unit,
    modifier: Modifier = Modifier,
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
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedImage(
            modifier = modifier.fillMaxSize(),
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
                text = stringResource(R.string.mode_select_title),
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
                        text = stringResource(R.string.mode_select_challange),
                        style = Typography.bodyLarge.copy(
                            fontSize = 24.sp, color = Color.White,
                        ),
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    Text(
                        modifier = modifier,
                        text = stringResource(R.string.mode_select_challange_content),
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = modifier
                                .background(color = Color.Gray)
                                .padding(2.dp),
                            text = stringResource(R.string.mode_select_free),
                            style = Typography.bodyLarge.copy(
                                fontSize = 24.sp, color = Color.White,
                            ),
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        Image(
                            modifier = modifier
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.ic_lock),
                            contentDescription = "ic_lock",
                            colorFilter = ColorFilter.tint(Color.Gray)
                        )
                    }
                    Spacer(modifier = modifier.height(24.dp))
                    Text(
                        modifier = modifier,
                        text = stringResource(R.string.mode_select_free_content),
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
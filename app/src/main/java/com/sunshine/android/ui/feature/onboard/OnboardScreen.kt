package com.sunshine.android.ui.feature.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sunshine.android.R
import com.sunshine.android.ui.theme.DarkBrown
import com.sunshine.android.ui.theme.LightBrown
import com.sunshine.android.ui.theme.Typography

@Composable
internal fun OnboardRoute(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardViewModel = hiltViewModel(),
) {
    OnboardScreen(
        onFinish = onFinish,
        modifier = modifier,
    )
}

@Composable
internal fun OnboardScreen(
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier.weight(1f),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            contentDescription = "onboard image",
            painter = painterResource(id = R.drawable.img_story_1)
        )
        Column(
            modifier = modifier
                .background(color = LightBrown)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 36.dp), horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = modifier,
                text = "태초에 여러 신들이 있었다.\n" + "인간 창조에 관여한 신은 4명이며, \n" + "이들은 각각 생명, 정신, 평화, 지식의 신이다.",
                style = Typography.bodyMedium.copy(
                    color = colorResource(id = R.color.white), lineHeight = 32.sp, fontSize = 20.sp
                )
            )
            Spacer(modifier = modifier.height(24.dp))
            ElevatedButton(
                modifier = modifier.defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
                onClick = { onFinish() },
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = DarkBrown,
                    disabledContainerColor = DarkBrown,
                    disabledContentColor = Color.Black
                ),
                shape = RoundedCornerShape(0.dp),
            ) {
                Text(text = "NEXT", style = Typography.bodyMedium.copy(fontSize = 20.sp))
            }
        }
    }
}
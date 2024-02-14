package com.sunshine.android.presention

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sunshine.android.R

@Composable
fun MissionDialog(
    onDismiss: () -> Unit
) {
    Box(modifier = Modifier.height(240.dp)) {
        // 기본 이미지
        Image(
            painter = painterResource(id = R.drawable.img_paper_shadow),
            contentDescription = "Base Image",
            modifier = Modifier
                .fillMaxWidth()
                .width(100.dp),
            alignment = Alignment.CenterEnd,
            contentScale = ContentScale.Crop
        )
        Text(
            text = "완료하지 않은 미션이 있어요!",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 16.dp),
            color = Color.White
        )

        Image(
            painter = painterResource(id = R.drawable.btn_okay),
            contentDescription = "버튼",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(100.dp)
                .padding(bottom = 30.dp)
                .clickable { onDismiss.invoke() }
        )
    }
}


@Preview
@Composable
fun CustomDialogPreview() {
    MissionDialog(onDismiss = {})
}
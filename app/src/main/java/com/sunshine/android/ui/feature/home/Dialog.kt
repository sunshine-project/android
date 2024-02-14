package com.sunshine.android.ui.feature.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sunshine.android.R

@Composable
fun Dialog(
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
    Dialog(onDismiss = {})
}

@Composable
fun MinimalDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun MinimalDialogPreview() {
    MinimalDialog(onDismiss = {})
}
package com.sunshine.android.ui.common.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sunshine.android.R

@Composable
fun SunDialog(comment: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.img_paper_shadow),
                    contentDescription = "Base Image",
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = comment,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(start = 50.dp, end = 50.dp),
                        color = Color.Black
                    )

                    Image(painter = painterResource(id = R.drawable.btn_okay),
                        contentDescription = "btn_ok",
                        modifier = Modifier
                            .width(100.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 10.dp)
                            .clickable { onDismiss.invoke() })
                }
            }
        }
    }
}

@Preview
@Composable
fun MinimalDialogPreview() {
    SunDialog("동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리 나라 만세", onDismiss = {})
}
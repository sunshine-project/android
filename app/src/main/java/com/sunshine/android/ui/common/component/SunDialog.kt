package com.sunshine.android.ui.common.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sunshine.android.R
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.Typography

@Composable
fun SunDialog(comment: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = {}) { // 빈 공간을 클릭해도 다이얼로그가 닫히지 않도록 onDismissRequest를 무시합니다.
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
        ) {
            Box(
//                modifier = Modifier.clickable { } // 빈 공간을 클릭해도 아무런 동작을 하지 않도록 clickable을 추가합니다.
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_paper_shadow),
                    contentDescription = "Base Image",
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    contentScale = FillBounds
                )
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    TypewriterText(
                        text = comment,
                        modifier = Modifier
                            .padding(horizontal = 40.dp)
                            .fillMaxWidth(),
                        style = Typography.bodyMedium.copy(
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            lineHeight = 24.sp
                        ),
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // ElevatedButton에만 클릭 가능한 영역을 제한합니다.
                    ElevatedButton(
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally)
//                            .clickable { onDismiss() }, // 버튼을 클릭했을 때만 onDismiss 함수를 호출합니다.
//                        onClick = { },

                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            onDismiss()
                        },
                        colors = ButtonColors(
                            contentColor = Color.White,
                            containerColor = Brown,
                            disabledContainerColor = Brown,
                            disabledContentColor = Color.White
                        ),
                        shape = RoundedCornerShape(0.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.common_ok),
                            style = Typography.bodyMedium.copy(
                                fontSize = 16.sp, textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MinimalDialogPreview() {
    SunDialog("동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리 나라 만세동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리 나라 만세동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리 나라 만세동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리 나라 만세",
        onDismiss = {})
}
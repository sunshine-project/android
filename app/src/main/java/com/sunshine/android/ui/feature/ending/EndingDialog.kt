package com.sunshine.android.ui.feature.ending

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sunshine.android.R
import com.sunshine.android.domain.model.JobModel
import com.sunshine.android.ui.theme.Brown
import com.sunshine.android.ui.theme.Typography
import com.sunshine.android.ui.common.component.TypewriterText
import com.sunshine.android.util.getString

@Composable
fun EndingDialog(jobList: List<JobModel>, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp, bottom = 30.dp),
                    painter = painterResource(id = R.drawable.img_scroll_bg),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "bg"
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp, vertical = 60.dp)
                ) {
                    Text(
                        text = getString(R.string.home_the_end_title),
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(),
                        style = Typography.bodyLarge.copy(
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            lineHeight = 24.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    TypewriterText(
                        text = getString(R.string.home_the_end),
                        modifier = Modifier
//                                .padding(horizontal = 40.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(),
                        style = Typography.bodyMedium.copy(
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            lineHeight = 20.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    JobList(modifier = Modifier.weight(1f), jobList = jobList)

                    Spacer(modifier = Modifier.height(10.dp))

                    ElevatedButton(
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
                        shape = RoundedCornerShape(1.dp),
                    ) {
                        Text(
                            text = "THE END", style = Typography.bodyMedium.copy(
                                fontSize = 16.sp, textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }

    }

}

@Composable
fun JobList(modifier: Modifier, jobList: List<JobModel>) {
    val uriHandler = LocalUriHandler.current
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(jobList) {
            JobItem(modifier = modifier.clickable {
                uriHandler.openUri(it.url)
            }, job = it)
        }
    }
}
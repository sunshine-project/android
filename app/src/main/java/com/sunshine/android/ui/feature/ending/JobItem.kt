package com.sunshine.android.ui.feature.ending

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunshine.android.R
import com.sunshine.android.domain.model.JobModel
import com.sunshine.android.ui.theme.Yellow

@Composable
fun JobItem(modifier: Modifier = Modifier, job: JobModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Yellow),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(
                id = when (job.indCd) {
                    1 -> R.drawable.qst_oldman
                    2 -> R.drawable.qst_woman
                    3 -> R.drawable.qst_beardedman
                    4 -> R.drawable.qst_frog
                    5 -> R.drawable.qst_iceghost
                    6 -> R.drawable.qst_man
                    7 -> R.drawable.qst_turtle
                    8 -> R.drawable.qst_pig
                    9 -> R.drawable.qst_kingfrog
                    else -> R.drawable.qst_gentleman
                }
            ), contentDescription = "bg", modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(
                text = job.jobType,
                fontSize = 10.sp,
//                    modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = job.companyName,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(24.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = job.industry,
                    fontSize = 10.sp,
                )
            }
        }
    }
}


@Composable
@Preview
fun CustomItemPreview() {
    JobItem(
        job = JobModel(
            companyName = "(주)클라리파이",
            industry = "솔루션 • SI •ERP • CRW",
            location = "서울 종로구",
            jobType = "정규직",
            url = "http://www.saramin.co.kr/zf_user/jobs/relay/view?rec_idx=47591447&utm_source=job-search-api&utm medium=api&utm campaign=saramin =job-search-api",
            indCd = 3
        )
    )
}
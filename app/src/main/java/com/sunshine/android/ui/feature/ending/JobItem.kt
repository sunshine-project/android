package com.sunshine.android.ui.feature.ending

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.sunshine.android.R
import com.sunshine.android.domain.model.JobModel
import com.sunshine.android.ui.theme.Typography

@Composable
fun JobItem(job: JobModel) {
    Row(
        modifier = Modifier
            .width(300.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.qst_oldman),
            contentDescription = "bg",
            modifier = Modifier.size(100.dp)
        )
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = job.companyName,
                    style = Typography.titleSmall
                )
//                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = job.jobType,
                    style = Typography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            )
            {
                Text(
                    text = job.industry,
                    style = Typography.bodySmall
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
            ind_cd = 3
        )
    )
}
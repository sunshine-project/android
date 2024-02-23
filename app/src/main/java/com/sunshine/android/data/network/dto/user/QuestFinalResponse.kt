package com.sunshine.android.data.network.dto.user

import com.google.gson.annotations.SerializedName
import com.sunshine.android.domain.model.JobModel


data class QuestFinalResponse(
    val companyName: String,
    val industry: String,
    val location: String,
    val jobType: String,
    val url: String,
    @SerializedName("ind_cd") val indCd: Int,
)

fun QuestFinalResponse.asJobModel() = JobModel(
    companyName = companyName,
    industry = industry,
    location = location,
    jobType = jobType,
    url = url,
    indCd = indCd,
)
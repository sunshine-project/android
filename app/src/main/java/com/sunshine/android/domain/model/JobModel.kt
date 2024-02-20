package com.sunshine.android.domain.model


data class JobModel(
    val companyName: String,
    val industry: String,
    val location: String,
    val jobType: String,
    val url: String,
    val ind_cd: Int
)
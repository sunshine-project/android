package com.sunshine.android.data.network.dto.user

import com.sunshine.android.domain.model.JournalModel


data class JournalResponse(
    val title: String,
    val description: String?,
)

fun JournalResponse.asJournalModel() = JournalModel(
    title = title,
    answer = description ?: "",
)
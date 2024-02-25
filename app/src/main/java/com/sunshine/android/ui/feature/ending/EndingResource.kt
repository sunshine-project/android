package com.sunshine.android.ui.feature.ending

import com.sunshine.android.R
import com.sunshine.android.util.getString

data class EndingResource(
    val content: String, val imageRes: Int
)

val endingResources = listOf(
    EndingResource(
        content = getString(R.string.ending_content), imageRes = R.drawable.img_ending
    ),
)
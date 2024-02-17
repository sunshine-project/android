package com.sunshine.android.ui.feature.story

import com.sunshine.android.R
import com.sunshine.android.util.getString

data class StoryResource(
    val content: String,
    val imageRes: Int,
    var isLastPage: Boolean = false
)

val storyResources = listOf(
    StoryResource(
        content = getString(R.string.story_content_1),
        imageRes = R.drawable.img_story_1
    ),
    StoryResource(
        content = getString(R.string.story_content_2),
        imageRes = R.drawable.img_start_bg
    )
)
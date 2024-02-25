package com.sunshine.android.ui.feature.story

import com.sunshine.android.R
import com.sunshine.android.util.getString

data class StoryResource(
    val content: String, val imageRes: Int
)

val storyResources = listOf(
    StoryResource(
        content = getString(R.string.story_content_1), imageRes = R.drawable.img_story_1
    ), StoryResource(
        content = getString(R.string.story_content_2), imageRes = R.drawable.img_story_2
    ), StoryResource(
        content = getString(R.string.story_content_3), imageRes = R.drawable.img_story_3
    ), StoryResource(
        content = getString(R.string.story_content_4), imageRes = R.drawable.img_story_4
    ), StoryResource(
        content = getString(R.string.story_content_5), imageRes = R.drawable.img_story_5
    ), StoryResource(
        content = getString(R.string.story_content_6), imageRes = R.drawable.img_story_6
    ), StoryResource(
        content = getString(R.string.story_content_7), imageRes = R.drawable.img_story_7
    ), StoryResource(
        content = getString(R.string.story_content_8), imageRes = R.drawable.img_story_8
    ), StoryResource(
        content = getString(R.string.story_content_9), imageRes = R.drawable.img_story_9
    ), StoryResource(
        content = getString(R.string.story_content_10), imageRes = R.drawable.img_story_10
    ), StoryResource(
        content = getString(R.string.story_content_11), imageRes = R.drawable.img_story_11
    )
)
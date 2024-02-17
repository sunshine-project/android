package com.sunshine.android.ui.feature.home

import com.sunshine.android.R
import com.sunshine.android.util.getString

enum class TutorialEvent {
    SHOW_PROFILE, SHOW_DAYS, SHOW_QUEST, SHOW_DIARY, SHOW_NOTI,
}

data class TutorialDialog(
    val content: String, var event: TutorialEvent? = null
)

val tutorialDialogResources = listOf(
    TutorialDialog(
        content = getString(R.string.tutorial_content_1),
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_2),
        event = TutorialEvent.SHOW_PROFILE,
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_3),
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_4),
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_5),
        event = TutorialEvent.SHOW_DAYS,
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_6),
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_7),
        event = TutorialEvent.SHOW_QUEST,
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_8),
        event = TutorialEvent.SHOW_QUEST,
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_9),
        event = TutorialEvent.SHOW_DIARY,
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_10),
        event = TutorialEvent.SHOW_DIARY,
    ),
    TutorialDialog(
        content = getString(R.string.tutorial_content_11),
        event = TutorialEvent.SHOW_NOTI,
    ),
)
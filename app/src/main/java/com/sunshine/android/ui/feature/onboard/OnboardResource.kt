package com.sunshine.android.ui.feature.onboard

import com.sunshine.android.R
import com.sunshine.android.util.getString

enum class OnboardEvent {
    ONBOARD_NAME, ONBOARD_GENDER, ONBOARD_WARNING, ONBOARD_STAT, ONBOARD_REGISTER, ONBOARD_FINISH
}

data class OnboardDialog(
    val content: String,
    val answer: String,
    var isHostWithLantern: Boolean = false,
    var event: OnboardEvent? = null
)

val onboardDialogResources = listOf(
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_1),
        answer = getString(R.string.onboard_dialog_answer_1),
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_2),
        answer = getString(R.string.onboard_dialog_answer_2),
        event = OnboardEvent.ONBOARD_NAME
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_3),
        answer = getString(R.string.onboard_dialog_answer_3),
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_4),
        answer = getString(R.string.onboard_dialog_answer_4),
        event = OnboardEvent.ONBOARD_GENDER
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_5),
        answer = getString(R.string.onboard_dialog_answer_5),
        isHostWithLantern = true
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_6),
        answer = getString(R.string.onboard_dialog_answer_6),
        isHostWithLantern = true
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_7),
        answer = getString(R.string.onboard_dialog_answer_7),
        isHostWithLantern = true
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_8),
        answer = getString(R.string.onboard_dialog_answer_8),
        isHostWithLantern = true
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_9),
        answer = getString(R.string.onboard_dialog_answer_9),
        isHostWithLantern = true
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_10),
        answer = getString(R.string.onboard_dialog_answer_10),
        isHostWithLantern = true,
        event = OnboardEvent.ONBOARD_WARNING
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_11),
        answer = getString(R.string.onboard_dialog_answer_11),
        isHostWithLantern = true
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_12),
        answer = getString(R.string.onboard_dialog_answer_12),
        isHostWithLantern = true,
        event = OnboardEvent.ONBOARD_STAT
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_13),
        answer = getString(R.string.onboard_dialog_answer_13),
        isHostWithLantern = true,
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_14),
        answer = getString(R.string.onboard_dialog_answer_14),
        isHostWithLantern = true,
    ),
    OnboardDialog(
        content = getString(R.string.onboard_dialog_content_15),
        answer = getString(R.string.onboard_dialog_answer_15),
        isHostWithLantern = true,
        event = OnboardEvent.ONBOARD_REGISTER
    ),
)

val onboardWarningResources = listOf(
    getString(R.string.onboard_warning_content_1),
    getString(R.string.onboard_warning_content_2),
    getString(R.string.onboard_warning_content_3),
    getString(R.string.onboard_warning_content_4),
)

val onboardStatResources = listOf(
    getString(R.string.onboard_stat_content_1),
    getString(R.string.onboard_stat_content_2),
    getString(R.string.onboard_stat_content_3),
    getString(R.string.onboard_stat_content_4),
)
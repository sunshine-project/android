package com.sunshine.android.ui.feature.quest.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sunshine.android.ui.feature.quest.QuestRoute
import java.net.URLDecoder
import java.net.URLEncoder

private const val QUEST_ROUTE = "quest_route"

private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

internal const val QUEST_ID_ARG = "questId"

internal class QuestArgs(val questId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        URLDecoder.decode(
            checkNotNull(savedStateHandle[QUEST_ID_ARG]), URL_CHARACTER_ENCODING
        )
    )
}

fun NavController.navigateToQuest(questId: Int) {
    val encodedId = URLEncoder.encode(questId.toString(), URL_CHARACTER_ENCODING)
    navigate("$QUEST_ROUTE/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.questScreen(onFinish: () -> Unit) {
    composable(
        route = "$QUEST_ROUTE/{$QUEST_ID_ARG}",
        arguments = listOf(
            navArgument(QUEST_ID_ARG) { type = NavType.StringType },
        ),
    ) {
        QuestRoute(onFinish = onFinish)
    }
}

package com.sunshine.android.ui.feature.quest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sunshine.android.ui.feature.quest.QuestRoute

const val QUEST_ROUTE = "quest_route"
fun NavController.navigateToQuest(navOptions: NavOptions) = navigate(QUEST_ROUTE, navOptions)

fun NavGraphBuilder.questScreen() {
    composable(
        route = QUEST_ROUTE,
    ) {
        QuestRoute()
    }
}

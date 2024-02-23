package com.sunshine.android.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sunshine.android.ui.common.SunAppState
import com.sunshine.android.ui.feature.ending.navigation.endingScreen
import com.sunshine.android.ui.feature.ending.navigation.navigateToEnding
import com.sunshine.android.ui.feature.home.navigation.homeGraph
import com.sunshine.android.ui.feature.home.navigation.navigateToHomeGraph
import com.sunshine.android.ui.feature.onboard.navigation.navigateToOnboard
import com.sunshine.android.ui.feature.onboard.navigation.onboardScreen
import com.sunshine.android.ui.feature.quest.navigation.navigateToQuest
import com.sunshine.android.ui.feature.quest.navigation.questScreen
import com.sunshine.android.ui.feature.start.modeselect.navigation.modeSelectScreen
import com.sunshine.android.ui.feature.start.modeselect.navigation.navigateToModeSelect
import com.sunshine.android.ui.feature.start.navigation.navigateToStartGraph
import com.sunshine.android.ui.feature.start.navigation.startGraph
import com.sunshine.android.ui.feature.story.navigation.storyScreen

@Composable
fun SunNavHost(
    appState: SunAppState,
    modifier: Modifier = Modifier,
    startDestination: String,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        startGraph(
            onModeSelect = navController::navigateToModeSelect,
            onHome = navController::navigateToHomeGraph,
            nestedGraphs = {
                modeSelectScreen(
                    onNormalModeClick = navController::navigateToOnboard,
                    onFreeModeClick = { },
                )
            },
        )
        storyScreen(onFinish = navController::navigateToStartGraph)
        onboardScreen(onFinish = navController::navigateToHomeGraph)
        homeGraph(onQuestClick = navController::navigateToQuest,
            onLogout = navController::navigateToStartGraph,
            onEnding = navController::navigateToEnding,
            nestedGraphs = {
                questScreen(onFinish = navController::popBackStack)
            })
        endingScreen(onFinish = navController::navigateToStartGraph)
    }
}

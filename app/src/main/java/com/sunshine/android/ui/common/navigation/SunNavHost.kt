package com.sunshine.android.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sunshine.android.ui.common.SunAppState
import com.sunshine.android.ui.feature.home.navigation.homeScreen
import com.sunshine.android.ui.feature.home.navigation.navigateToHome
import com.sunshine.android.ui.feature.onboard.navigation.navigateToOnboard
import com.sunshine.android.ui.feature.onboard.navigation.onboardScreen
import com.sunshine.android.ui.feature.start.modeselect.navigation.modeSelectScreen
import com.sunshine.android.ui.feature.start.modeselect.navigation.navigateToModeSelect
import com.sunshine.android.ui.feature.start.navigation.navigateToStartGraph
import com.sunshine.android.ui.feature.start.navigation.startGraph
import com.sunshine.android.ui.feature.story.navigation.STORY_ROUTE
import com.sunshine.android.ui.feature.story.navigation.storyScreen

@Composable
fun SunNavHost(
    appState: SunAppState,
    modifier: Modifier = Modifier,
    startDestination: String = STORY_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        startGraph(
            onScreenClick = navController::navigateToModeSelect,
            nestedGraphs = {
                modeSelectScreen(
                    onNormalModeClick = navController::navigateToOnboard,
                    onFreeModeClick = { },
                )
            },
        )
        storyScreen(onFinish = navController::navigateToStartGraph)
        onboardScreen(onFinish = navController::navigateToHome)
        homeScreen()
    }
}

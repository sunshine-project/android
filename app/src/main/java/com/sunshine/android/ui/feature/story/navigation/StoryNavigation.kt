package com.sunshine.android.ui.feature.story.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sunshine.android.ui.feature.story.StoryRoute

const val STORY_ROUTE = "story_route"
fun NavController.navigateToStory(navOptions: NavOptions) = navigate(STORY_ROUTE, navOptions)

fun NavGraphBuilder.storyScreen() {
    composable(
        route = STORY_ROUTE,
    ) {
        StoryRoute()
    }
}

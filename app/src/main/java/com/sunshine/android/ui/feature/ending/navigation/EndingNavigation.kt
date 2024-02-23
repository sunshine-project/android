package com.sunshine.android.ui.feature.ending.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sunshine.android.ui.feature.ending.EndingRoute

const val ENDING_ROUTE = "ending_route"
fun NavController.navigateToEnding() = navigate(ENDING_ROUTE)

fun NavGraphBuilder.endingScreen(onFinish: () -> Unit) {
    composable(
        route = ENDING_ROUTE,
    ) {
        EndingRoute(onFinish = onFinish)
    }
}

package com.sunshine.android.ui.feature.onboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sunshine.android.ui.feature.onboard.OnboardRoute

const val ONBOARD_ROUTE = "onboard_route"
fun NavController.navigateToOnboard(navOptions: NavOptions) = navigate(ONBOARD_ROUTE, navOptions)

fun NavGraphBuilder.onboardScreen(onFinish: () -> Unit) {
    composable(
        route = ONBOARD_ROUTE,
    ) {
        OnboardRoute(onFinish)
    }
}

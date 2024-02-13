package com.sunshine.android.ui.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sunshine.android.ui.feature.home.HomeRoute

const val HOME_ROUTE = "home_route"
fun NavController.navigateToHome() = navigate(HOME_ROUTE)

fun NavGraphBuilder.homeScreen() {
    composable(
        route = HOME_ROUTE,
    ) {
        HomeRoute()
    }
}

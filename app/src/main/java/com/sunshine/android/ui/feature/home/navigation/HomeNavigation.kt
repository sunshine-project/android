package com.sunshine.android.ui.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sunshine.android.ui.feature.home.HomeRoute

const val HOME_GRAPH_ROUTE_PATTERN = "home_graph"
const val HOME_ROUTE = "home_route"
fun NavController.navigateToHomeGraph() = navigate(HOME_GRAPH_ROUTE_PATTERN)

fun NavGraphBuilder.homeGraph(
    onQuestClick: (Int) -> Unit,
    onLogout: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = HOME_GRAPH_ROUTE_PATTERN,
        startDestination = HOME_ROUTE,
    ) {
        composable(
            route = HOME_ROUTE,
        ) {
            HomeRoute(onQuestClick = onQuestClick, onLogout = onLogout)
        }
        nestedGraphs()
    }
}

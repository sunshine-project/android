package com.sunshine.android.ui.feature.start.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sunshine.android.ui.feature.start.StartRoute

const val START_GRAPH_ROUTE_PATTERN = "start_graph"
const val START_ROUTE = "start_route"
fun NavController.navigateToStartGraph() =
    navigate(START_GRAPH_ROUTE_PATTERN)

fun NavGraphBuilder.startGraph(
    onModeSelect: () -> Unit,
    onHome: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = START_GRAPH_ROUTE_PATTERN,
        startDestination = START_ROUTE,
    ) {
        composable(
            route = START_ROUTE,
        ) {
            StartRoute(onModeSelect = onModeSelect, onHome = onHome)
        }
        nestedGraphs()
    }
}

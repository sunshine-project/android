package com.sunshine.android.ui.feature.start.modeselect.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sunshine.android.ui.feature.start.modeselect.ModeSelectRoute

const val MODE_SELECT_ROUTE = "mode_select_route"
fun NavController.navigateToModeSelect() = navigate(MODE_SELECT_ROUTE)

fun NavGraphBuilder.modeSelectScreen(
    onNormalModeClick: () -> Unit,
    onFreeModeClick: () -> Unit,
) {
    composable(
        route = MODE_SELECT_ROUTE,
    ) {
        ModeSelectRoute(
            onNormalModeClick = onNormalModeClick,
            onFreeModeClick = onFreeModeClick
        )
    }
}

package com.sunshine.android.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberSunAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): SunAppState {
    return remember(
        navController,
        coroutineScope,
    ) {
        SunAppState(
            navController,
            coroutineScope,
        )
    }
}

@Stable
class SunAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {}
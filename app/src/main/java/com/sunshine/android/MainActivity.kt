package com.sunshine.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sunshine.android.ui.common.SunApp
import com.sunshine.android.ui.feature.home.navigation.HOME_GRAPH_ROUTE_PATTERN
import com.sunshine.android.ui.feature.home.navigation.HOME_ROUTE
import com.sunshine.android.ui.feature.story.navigation.STORY_ROUTE
import com.sunshine.android.ui.theme.SunTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSplashScreen()

        setContent {
            SunTheme {
                val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()
                startDestination?.let {
                    SunApp(startDestination = HOME_GRAPH_ROUTE_PATTERN) //TODO change to it
                }
            }
        }
    }

    private fun setupSplashScreen() {
        var keepSplashScreenOn = true
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect {
                    keepSplashScreenOn = it
                }
            }
        }

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreenOn
        }
    }
}


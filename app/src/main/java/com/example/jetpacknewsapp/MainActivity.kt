package com.example.jetpacknewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.jetpacknewsapp.domain.usecases.appentry.AppEntryUseCases
import com.example.jetpacknewsapp.ui.theme.JetpackNewsAppTheme
import com.example.jetpacknewsapp.view.navgraph.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    @Inject
    lateinit var appEntryUseCases : AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        setContent {
            JetpackNewsAppTheme {

                val isSystemInDarkMode = isSystemInDarkTheme()
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        detectDarkMode = { isSystemInDarkMode },
                        darkScrim = android.graphics.Color.BLACK
                    )
                )

                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination)
                }
            }
        }
    }
}
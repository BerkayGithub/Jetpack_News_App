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
import androidx.lifecycle.lifecycleScope
import com.example.jetpacknewsapp.data.local.NewsDAO
import com.example.jetpacknewsapp.domain.model.Article
import com.example.jetpacknewsapp.domain.model.Source
import com.example.jetpacknewsapp.domain.usecases.appentry.AppEntryUseCases
import com.example.jetpacknewsapp.ui.theme.JetpackNewsAppTheme
import com.example.jetpacknewsapp.view.navgraph.NavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    @Inject
    lateinit var newsDAO: NewsDAO
    @Inject
    lateinit var appEntryUseCases : AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
            newsDAO.upsert(
                article = Article(
                    author = "",
                    content = "As wildfires continue to have an impact across the world, BBC Tech Now’s Alasdair Keane travels to Canada to find out how new developments in technology are bringing hope of earlier detection and prevention.",
                    description = "How close are we to predicting wildfires with tech?",
                    publishedAt = "2 hours ago",
                    source = Source(
                        id = "",
                        name = "bbc",
                    ),
                    title = "How close are we to predicting wildfires with tech?",
                    url = "https://bbc.com/reel/video/p0m3mn2t/how-close-are-we-to-predicting-wildfires-with-tech- ",
                    urlToImage = "https://ichef.bbci.co.uk/images/ic/1920x1080/p0m4cy8t.jpg.webp"
                )
            )
        }

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
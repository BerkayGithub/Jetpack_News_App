package com.example.jetpacknewsapp

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.jetpacknewsapp.ui.theme.JetpackNewsAppTheme
import com.example.jetpacknewsapp.view.onboarding.components.OnboardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setContent {
            JetpackNewsAppTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
                    OnboardingScreen()
                }
            }
        }
    }
}
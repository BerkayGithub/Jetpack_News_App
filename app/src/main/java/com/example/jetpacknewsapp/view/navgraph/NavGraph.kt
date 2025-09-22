package com.example.jetpacknewsapp.view.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.jetpacknewsapp.view.home.HomeScreen
import com.example.jetpacknewsapp.view.home.HomeViewModel
import com.example.jetpacknewsapp.view.onboarding.OnboardingViewModel
import com.example.jetpacknewsapp.view.onboarding.components.OnboardingScreen

@Composable
fun NavGraph(
    startDestination: String
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route
        ){
            composable(
                route = Route.OnboardingScreen.route
            ){
                val viewModel : OnboardingViewModel = hiltViewModel()
                OnboardingScreen (
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            composable(
                route = Route.NewsNavigatorScreen.route
            ){
                val viewModel : HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles) { }
            }
        }
    }
}
package ui.screen.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.navigation.HomeNavigation

@Composable
fun HomeNavConfiguration() {
    val navigator = rememberNavController()

    NavHost(
        startDestination = HomeNavigation.Home.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable(route = HomeNavigation.Home.route) {
            HomeScreen()
        }
    }
}
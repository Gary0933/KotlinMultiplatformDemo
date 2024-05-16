package ui.screen.main.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.navigation.FavoriteNavigation

@Composable
fun FavoriteNavConfiguration() {
    val navigator = rememberNavController()

    NavHost(
        startDestination = FavoriteNavigation.Favorite.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = FavoriteNavigation.Favorite.route) {
            FavoriteScreen()
        }
    }
}
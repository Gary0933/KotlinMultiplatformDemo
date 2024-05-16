package ui.screen.main.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.navigation.ProfileNavigation

@Composable
fun ProfileNavConfiguration() {
    val navigator = rememberNavController()

    NavHost(
        startDestination = ProfileNavigation.Profile.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = ProfileNavigation.Profile.route) {
            ProfileScreen()
        }
    }
}
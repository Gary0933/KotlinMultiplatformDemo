package ui.screen.main.cart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.navigation.CartNavigation

@Composable
fun CartNavConfiguration() {
    val navigator = rememberNavController()

    NavHost(
        startDestination = CartNavigation.Cart.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = CartNavigation.Cart.route) {
            CartScreen()
        }
    }
}
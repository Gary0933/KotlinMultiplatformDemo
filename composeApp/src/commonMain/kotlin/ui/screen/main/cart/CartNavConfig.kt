package ui.screen.main.cart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import ui.navigation.CartNavigation
import ui.screen.main.MainViewModel
import ui.screen.main.cart.view_model.CartViewModel

@Composable
fun CartNavConfiguration(
    mainViewModel: MainViewModel,
    cartViewModel: CartViewModel = koinInject()
) {
    val navigator = rememberNavController()
    NavHost(
        startDestination = CartNavigation.Cart.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = CartNavigation.Cart.route) {
            mainViewModel.hideBottomBar(false)
            CartScreen(
                cartViewModel = cartViewModel,
                navigateToAddToCart = {
                    navigator.navigate(CartNavigation.AddToCart.route)
                }
            )
        }

        composable(route = CartNavigation.AddToCart.route) {
            mainViewModel.hideBottomBar(true)
            AddToCartScreen(
                cartViewModel = cartViewModel,
                navigateToCart = {
                    navigator.navigate(CartNavigation.Cart.route)
                },
                navigateToScanner = {
                    navigator.navigate(CartNavigation.CartScanner.route)
                },
                backOnTopBar = {
                    navigator.popBackStack()
                }
            )
        }

        composable(route = CartNavigation.CartScanner.route) {
            mainViewModel.hideBottomBar(true)
            CartScannerScreen(
                cartViewModel = cartViewModel,
                navigateToAddToCart = {
                    navigator.navigate(CartNavigation.AddToCart.route)
                },
            )
        }
    }
}
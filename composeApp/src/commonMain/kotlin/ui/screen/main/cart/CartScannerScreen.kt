package ui.screen.main.cart

import androidx.compose.runtime.Composable
import ui.components.BasicScreenUI
import ui.screen.main.cart.view_model.CartViewModel

@Composable
fun CartScannerScreen(
    cartViewModel: CartViewModel,
    navigateToAddToCart: () -> Unit
) {

    BasicScreenUI(
        showTopBar = false,
    ) {

    }
}
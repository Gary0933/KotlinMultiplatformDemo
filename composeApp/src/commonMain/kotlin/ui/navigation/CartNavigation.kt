package ui.navigation

import androidx.navigation.NamedNavArgument

sealed class CartNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Cart : CartNavigation(route = "Cart", arguments = emptyList())
    data object AddToCart : CartNavigation(route = "AddToCart", arguments = emptyList())
    data object CartScanner : CartNavigation(route = "CartScanner", arguments = emptyList())

}
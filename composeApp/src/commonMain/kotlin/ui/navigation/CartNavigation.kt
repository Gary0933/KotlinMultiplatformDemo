package ui.navigation

import androidx.navigation.NamedNavArgument

sealed class CartNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Cart : CartNavigation(route = "Cart", arguments = emptyList())
}
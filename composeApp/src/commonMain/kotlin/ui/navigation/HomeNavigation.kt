package ui.navigation

import androidx.navigation.NamedNavArgument

sealed class HomeNavigation(val route: String, val arguments: List<NamedNavArgument>) {
    data object Home : HomeNavigation(route = "Home", arguments = emptyList())
}
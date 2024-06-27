package ui.navigation

import androidx.navigation.NamedNavArgument

sealed class AppNavigation(val route: String, val arguments: List<NamedNavArgument>) {
    data object Login : AppNavigation(route = "Login", arguments = emptyList())
    data object Register : AppNavigation(route = "Register", arguments = emptyList())
    data object Main : AppNavigation(route = "Main", arguments = emptyList())

}
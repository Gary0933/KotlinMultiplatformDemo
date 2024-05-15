package ui.navigation

sealed class AppNavigation(
    val route: String
) {
    data object Login : AppNavigation(route = "Login")

    data object Main : AppNavigation(route = "Main")


}
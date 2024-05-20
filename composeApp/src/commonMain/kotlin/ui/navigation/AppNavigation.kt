package ui.navigation

sealed class AppNavigation(
    val route: String
) {
    data object Login : AppNavigation(route = "Login")

    data object Register : AppNavigation(route = "Register")

    data object Main : AppNavigation(route = "Main")

}
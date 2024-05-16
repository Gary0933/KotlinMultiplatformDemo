package ui.navigation

import androidx.navigation.NamedNavArgument

sealed class ProfileNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Profile : ProfileNavigation(route = "Profile", arguments = emptyList())
}
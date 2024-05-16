package ui.navigation

import androidx.navigation.NamedNavArgument

sealed class FavoriteNavigation(
    val route: String, val arguments: List<NamedNavArgument>
) {
    data object Favorite : FavoriteNavigation(route = "Favorite", arguments = emptyList())
}
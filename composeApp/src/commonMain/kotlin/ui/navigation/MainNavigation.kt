package ui.navigation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.cart
import kotlinmultiplatformdemo.composeapp.generated.resources.cart_border
import kotlinmultiplatformdemo.composeapp.generated.resources.heart2
import kotlinmultiplatformdemo.composeapp.generated.resources.heart_border2
import kotlinmultiplatformdemo.composeapp.generated.resources.home
import kotlinmultiplatformdemo.composeapp.generated.resources.home_border
import kotlinmultiplatformdemo.composeapp.generated.resources.profile
import kotlinmultiplatformdemo.composeapp.generated.resources.profile_border


@OptIn(ExperimentalResourceApi::class)
sealed class MainNavigation (
    val route: String,
    val title: String,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
) {

   data object Home : MainNavigation(
        route = "Home", title = "Home",
        selectedIcon = Res.drawable.home,
        unSelectedIcon = Res.drawable.home_border
    )

   data object Favorite : MainNavigation(
        route = "Favorite", title = "Favorite",
        selectedIcon = Res.drawable.heart2,
        unSelectedIcon = Res.drawable.heart_border2
    )

   data object Cart : MainNavigation(
        route = "Cart", title = "Cart",
        selectedIcon = Res.drawable.cart,
        unSelectedIcon = Res.drawable.cart_border
    )

   data object Profile : MainNavigation(
        route = "Profile", title = "Profile",
        selectedIcon = Res.drawable.profile,
        unSelectedIcon = Res.drawable.profile_border
    )


}


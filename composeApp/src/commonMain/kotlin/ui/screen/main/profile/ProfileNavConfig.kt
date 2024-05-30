package ui.screen.main.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.navigation.ProfileNavigation
import ui.screen.main.profile.my_orders.ProfileMyOrdersScreen
import ui.screen.main.profile.settings.ProfileSettingsScreen
import ui.screen.main.profile.show_users.ProfileShowUsersScreen

@Composable
fun ProfileNavConfiguration(logout: () -> Unit) {
    val navigator = rememberNavController()

    NavHost(
        startDestination = ProfileNavigation.Profile.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = ProfileNavigation.Profile.route) {
            ProfileScreen(
                navigateToMyOrders = {
                    navigator.navigate(ProfileNavigation.MyOrders.route)
                },
                navigateToShowUsers = {
                    navigator.navigate(ProfileNavigation.ShowUsers.route)
                },
                // 实现一个跳转到settings页面的行为，并作为参数传递给Profile页面，在Profile页面里可以使用这个函数来实现跳转
                navigateToSettings = {
                    navigator.navigate(ProfileNavigation.Settings.route)
                },
            )
        }

        composable(route = ProfileNavigation.MyOrders.route) {
            ProfileMyOrdersScreen(
                backOnTopBar = {
                    navigator.popBackStack()
                }
            )
        }

        composable(route = ProfileNavigation.ShowUsers.route) {
            ProfileShowUsersScreen(
                backOnTopBar = {
                    navigator.popBackStack()
                }
            )
        }

        // 定义一个settings页面的路由
        composable(route = ProfileNavigation.Settings.route) {
            ProfileSettingsScreen(
                backOnTopBar = {
                    navigator.popBackStack()
                },
                logout = logout
            )
        }
    }
}
package ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import common.ChangeStatusBarColors
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import ui.navigation.MainNavigation
import ui.screen.main.cart.CartNavConfiguration
import ui.screen.main.favorite.FavoriteNavConfiguration
import ui.screen.main.home.HomeNavConfiguration
import ui.screen.main.profile.ProfileNavConfiguration
import ui.theme.DefaultNavigationBarItemTheme

@Composable
fun MainNavConfiguration(
    mainViewModel: MainViewModel = koinInject(),
    logout: () -> Unit,
) {
    val navBottomBarController = rememberNavController()
    val isBottomBarVisible by mainViewModel.isBottomBarVisible.collectAsState()

    ChangeStatusBarColors(Color.White) // 修改样式不支持跨平台，需要在各自的平台里实现(composeApp里各种模块的common文件夹下)

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigationUI(navController = navBottomBarController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                startDestination = MainNavigation.Home.route,
                navController = navBottomBarController,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = MainNavigation.Home.route) {
                    mainViewModel.hideBottomBar(false)
                    HomeNavConfiguration()
                }
                composable(route = MainNavigation.Favorite.route) {
                    mainViewModel.hideBottomBar(false)
                    FavoriteNavConfiguration()
                }
                composable(route = MainNavigation.Cart.route) {
                    mainViewModel.hideBottomBar(false)
                    CartNavConfiguration(
                        mainViewModel = mainViewModel
                    )
                }
                composable(route = MainNavigation.Profile.route) {
                    mainViewModel.hideBottomBar(false)
                    ProfileNavConfiguration(
                        logout = logout
                    )
                }
            }
        }

    }
}

@Composable
fun BottomNavigationUI(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 8.dp,
        )
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.background,
            tonalElevation = 8.dp,
        ) {

            val items = listOf(
                MainNavigation.Home,
                MainNavigation.Favorite,
                MainNavigation.Cart,
                MainNavigation.Profile,
            )
            items.forEach {
                NavigationBarItem(
                    label = { Text(text = it.title) },
                    colors = DefaultNavigationBarItemTheme(),
                    selected = it.route == currentRoute,
                    icon = {
                        Icon(
                            painterResource(if (it.route == currentRoute) it.selectedIcon else it.unSelectedIcon),
                            it.title
                        )
                    },
                    onClick = {
                        if (currentRoute != it.route) {
                            navController.navigate(it.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}
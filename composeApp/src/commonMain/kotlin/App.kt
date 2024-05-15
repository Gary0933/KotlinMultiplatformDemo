import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.navigation.AppNavigation
import ui.screen.login.LoginScreen
import ui.screen.main.MainScreen
import ui.theme.DemoTheme

@Composable
@Preview
fun App() {
    DemoTheme{

        val navController = rememberNavController()

        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = AppNavigation.Login.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = AppNavigation.Login.route) {
                    LoginScreen(navController)
                }
                composable(route = AppNavigation.Main.route) {
                    MainScreen()
                }
            }
        }
    }
}
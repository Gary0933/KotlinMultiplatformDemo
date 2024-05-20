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
import ui.screen.login.register.RegisterScreen
import ui.screen.main.MainNavConfiguration
import ui.theme.DemoTheme

@Composable
@Preview
fun App() {
    DemoTheme{

        val navigator = rememberNavController()

        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navigator,
                startDestination = AppNavigation.Login.route, // 定义初始页面是登录页面
                modifier = Modifier.fillMaxSize()
            ) {
                // 定义登录页面的渲染方法
                composable(route = AppNavigation.Login.route) {
                    LoginScreen(
                        navigateToMain = {
                            navigator.navigate(AppNavigation.Main.route)
                        },
                        navigateToRegister = {
                            navigator.navigate(AppNavigation.Register.route)
                        }
                    )
                }
                composable(route = AppNavigation.Register.route) {
                    RegisterScreen(
                        navigateToLogin = {
                            navigator.popBackStack()
                        }
                    )
                }
                composable(route = AppNavigation.Main.route) {
                    // 跳转到主页的导航route配置
                    MainNavConfiguration(
                        // 实现MainNavConfiguration里的参数，参数是一个名为logout的函数，当后续使用这个logout函数后会跳转回登录页面
                        logout = {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Login.route)
                        }
                    )
                }
            }
        }
    }
}
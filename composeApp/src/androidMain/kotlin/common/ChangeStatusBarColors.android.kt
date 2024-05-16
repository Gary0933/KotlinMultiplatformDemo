package common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController // 需要引入依赖implementation(libs.system.ui.controller)

@Composable
actual fun ChangeStatusBarColors(statusBarColor: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(statusBarColor)
}
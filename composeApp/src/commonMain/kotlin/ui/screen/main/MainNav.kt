package ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import common.ChangeStatusBarColors

@Composable
fun MainNavigation() {
    val navBottomBarController = rememberNavController()

    ChangeStatusBarColors(Color.White) // 修改样式不支持跨平台，需要在各自的平台里实现(composeApp里各种模块的common文件夹下)

}
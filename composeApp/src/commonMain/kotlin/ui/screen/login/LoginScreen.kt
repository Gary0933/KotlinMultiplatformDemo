package ui.screen.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.email
import kotlinmultiplatformdemo.composeapp.generated.resources.password
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_in
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import ui.components.Spacer_32dp
import ui.components.Spacer_4dp
import ui.components.Spacer_8dp
import ui.navigation.AppNavigation
import ui.theme.DefaultButtonTheme
import ui.theme.DefaultTextFieldTheme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    var isUsernameError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(Res.string.sign_in),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer_32dp()


        Column(
            horizontalAlignment = Alignment.Start
        ) {
            // 登录帐号 lable
            Text(stringResource(Res.string.email))
            Spacer_4dp()
            // 登录输入框
            TextField(
                isError = isUsernameError,
                value = "",
                onValueChange = {
                    if (it.length < 32) {
                        isUsernameError = it.isEmpty()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                ),
            )

            Spacer_8dp()

            // 密码 lable
            Text(stringResource(Res.string.password))
            Spacer_4dp()
            // 密码输入框
            TextField(
                isError = isPasswordError,
                value = "",
                onValueChange = {
                    isPasswordError = it.length < 8
                },
                modifier = Modifier.fillMaxWidth(),
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                readOnly = false,
            )
        }

        Spacer_32dp()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 点击登录按钮
            Button(
                enabled = true, // 按钮是否可用
                elevation = ButtonDefaults.buttonElevation(), // 设置按钮阴影
                shape = MaterialTheme.shapes.extraLarge,
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.primary
                ),
                colors = DefaultButtonTheme(),
                contentPadding = ButtonDefaults.ContentPadding, // 按钮内的内容跟按钮边框的距离
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = {
                    navController.navigate(AppNavigation.Main.route) // 跳转到主页面
                }
            ) {
                Text(
                    text = stringResource(Res.string.sign_in),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Spacer(Modifier.height(32.dp))
        }
    }



}
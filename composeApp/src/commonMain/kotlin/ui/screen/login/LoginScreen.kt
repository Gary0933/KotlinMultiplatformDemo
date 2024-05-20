package ui.screen.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.dont_have_an_account
import kotlinmultiplatformdemo.composeapp.generated.resources.email
import kotlinmultiplatformdemo.composeapp.generated.resources.ic_password_hide
import kotlinmultiplatformdemo.composeapp.generated.resources.ic_password_show
import kotlinmultiplatformdemo.composeapp.generated.resources.password
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_in
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.components.Spacer_32dp
import ui.components.Spacer_4dp
import ui.components.Spacer_8dp
import ui.theme.DefaultButtonTheme
import ui.theme.DefaultTextFieldTheme
import ui.theme.IconColorGrey

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(
    navigateToMain: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    val emailTextState = remember { mutableStateOf("") }
    val passwordTextState = remember { mutableStateOf("") }
    val isPasswordVisible = remember { mutableStateOf(false) }

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
                value = emailTextState.value,
                onValueChange = {
                    emailTextState.value = it
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
                value = passwordTextState.value,
                onValueChange = {
                    passwordTextState.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                colors = DefaultTextFieldTheme(),
                shape = MaterialTheme.shapes.small,
                readOnly = false,
                trailingIcon = { // 在输入框末尾添加一个图标
                    IconButton(onClick = {
                        isPasswordVisible.value = !isPasswordVisible.value
                    }) {
                        when (isPasswordVisible.value) {
                            true -> Icon(
                                painter = painterResource(Res.drawable.ic_password_hide),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                            )
                            false -> Icon(
                                painter = painterResource(Res.drawable.ic_password_show),
                                contentDescription = null,
                                tint = IconColorGrey,
                            )
                        }
                    }
                },
                visualTransformation = when (isPasswordVisible.value) { // 根据isPasswordVisible的状态值来决定是否显示密码
                    true -> VisualTransformation.None // 直接显示密码
                    false -> PasswordVisualTransformation() // 显示***
                },
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
                    navigateToMain() // 跳转到主页面
                }
            ) {
                Text(
                    text = stringResource(Res.string.sign_in),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Spacer(Modifier.height(32.dp))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(Res.string.dont_have_an_account),
            )
            Spacer_4dp()
            Text(
                modifier = Modifier.clickable {
                    navigateToRegister()
                },
                text = stringResource(Res.string.sign_up),
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            )
        }

    }
}
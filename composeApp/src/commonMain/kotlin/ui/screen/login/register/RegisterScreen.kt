package ui.screen.login.register

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import database.entity.UserInfoModel
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.agree_with
import kotlinmultiplatformdemo.composeapp.generated.resources.already_have_account
import kotlinmultiplatformdemo.composeapp.generated.resources.create_account
import kotlinmultiplatformdemo.composeapp.generated.resources.email
import kotlinmultiplatformdemo.composeapp.generated.resources.ic_password_hide
import kotlinmultiplatformdemo.composeapp.generated.resources.ic_password_show
import kotlinmultiplatformdemo.composeapp.generated.resources.name
import kotlinmultiplatformdemo.composeapp.generated.resources.password
import kotlinmultiplatformdemo.composeapp.generated.resources.register_title
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_in
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_up
import kotlinmultiplatformdemo.composeapp.generated.resources.terms_condition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.components.BasicScreenUI
import ui.components.Spacer_16dp
import ui.components.Spacer_32dp
import ui.components.Spacer_4dp
import ui.components.Spacer_8dp
import ui.screen.login.register.view_model.RegisterViewModel
import ui.theme.DefaultButtonTheme
import ui.theme.DefaultCheckBoxTheme
import ui.theme.DefaultTextFieldTheme
import ui.theme.IconColorGrey

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RegisterScreen(
    navigateToLogin: () -> Unit
) {
    val nameTextState = remember { mutableStateOf("") }
    val emailTextState = remember { mutableStateOf("") }
    val passwordTextState = remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    val isPasswordVisible = remember { mutableStateOf(false) }

    val registerViewModel: RegisterViewModel = viewModel { RegisterViewModel() }

    BasicScreenUI(
        showTopBar = false,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // title
            Text(
                stringResource(Res.string.create_account),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer_16dp()
            Text(
                stringResource(Res.string.register_title),
                style = MaterialTheme.typography.labelMedium
            )
            Spacer_32dp()

            // 填写部分
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                // 姓名
                Text(stringResource(Res.string.name))
                Spacer_4dp()
                TextField(
                    value = nameTextState.value,
                    onValueChange = {
                        nameTextState.value = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.small,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text,
                    ),
                )
                Spacer_8dp()

                // 邮箱
                Text(stringResource(Res.string.email))
                Spacer_4dp()
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

                // 密码
                Text(stringResource(Res.string.password))
                Spacer_4dp()
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.small,
                    readOnly = false,
                    value = passwordTextState.value,
                    onValueChange = {
                        passwordTextState.value = it
                    },
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
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password,
                    )
                )
            }
            Spacer_8dp()

            // 免责声明
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                    },
                    colors = DefaultCheckBoxTheme()
                )
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(Res.string.agree_with))
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(stringResource(Res.string.terms_condition))
                        }
                        append(".")
                    },
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Spacer_32dp()

            // 提交按钮
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        0.dp
                    ),
                    shape = MaterialTheme.shapes.extraLarge,
                    border = null,
                    colors = DefaultButtonTheme(),
                    contentPadding = ButtonDefaults.ContentPadding,
                    onClick = {
                        registerViewModel.register(
                           UserInfoModel(
                               Id = 0,
                               UserName = nameTextState.value,
                               UserEmail = emailTextState.value,
                               UserPassword = passwordTextState.value,
                               CreateDate = null
                           )
                        )
                        navigateToLogin()
                    },
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.sign_up),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Spacer(Modifier.height(32.dp))
            }

            // 返回登录页面
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.already_have_account),
                )
                Spacer_4dp()
                Text(
                    modifier = Modifier.clickable {
                        navigateToLogin()
                    },
                    text = stringResource(Res.string.sign_in),
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}
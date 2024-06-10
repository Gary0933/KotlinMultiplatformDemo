package ui.screen.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import business.data_state.ManageUiState
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.dont_have_an_account
import kotlinmultiplatformdemo.composeapp.generated.resources.ic_password_hide
import kotlinmultiplatformdemo.composeapp.generated.resources.ic_password_show
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_in
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_up
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.components.BasicScreenUI
import ui.components.Spacer_16dp
import ui.components.Spacer_4dp
import ui.components.Spacer_dp
import ui.components.TextWithLoadingInButton
import ui.screen.login.view_model.LoginViewModel
import ui.theme.DefaultButtonTheme
import ui.theme.DefaultTextFieldTheme
import ui.theme.IconColorGrey
import ui.theme.PrimaryColor
import ui.theme.placeholderGrey

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navigateToMain: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isUsernameError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }
    var showButtonLoading by rememberSaveable { mutableStateOf(false) }

    val uiState: ManageUiState by loginViewModel.uiState.collectAsState()

    LaunchedEffect(uiState.showRegisterSuccessAlert, showButtonLoading) {
        if (uiState.showRegisterSuccessAlert) {
            delay(2000)
            loginViewModel.closeRegisterSuccessAlert()
        }
        if(showButtonLoading) {
            delay(1000)
            navigateToMain()
        }
    }

    BasicScreenUI(
        showTopBar = false,
        uiState = uiState
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(Res.string.sign_in),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall,
                color = PrimaryColor
            )
            Spacer_dp(60.dp)

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                // 登录输入框
                TextField(
                    isError = isUsernameError,
                    value = emailText,
                    onValueChange = {
                        emailText = it
                        isUsernameError = it.trim().isEmpty()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PrimaryColor, MaterialTheme.shapes.medium),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Please enter email",
                            color = placeholderGrey
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person, // 使用Material图标
                            contentDescription = "Account icon", // 内容描述，用于辅助功能
                            modifier = Modifier.size(24.dp) // 可选，设置图标大小
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email,
                    ),
                )
                Spacer_4dp()
                AnimatedVisibility(visible = isUsernameError) {
                    Text(
                        text = "Email cannot be empty",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer_16dp()

                // 密码输入框
                TextField(
                    isError = isPasswordError,
                    value = passwordText,
                    onValueChange = {
                        passwordText = it
                        isPasswordError = it.trim().isEmpty()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PrimaryColor, MaterialTheme.shapes.medium),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.medium,
                    readOnly = false,
                    placeholder = {
                        Text(
                            text = "Please enter password",
                            color = placeholderGrey
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock, // 使用Material图标
                            contentDescription = "Password icon", // 内容描述，用于辅助功能
                            modifier = Modifier.size(24.dp) // 可选，设置图标大小
                        )
                    },
                    trailingIcon = { // 在输入框末尾添加一个图标
                        IconButton(onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }) {
                            when (isPasswordVisible) {
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
                    visualTransformation = when (isPasswordVisible) { // 根据isPasswordVisible的状态值来决定是否显示密码
                        true -> VisualTransformation.None // 直接显示密码
                        false -> PasswordVisualTransformation() // 显示***
                    },
                )
                Spacer_4dp()
                AnimatedVisibility(visible = isPasswordError) {
                    Text(
                        text = "Password cannot be empty",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer_dp(80.dp)

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
                        isUsernameError = emailText.trim().isEmpty()
                        isPasswordError = passwordText.trim().isEmpty()
                        if (!isUsernameError && !isPasswordError) {
                            showButtonLoading = true
                        }
                    }
                ) {
                    TextWithLoadingInButton(
                        showLoading = showButtonLoading
                    ) {
                        Text(
                            text = stringResource(Res.string.sign_in),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(horizontal = 30.dp)
                        )
                    }
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
                        isUsernameError = false
                        isPasswordError = false
                        navigateToRegister()
                    },
                    text = stringResource(Res.string.sign_up),
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            }

        }
    }
}
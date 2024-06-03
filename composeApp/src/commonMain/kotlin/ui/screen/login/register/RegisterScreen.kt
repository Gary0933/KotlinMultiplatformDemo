package ui.screen.login.register

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import database.DbEngine
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
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_in
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_up
import kotlinmultiplatformdemo.composeapp.generated.resources.terms_condition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ui.components.BasicScreenUI
import ui.components.Spacer_32dp
import ui.components.Spacer_4dp
import ui.components.Spacer_50dp
import ui.components.Spacer_8dp
import ui.screen.login.register.view_model.RegisterViewModel
import ui.theme.DefaultButtonTheme
import ui.theme.DefaultCheckBoxTheme
import ui.theme.DefaultTextFieldTheme
import ui.theme.IconColorGrey
import ui.theme.PrimaryColor

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RegisterScreen(
    db: DbEngine = koinInject(), // 创建操作数据的单例实例
    navigateToLogin: () -> Unit
) {
    var nameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var confirmPasswordText by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isUserNameError by rememberSaveable { mutableStateOf(false) }
    var isUserEmailError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordError by rememberSaveable { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(true) }

    val registerViewModel: RegisterViewModel = viewModel { RegisterViewModel(db) }

    BasicScreenUI(
        showTopBar = false,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // title
            Text(
                stringResource(Res.string.create_account),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor
            )
            Spacer_50dp()

            // 填写部分
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                // 姓名
                Text(
                    text = stringResource(Res.string.name),
                    fontSize = 15.sp,
                    color = PrimaryColor
                )
                Spacer_8dp()
                TextField(
                    isError = isUserNameError,
                    value = nameText,
                    onValueChange = {
                        nameText = it
                        isUserNameError = it.trim().isEmpty()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PrimaryColor, MaterialTheme.shapes.small),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.small,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text,
                    ),
                )
                Spacer_4dp()
                AnimatedVisibility(visible = isUserNameError) {
                    Text(
                        text = "User name cannot be empty",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer_8dp()

                // 邮箱
                Text(
                    text = stringResource(Res.string.email),
                    fontSize = 15.sp,
                    color = PrimaryColor
                )
                Spacer_8dp()
                TextField(
                    isError = isUserEmailError,
                    value = emailText,
                    onValueChange = {
                        emailText = it
                        isEmailValid = Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""").matches(it)
                        isUserEmailError = !isEmailValid
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PrimaryColor, MaterialTheme.shapes.small),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.small,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email,
                    ),
                )
                Spacer_4dp()
                AnimatedVisibility(visible = isUserEmailError) {
                    Text(
                        text = "Please validate email format",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer_8dp()

                // 密码
                Text(
                    text = stringResource(Res.string.password),
                    fontSize = 15.sp,
                    color = PrimaryColor
                )
                Spacer_8dp()
                TextField(
                    isError = isPasswordError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PrimaryColor, MaterialTheme.shapes.small),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.small,
                    readOnly = false,
                    value = passwordText,
                    onValueChange = {
                        passwordText = it
                        isPasswordError = it.length < 6 || it.length > 12
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
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password,
                    )
                )
                Spacer_4dp()
                AnimatedVisibility(visible = isPasswordError) {
                    Text(
                        text = "Password must between 6 to 12 digits",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer_8dp()

                // 确认密码
                Text(
                    text = "Confirm Password",
                    fontSize = 15.sp,
                    color = PrimaryColor
                )
                Spacer_8dp()
                TextField(
                    isError = isConfirmPasswordError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PrimaryColor, MaterialTheme.shapes.small),
                    colors = DefaultTextFieldTheme(),
                    shape = MaterialTheme.shapes.small,
                    readOnly = false,
                    value = confirmPasswordText,
                    onValueChange = {
                        confirmPasswordText = it
                        isConfirmPasswordError = confirmPasswordText != passwordText
                    },
                    visualTransformation = when (isPasswordVisible) { // 根据isPasswordVisible的状态值来决定是否显示密码
                        true -> VisualTransformation.None // 直接显示密码
                        false -> PasswordVisualTransformation() // 显示***
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password,
                    )
                )
                Spacer_4dp()
                AnimatedVisibility(visible = isConfirmPasswordError) {
                    Text(
                        text = "Confirm password were not same as password",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
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
                               UserName = nameText,
                               UserEmail = emailText,
                               UserPassword = passwordText,
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
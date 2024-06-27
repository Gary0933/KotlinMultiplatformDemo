package ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val myShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextFieldTheme() = textFieldColors(
    focusedContainerColor = TextFieldColor,
    unfocusedContainerColor = TextFieldColor,
    cursorColor = MaterialTheme.colorScheme.onBackground,
    disabledContainerColor = TextFieldColor,
    focusedIndicatorColor = Color.Transparent, // 当 TextField 获得焦点时，指示器的颜色被设置为透明。通常，指示器是一个下划线，它在输入框获得焦点时显示。设置为透明意味着当输入框获得焦点时，这个下划线将不可见
    unfocusedIndicatorColor = Color.Transparent, // 当 TextField 没有获得焦点时，指示器的颜色也被设置为透明。这意味着即使输入框没有焦点，下划线也不会显示
    disabledTextColor = MaterialTheme.colorScheme.onBackground, // 根据系统样式，颜色选择与背景不同的反差色
    disabledIndicatorColor = Color.Transparent, // 当 TextField 被禁用时，指示器的颜色设置为透明。这意味着当输入框被禁用时，下划线不会显示
    errorIndicatorColor = Color.Transparent, // 显示validation的信息的时候，指示器颜色设置为透明
)

@Composable
fun DefaultButtonTheme() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.background,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.primary,
)

@Composable
fun DefaultNavigationBarItemTheme() = NavigationBarItemDefaults.colors(
    selectedIconColor = MaterialTheme.colorScheme.primary,
    unselectedIconColor = MaterialTheme.colorScheme.primary,
    unselectedTextColor = MaterialTheme.colorScheme.primary.copy(.7f),
    selectedTextColor = MaterialTheme.colorScheme.primary,
    indicatorColor = MaterialTheme.colorScheme.background,
)

@Composable
fun DefaultCheckBoxTheme() = CheckboxDefaults.colors(
    checkedColor = MaterialTheme.colorScheme.primary,
    uncheckedColor = MaterialTheme.colorScheme.primary,
    checkmarkColor = MaterialTheme.colorScheme.background
)
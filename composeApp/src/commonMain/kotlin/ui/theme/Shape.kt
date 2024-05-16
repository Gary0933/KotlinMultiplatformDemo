package ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextFieldTheme() = textFieldColors(
    focusedContainerColor = TextFieldColor,
    unfocusedContainerColor = TextFieldColor,
    cursorColor = MaterialTheme.colorScheme.onBackground,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledContainerColor = TextFieldColor,
    disabledTextColor = MaterialTheme.colorScheme.onBackground,
    disabledIndicatorColor = Color.Transparent,
)

@Composable
fun DefaultButtonTheme() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = MaterialTheme.colorScheme.background,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.primary
)
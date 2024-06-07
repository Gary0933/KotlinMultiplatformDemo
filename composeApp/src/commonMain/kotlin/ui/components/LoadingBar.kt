package ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingBar(
    showLoading: Boolean = false,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 2.dp,
    color: Color = MaterialTheme.colorScheme.background
) {
    if (showLoading) {
        CircularProgressIndicator(
            modifier = modifier,
            strokeWidth = strokeWidth, // loading指示器的线条宽度
            color = color
        )
    }
}
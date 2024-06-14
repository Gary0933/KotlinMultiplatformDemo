package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ui.theme.BorderColor

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(1.dp, BorderColor),
        onClick = {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector, null)
        }
    }
}

@Composable
fun TextWithLoadingInButton(
    showLoading: Boolean,
    content: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Box (
            Modifier
                .weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            LoadingBar(
                showLoading = showLoading,
                modifier = Modifier
                    .size(25.dp),
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.background
            )
        }

        content()

        Spacer(Modifier.weight(1f))
    }

}
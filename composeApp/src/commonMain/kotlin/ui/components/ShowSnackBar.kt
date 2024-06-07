package ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowSnackBar(
    title: String,
    modifier: Modifier,
    snackBarVisibleState: Boolean
) {


    if (snackBarVisibleState) {
        // Show the SnackBar
        Snackbar(
            modifier = modifier.padding(16.dp),
            action = {}
        ) {
            Text(text = title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
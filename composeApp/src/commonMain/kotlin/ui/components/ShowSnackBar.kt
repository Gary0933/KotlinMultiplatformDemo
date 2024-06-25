package ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import business.data_state.ManageUiState

@Composable
fun ShowSnackBar(
    uiState: ManageUiState,
    modifier: Modifier = Modifier.padding(40.dp)
) {

    if (uiState.showAlert) {
        // Show the SnackBar
        Snackbar(
            modifier = modifier,
            action = {}
        ) {
            Text(
                text = uiState.alertMessage,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
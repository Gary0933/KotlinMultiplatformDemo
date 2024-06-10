package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import business.data_state.ManageUiState

@Composable
fun BasicScreenUI(
    showTopBar: Boolean = true,
    uiState: ManageUiState? = null,
    toolbarTitle: String? = null,
    backOnTopBarOnClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            if (showTopBar) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CircleButton(
                        modifier = Modifier.size(50.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        onClick = { backOnTopBarOnClick() }
                    )
                    if (toolbarTitle != null) {
                        Text(
                            text = toolbarTitle,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Spacer_50dp() // 这里的大小要跟上面CircleButton保持一致，才能保证Text居中显示
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            content()

            if (uiState != null) {
                ShowSnackBar(
                    title = uiState.registerSuccessAlertMessage,
                    snackBarVisibleState = uiState.showRegisterSuccessAlert,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}
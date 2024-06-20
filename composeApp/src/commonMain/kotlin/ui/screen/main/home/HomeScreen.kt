package ui.screen.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.components.BasicScreenUI
import ui.theme.PrimaryColor

@Composable
fun HomeScreen() {

    BasicScreenUI(
        showTopBar = false,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "This is home screen",
                    style = MaterialTheme.typography.labelLarge,
                    color = PrimaryColor,
                )
            }
        }
    }
}
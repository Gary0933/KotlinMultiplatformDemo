package ui.screen.main.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.theme.PrimaryColor

@Composable
fun CartScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.align(Alignment.Center).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "This is cart screen",
                style = MaterialTheme.typography.labelLarge,
                color = PrimaryColor,
            )
        }
    }
}
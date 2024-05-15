package ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun MainScreen() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This is Main Screen"
        )
    }

}
package common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun QrScannerComponent (
    modifier: Modifier,
    flashlightOn: Boolean,
    launchGallery: Boolean,
    onCompletion: (String) -> Unit,
    onGalleryCallBackHandler: (Boolean) -> Unit,
    onFailure: (String) -> Unit
)
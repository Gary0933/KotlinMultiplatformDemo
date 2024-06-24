package common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun QrScannerComponent(
    modifier: Modifier,
    flashlightOn: Boolean,
    launchGallery: Boolean,
    onCompletion: (String) -> Unit,
    onGalleryCallBackHandler: (Boolean) -> Unit,
    onFailure: (String) -> Unit
) {
    // do noting, desktop can not use qr scanner
}
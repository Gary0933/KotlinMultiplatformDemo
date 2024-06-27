package common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import qrscanner.QrScanner

@Composable
actual fun QrScannerComponent(
    modifier: Modifier,
    flashlightOn: Boolean,
    launchGallery: Boolean,
    onCompletion: (String) -> Unit,
    onGalleryCallBackHandler: (Boolean) -> Unit,
    onFailure: (String) -> Unit,
) {
    QrScanner(
        modifier = modifier,
        flashlightOn = flashlightOn,
        launchGallery = launchGallery,
        onCompletion = onCompletion,
        onGalleryCallBackHandler = onGalleryCallBackHandler,
        onFailure = onFailure,
    )
}
package ui.screen.main.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import common.QrScannerComponent
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.back_3
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import ui.components.BasicScreenUI
import ui.screen.main.cart.view_model.CartViewModel
import ui.theme.PrimaryColor
import ui.theme.QrScannerBackground

@Composable
fun CartScannerScreen(
    cartViewModel: CartViewModel,
    navigateToAddToCart: () -> Unit
) {
    var scannerBorderColor by remember { mutableStateOf(Color.Red) }
    var productId by remember { mutableStateOf("") }
    var startBarCodeScan by remember { mutableStateOf(false) }
    val flashlightOn by remember { mutableStateOf(false) }
    var launchGallery by remember { mutableStateOf(value = false) }
    val coroutineScope = rememberCoroutineScope()

    BasicScreenUI(
        showTopBar = false,
        contentAlignment = Alignment.TopCenter,
        backGroundColor = QrScannerBackground
    ) {

        LaunchedEffect(scannerBorderColor) {
            if (scannerBorderColor == Color.Green) {
                cartViewModel.updateProductId(productId)
                delay(1000)
                navigateToAddToCart()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment  = Alignment.TopStart,
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape) // 裁剪为圆形
                    .clickable {
                        navigateToAddToCart()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.back_3),
                    contentDescription = "Back",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    tint = PrimaryColor,
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {

            Column(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 300.dp, height = 150.dp) // 设置宽度和高度，根据需要调整
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 14.dp,
                                topEnd = 14.dp,
                                bottomStart = 14.dp,
                                bottomEnd = 14.dp
                            )
                        )
                        .border(
                            2.dp,
                            scannerBorderColor,
                            RoundedCornerShape(
                                topStart = 14.dp,
                                topEnd = 14.dp,
                                bottomStart = 14.dp,
                                bottomEnd = 14.dp
                            )
                        )
                        .clickable {
                            if (productId.isNotEmpty()) {
                                cartViewModel.updateProductId(productId)
                                navigateToAddToCart()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    QrScannerComponent(
                        modifier = Modifier
                            .clipToBounds()
                            .clip(shape = RoundedCornerShape(size = 14.dp)),
                        flashlightOn = flashlightOn,
                        launchGallery = launchGallery,
                        onCompletion = {
                            scannerBorderColor = Color.Green
                            productId = it
                            startBarCodeScan = false
                            println("scan result $productId")
                        },
                        onGalleryCallBackHandler = {
                            launchGallery = it
                        },
                        onFailure = {
                            coroutineScope.launch {

                            }
                        }
                    )
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Touch to confirm")
                }
            }
        }
    }
}
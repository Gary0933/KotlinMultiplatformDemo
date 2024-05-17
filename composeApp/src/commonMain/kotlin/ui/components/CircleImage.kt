package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.default_image_loader
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun CircleImage(
    image: String,
    modifier: Modifier = Modifier.size(55.dp),
    onClick: (() -> Unit?)? = null,
    width: Dp = 1.5.dp,
    color: Color = Color.White
) {
    Card(
        modifier = modifier,
        onClick = {
            if (onClick != null) {
                onClick()
            }
        },
        border = if (width == 0.dp) null else BorderStroke(width = width, color = color),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Image(
            painterResource(Res.drawable.default_image_loader),
            //rememberCustomImagePainter(image), // 这个方法只支持android,ios不知道，但是desktop不支持
            null,
            contentScale = ContentScale.Crop, // 将图片填充整个容器
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun CircleImage(
    image: ImageBitmap?,
    modifier: Modifier = Modifier.size(55.dp),
    onClick: (() -> Unit?)? = null,
    width: Dp = 1.5.dp,
    color: Color = Color.White
) {
    Card(
        modifier = modifier,
        onClick = {
            if (onClick != null) {
                onClick()
            }
        },
        border = if (width == 0.dp) null else BorderStroke(width = width, color = color),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        if (image == null) {
            Image(
                rememberCustomImagePainter(image),
                null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                image,
                null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
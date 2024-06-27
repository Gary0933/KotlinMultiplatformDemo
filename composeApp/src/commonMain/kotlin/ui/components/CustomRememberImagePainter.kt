package ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import coil3.ImageLoader
import coil3.compose.rememberAsyncImagePainter
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.default_image_loader
import org.jetbrains.compose.resources.painterResource

@Composable
fun rememberCustomImagePainter(
    model: Any?,
    imageLoader: ImageLoader,
    contentScale: ContentScale = ContentScale.Fit,
) = rememberAsyncImagePainter(
    model, imageLoader,
    error = painterResource(Res.drawable.default_image_loader),
    placeholder = painterResource(Res.drawable.default_image_loader),
    contentScale = contentScale,
)

@Composable
fun rememberCustomImagePainter(
    model: Any?,
    contentScale: ContentScale = ContentScale.Fit,
) = rememberAsyncImagePainter(
    model,
    error = painterResource(Res.drawable.default_image_loader),
    placeholder = painterResource(Res.drawable.default_image_loader),
    contentScale = contentScale,
)
package ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


data class SwipeConfiguration (
    var swipeToLeft: Boolean = false,
    var swipeToRight: Boolean = false,
    var swipeToLeftColor: Color = Color.Red,
    var swipeToRightColor: Color = Color.Green,
    var swipeDurationColor: Color = Color.LightGray,
    var swipeToLeftIcon: ImageVector = Icons.Default.Delete,
    var swipeToRightIcon: ImageVector = Icons.Default.Add,
)


//使用material3自带的SwipeToDismissBox，滑动后放手松开立即执行
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDismissComponent(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    swipeConfiguration: SwipeConfiguration = SwipeConfiguration(),
    swipeToLeftAction: () -> Unit = {},
    swipeToRightAction: () -> Unit = {},
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            // 从右向左滑
            if (swipeConfiguration.swipeToLeft) {
                if (it == SwipeToDismissBoxValue.EndToStart) { //滑动后放手会执行
                    swipeToLeftAction()
                    return@rememberSwipeToDismissBoxState true
                }
            }
            // 从左向右划
            if (swipeConfiguration.swipeToRight) {
                if (it == SwipeToDismissBoxValue.StartToEnd) { //滑动后放手会执行
                    swipeToRightAction()
                }
            }
            return@rememberSwipeToDismissBoxState false
        },
        positionalThreshold = { //滑动到什么位置会改变状态，滑动阈值
            it / 4
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier,
        enableDismissFromStartToEnd = swipeConfiguration.swipeToRight,
        enableDismissFromEndToStart = swipeConfiguration.swipeToLeft,
        backgroundContent = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.StartToEnd -> swipeConfiguration.swipeToRightColor
                    SwipeToDismissBoxValue.EndToStart -> swipeConfiguration.swipeToLeftColor
                    else -> swipeConfiguration.swipeDurationColor
                },
                label = ""
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(color),
                contentAlignment = if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                    Alignment.CenterEnd
                } else {
                    Alignment.CenterStart
                }
            ) {
                if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                    Icon(
                        swipeConfiguration.swipeToLeftIcon,
                        contentDescription = "",
                        modifier = Modifier
                    )
                } else {
                    Icon(
                        swipeConfiguration.swipeToRightIcon,
                        contentDescription = "",
                        modifier = Modifier
                    )
                }
            }
        },
        content = {
            Box(
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = content
            )
        }
    )
}
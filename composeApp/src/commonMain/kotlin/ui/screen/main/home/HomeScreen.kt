package ui.screen.main.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import business.data_state.DeliveryState
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import ui.components.BasicScreenUI
import ui.components.SwipeConfiguration
import ui.components.SwipeToDismissComponent
import ui.screen.main.home.view_model.HomeViewModel
import ui.theme.BorderColor
import ui.theme.DefaultButtonTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinInject()
) {
    /*
    BasicScreenUI(
        showTopBar = false,
    ) {

        val deliveryState: DeliveryState by homeViewModel.deliveryState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp)
                .background(Color.LightGray)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                itemsIndexed(deliveryState.product) {index, value ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .border(1.dp, BorderColor, MaterialTheme.shapes.medium)
                                .clickable {
                                    homeViewModel.updateProductToState(index, "BBB")
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(value)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp)
                    .height(60.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        0.dp
                    ),
                    shape = MaterialTheme.shapes.extraLarge,
                    border = null,
                    colors = DefaultButtonTheme(),
                    contentPadding = ButtonDefaults.ContentPadding,
                    onClick = {
                        homeViewModel.addProductToState("AAA")
                        println(deliveryState)
                    }
                ) {
                    Text(
                        text = stringResource(Res.string.sign_up),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                }
            }
        }
    }

    */

    val data = mutableListOf<DemoData>()

    repeat(10) {
        data.add(it, DemoData(it, "Item: $it"))
    }

    SwipeToDismissBoxDemo(data)
}

data class DemoData(
    val id: Int,
    val title: String,
)

/**
 * 使用material3自带的SwipeToDismissBox，滑动后放手松开立即执行
 * Box里面嵌套两层Row，当上面一层Row被滑动移走时，下面那层Row就会展示出来，两层Row布局都是全部充满Box的。
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeToDismissBoxDemo(list: MutableList<DemoData>) {
    val data = remember {
        mutableStateListOf<DemoData>()
    }
    data.addAll(list)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
    ) {
        //items务必添加key，否则会造成显示错乱
        itemsIndexed(data, key = { index, item -> item.id }) { index, item ->
            //index和item都是最原始的数据，一旦onDelete和onChange过，index和item就都不准了，因此根据item的id作为唯一标识查找
            SwipeToDismissComponent(
                modifier = Modifier
                    .animateItemPlacement()
                    .padding(20.dp)
                    .height(50.dp)
                    .fillMaxWidth(), //添加移除时的动画
                content = {
                    Text(
                        text = item.title,
                        modifier = Modifier.background(Color.Yellow).fillMaxSize()
                    )
                },
                swipeConfiguration = SwipeConfiguration(
                    swipeToLeft = true
                ),
                swipeToLeftAction = { data.remove(data.find { it.id == item.id }) },
            )
        }
    }
}


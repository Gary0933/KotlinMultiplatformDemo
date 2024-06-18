package ui.screen.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import business.data_state.CartStateTest
import org.koin.compose.koinInject
import ui.components.BasicScreenUI
import ui.components.SwipeConfiguration
import ui.components.SwipeToDismissComponent
import ui.screen.main.home.view_model.HomeViewModel
import ui.theme.PrimaryColor

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinInject()
) {

    BasicScreenUI(
        showTopBar = false,
    ) {

        SwipeToDismissBoxDemo(homeViewModel)
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDismissBoxDemo(homeViewModel: HomeViewModel) {

    val addCartDataState by homeViewModel.addCartDataState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
    ) {
        //items务必添加key，否则会造成显示错乱
        itemsIndexed(addCartDataState.productItem, key = { index, item -> item.id }) { index, item ->
            //index和item都是最原始的数据，一旦onDelete和onChange过，index和item就都不准了，因此根据item的id作为唯一标识查找
            SwipeToDismissComponent(
                modifier = Modifier.animateItemPlacement(), //添加移除时的动画
                content = { Text(item.name) },
                swipeConfiguration = SwipeConfiguration(swipeToLeft = true),
                swipeToLeftAction = {
                    homeViewModel.deleteProductItem(item.id)
                },
            )
        }
    }
}

data class DemoData(
    val id: Int,
    val title: String,
)

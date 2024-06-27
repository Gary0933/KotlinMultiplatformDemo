package ui.screen.main.profile.my_orders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ui.components.BasicScreenUI
import ui.theme.BorderColor

const val SHIPPING_ACTIVE = 0
const val SHIPPING_SUCCESS = 1
const val SHIPPING_FAILED = 2
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileMyOrdersScreen(backOnTopBar: () -> Unit) {

    val tabList by remember {
        mutableStateOf(
            listOf(
                "Active",
                "Success",
                "Failed",
            )
        )
    }

    val pagerState = rememberPagerState { tabList.size }

    val scope = rememberCoroutineScope()

    BasicScreenUI(
        toolbarTitle = "My Orders",
        backOnTopBarOnClick = backOnTopBar,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            TabRow( // 水平分页器的标签行
                modifier = Modifier.height(50.dp).fillMaxWidth(),
                selectedTabIndex = pagerState.currentPage,
                contentColor = Color.Transparent,
                containerColor = Color.Transparent,
                divider = {},
                indicator = { tabPositions -> // 选中指示器(标签下的横线)
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .height(4.dp)
                            .padding(horizontal = 28.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.medium
                            )
                    )
                }
            ) {
                tabList.forEachIndexed { index, _ ->
                    Tab( // 设置标签的样式
                        unselectedContentColor = Color.Transparent,
                        selectedContentColor = Color.Transparent,
                        text = {
                            Text(
                                tabList[index],
                                style = MaterialTheme.typography.labelLarge,
                                color = if (pagerState.currentPage == index) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onBackground
                                }
                            )
                        },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

            HorizontalPager( // 水平分页器每一页的样式和内容
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top
            ) { index ->
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier.fillMaxSize()
                ) {

                    when (index) {
                        SHIPPING_ACTIVE -> {
                            MyOrdersList("Nothing in active")
                        }
                        SHIPPING_SUCCESS -> {
                            MyOrdersList("Nothing in success")
                        }
                        SHIPPING_FAILED -> {
                            MyOrdersList("Nothing in failed")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MyOrdersList(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.titleLarge,
        color = BorderColor,
        modifier = Modifier.fillMaxSize().padding(top = 64.dp),
        textAlign = TextAlign.Center,
    )
}

package ui.screen.main.cart

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import business.constants.productItemOptions
import business.constants.productTypeOptions
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.add
import kotlinmultiplatformdemo.composeapp.generated.resources.expend
import kotlinmultiplatformdemo.composeapp.generated.resources.scan_barcode
import kotlinmultiplatformdemo.composeapp.generated.resources.shrink
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import ui.components.BasicScreenUI
import ui.components.SwipeConfiguration
import ui.components.SwipeToDismissComponent
import ui.components.TextWithLoadingInButton
import ui.components.noRippleClickable
import ui.screen.main.cart.view_model.CartViewModel
import ui.theme.myShapes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddToCartScreen(
    cartViewModel: CartViewModel,
    navigateToCart: () -> Unit,
    navigateToScanner: () -> Unit,
) {

    BasicScreenUI(
        toolbarTitle = "Add To Cart",
        backOnTopBarOnClick = navigateToCart
    ) {
        val addCartDataState by cartViewModel.addCartDataState.collectAsState()
        var showLoading by rememberSaveable { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf("New Item") }

        if (addCartDataState.productType.isEmpty()) {
            cartViewModel.updateProductType(productTypeOptions[0])
        }

        if (addCartDataState.productItemList.isEmpty()) {
            cartViewModel.addProductItem(productItemOptions[0])
        }

        LaunchedEffect(showLoading) {// 实时监测这两个对象，如果有变换，立即执行里面的代码
            if (showLoading) {
                delay(1000)
                showLoading = false
                cartViewModel.updateDeliveryItemList()
                navigateToCart()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // product id
                item {
                    Spacer(modifier = Modifier.height(8.dp))

                    itemCard {
                        cardWithSelectedOption {
                            Column(
                                modifier = Modifier.fillMaxHeight(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Product ID",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.size(5.dp))
                                Text(
                                    text = addCartDataState.productId,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                )
                            }
                            Icon(
                                painterResource(Res.drawable.scan_barcode),
                                contentDescription = "Scanner",
                                modifier = Modifier.clickable {
                                    navigateToScanner()
                                }
                            )
                        }
                    }
                }

                // Product type
                item {
                    itemCard {
                        cardWithSelectedOption {
                            val expanded = remember { mutableStateOf(false) }
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .noRippleClickable {
                                        expanded.value = !expanded.value
                                    },
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Type",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 15.sp,
                                        color = Color.Gray
                                    )
                                    Spacer(modifier = Modifier.size(5.dp))
                                    Text(
                                        text = addCartDataState.productType,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                    )
                                }
                                Icon(
                                    painter = if (expanded.value) {
                                        painterResource(Res.drawable.shrink)
                                    } else {
                                        painterResource(Res.drawable.expend)
                                    },
                                    contentDescription = "Icon",
                                    Modifier.align(Alignment.CenterEnd)
                                )
                                DropdownMenu(
                                    expanded = expanded.value,
                                    onDismissRequest = { expanded.value = false }
                                ) {
                                    productTypeOptions.forEach { selectedOption ->
                                        DropdownMenuItem(
                                            onClick = {
                                                // 选择选项
                                                cartViewModel.updateProductType(selectedOption)
                                                expanded.value = false
                                            },
                                            modifier = Modifier.fillMaxWidth(),
                                            text = { Text(selectedOption) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Product items
                itemsIndexed (
                    addCartDataState.productItemList,
                    /**
                     * Compose默认会使用index的索引作为键, 当你使用key参数时，你正在为列表中的每个项目指定一个唯一的标识符
                     * 此时Compose会使用你设置的key来作为键，当列表数据发生变化后(增删改),根据键来处理如何更新列表
                     * 这里不使用index作为键的原因是,index会随着某一个数据的删除或修改导致整个列表的index发生错乱，页面根据index重载会出问题
                     */
                    /**
                     * Compose默认会使用index的索引作为键, 当你使用key参数时，你正在为列表中的每个项目指定一个唯一的标识符
                     * 此时Compose会使用你设置的key来作为键，当列表数据发生变化后(增删改),根据键来处理如何更新列表
                     * 这里不使用index作为键的原因是,index会随着某一个数据的删除或修改导致整个列表的index发生错乱，页面根据index重载会出问题
                     */
                    key = { index, value -> value.itemId }
                ) { index, value ->
                    itemCard {
                        if (value.itemId == 0) {
                            cardWithSelectedOption {
                                val expanded = remember { mutableStateOf(false) }
                                Box(
                                    contentAlignment = Alignment.CenterStart,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .noRippleClickable {
                                            expanded.value = !expanded.value
                                        },
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        if (value.itemId == 0) {
                                            Text(
                                                text = "Item",
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 15.sp,
                                                color = Color.Gray
                                            )
                                        }
                                        Spacer(modifier = Modifier.size(5.dp))
                                        Text(
                                            text = value.itemText,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                        )
                                    }
                                    Icon(
                                        painter = if (expanded.value) {
                                            painterResource(Res.drawable.shrink)
                                        } else {
                                            painterResource(Res.drawable.expend)
                                        },
                                        contentDescription = "Icon",
                                        Modifier.align(Alignment.CenterEnd)
                                    )
                                    DropdownMenu(
                                        expanded = expanded.value,
                                        onDismissRequest = { expanded.value = false }
                                    ) {
                                        productItemOptions.forEach { selectedOption ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    // 选择选项
                                                    cartViewModel.updateProductItem(value.itemId, selectedOption)
                                                    expanded.value = false
                                                },
                                                modifier = Modifier.fillMaxWidth(),
                                                text = { Text(selectedOption) }
                                            )
                                        }
                                    }
                                }
                            }
                        } else {
                            SwipeToDismissComponent( // 可以右滑动删除
                                modifier = Modifier
                                    .animateItemPlacement()
                                    .fillMaxWidth(), //添加移除时的动画
                                swipeConfiguration = SwipeConfiguration(
                                    swipeToLeft = true
                                ),
                                swipeToLeftAction = {
                                    cartViewModel.deleteProductItem(value.itemId)
                                },
                                content = {
                                    cardWithSelectedOption {
                                        val expanded = remember { mutableStateOf(false) }
                                        Box(
                                            contentAlignment = Alignment.CenterStart,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .noRippleClickable {
                                                    expanded.value = !expanded.value
                                                },
                                        ) {
                                            Column(
                                                modifier = Modifier.fillMaxHeight(),
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                if (index == 0) {
                                                    Text(
                                                        text = "Item",
                                                        fontWeight = FontWeight.Medium,
                                                        fontSize = 15.sp,
                                                        color = Color.Gray
                                                    )
                                                }
                                                Spacer(modifier = Modifier.size(5.dp))
                                                Text(
                                                    text = value.itemText,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 18.sp,
                                                )
                                            }
                                            Icon(
                                                painter = if (expanded.value) {
                                                    painterResource(Res.drawable.shrink)
                                                } else {
                                                    painterResource(Res.drawable.expend)
                                                },
                                                contentDescription = "Icon",
                                                Modifier.align(Alignment.CenterEnd)
                                            )
                                            DropdownMenu(
                                                expanded = expanded.value,
                                                onDismissRequest = { expanded.value = false }
                                            ) {
                                                productItemOptions.forEach { selectedOption ->
                                                    DropdownMenuItem(
                                                        onClick = {
                                                            // 选择选项
                                                            cartViewModel.updateProductItem(value.itemId, selectedOption)
                                                            expanded.value = false
                                                        },
                                                        modifier = Modifier.fillMaxWidth(),
                                                        text = { Text(selectedOption) }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                }

                // add product item
                item {
                    itemCard {
                        cardWithSelectedOption {
                            val expanded = remember { mutableStateOf(false) }
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .noRippleClickable {
                                        expanded.value = !expanded.value
                                    },
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = selectedOptionText,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                    )
                                }
                                Icon(
                                    painter = if (expanded.value) {
                                        painterResource(Res.drawable.shrink)
                                    } else {
                                        painterResource(Res.drawable.expend)
                                    },
                                    contentDescription = "Icon",
                                    Modifier.align(Alignment.CenterEnd)
                                )
                                DropdownMenu(
                                    expanded = expanded.value,
                                    onDismissRequest = { expanded.value = false }
                                ) {
                                    productItemOptions.forEach { selectedOption ->
                                        DropdownMenuItem(
                                            onClick = {
                                                // 选择选项
                                                selectedOptionText = selectedOption
                                                expanded.value = false
                                            },
                                            modifier = Modifier.fillMaxWidth(),
                                            text = { Text(selectedOption) }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Row {
                        Spacer(modifier = Modifier.height(15.dp))
                        Icon(
                            painterResource(Res.drawable.add),
                            contentDescription = "Add item",
                            modifier = Modifier
                                .padding(12.dp)
                                .width(20.dp)
                                .height(20.dp)
                                .clickable {
                                    if (selectedOptionText != "New Item") {
                                        cartViewModel.addProductItem(selectedOptionText)
                                        println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ${addCartDataState}")
                                        selectedOptionText = "New Item"
                                    }
                                }
                        )
                        Text(
                            text = "Add Item",
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }

            // submit
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Button(
                    enabled = true, // 按钮是否可用
                    elevation = ButtonDefaults.buttonElevation(), // 设置按钮阴影
                    shape = MaterialTheme.shapes.extraLarge,
                    contentPadding = ButtonDefaults.ContentPadding, // 按钮内的内容跟按钮边框的距离
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = {
                        if (addCartDataState.productId.isNotEmpty()) {
                            showLoading = true
                        }
                    }
                ) {
                    TextWithLoadingInButton(
                        showLoading = showLoading
                    ) {
                        Text(
                            text = "Submit",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(horizontal = 30.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun itemCard(
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = myShapes.small
    ) {
        content()
    }
}

@Composable
fun cardWithSelectedOption(
    content: @Composable () -> Unit,
) {
    val expanded = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .noRippleClickable { // 点击时没有特效
                expanded.value = !expanded.value
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        content()
    }
}


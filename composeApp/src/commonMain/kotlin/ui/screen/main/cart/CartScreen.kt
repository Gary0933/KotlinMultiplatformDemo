package ui.screen.main.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import business.data_state.CartState
import business.data_state.ItemData
import ui.components.BasicScreenUI
import ui.screen.main.cart.view_model.CartViewModel
import ui.theme.DeletedColor
import ui.theme.PrimaryColor
import ui.theme.backgroundLightGrey
import ui.theme.myShapes

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    navigateToAddToCart: () -> Unit
) {
    BasicScreenUI(
        showTopBar = false
    ) {

        val cartList by cartViewModel.cartListState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // 显示添加UI的部分
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Card( // 这里用Card是为了利用Card显示背景底部的阴影
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(20.dp),
                    shape = RoundedCornerShape(0.dp), //取消圆角
                    colors = CardColors(
                        containerColor = PrimaryColor,
                        contentColor = PrimaryColor,
                        disabledContainerColor = PrimaryColor,
                        disabledContentColor = PrimaryColor,
                    )
                ) {
                    Box( // Card里内容的居中，需要通过Box来控制
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp, horizontal = 30.dp)
                            .height(70.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        OutlinedButton(
                            colors = ButtonColors(
                                containerColor = PrimaryColor,
                                contentColor = backgroundLightGrey,
                                disabledContainerColor = PrimaryColor,
                                disabledContentColor = backgroundLightGrey,
                            ),
                            shape = myShapes.small,
                            border = BorderStroke(
                                width = 2.dp,
                                color = backgroundLightGrey
                            ),
                            onClick = {
                                navigateToAddToCart()
                            }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .padding(5.dp)
                                            .border(
                                                width = 2.dp,
                                                color = backgroundLightGrey,
                                                shape = myShapes.small
                                            ),
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Add,
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            tint = backgroundLightGrey
                                        )
                                    }
                                }

                                Text(
                                    text = "Click add to cart",
                                    fontSize = 20.sp,
                                    color = backgroundLightGrey
                                )

                                Spacer(Modifier.weight(1f))
                            }
                        }

                    }
                }
            }

            // 显示已添加的Cart List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 10.dp)
                    .weight(1f)
            ) {

                itemsIndexed(cartList) {index, value ->
                    showCartItem(
                        cartIndex = index,
                        cartItemData = value,
                        cartViewModel = cartViewModel
                    )
                }

            }
        }

    }
}

@Composable
fun showCartItem(
    cartIndex: Int,
    cartItemData: CartState,
    cartViewModel: CartViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {

            },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = myShapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(cartItemData.productId)
                Spacer(modifier = Modifier.size(5.dp))
                Text(cartItemData.productType)
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = setAllItemValueToString(cartItemData.productItemList)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 15.dp)
                    .width(40.dp)
                    .background(
                        color = DeletedColor,
                        shape = myShapes.small
                    )
                    .clickable {
                        cartViewModel.deleteCartToState(cartIndex)
                    },
                    contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "cloud upload",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.size(10.dp))
}


fun setAllItemValueToString(productItemList: MutableList<ItemData>): String {
    val mutableList: MutableList<String> = mutableListOf()
    productItemList.forEach { item ->
        mutableList.add(
            item.itemText
        )
    }
    return mutableList.joinToString(",")
}
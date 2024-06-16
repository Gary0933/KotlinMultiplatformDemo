package ui.screen.main.cart

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
import androidx.compose.foundation.lazy.LazyColumn
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
import business.constants.productTypeOptions
import kotlinmultiplatformdemo.composeapp.generated.resources.Res
import kotlinmultiplatformdemo.composeapp.generated.resources.expend
import kotlinmultiplatformdemo.composeapp.generated.resources.scan_barcode
import kotlinmultiplatformdemo.composeapp.generated.resources.shrink
import org.jetbrains.compose.resources.painterResource
import ui.components.BasicScreenUI
import ui.components.TextWithLoadingInButton
import ui.components.noRippleClickable
import ui.screen.main.cart.view_model.CartViewModel
import ui.theme.myShapes

@Composable
fun AddToCartScreen(
    cartViewModel: CartViewModel,
    navigateToCart: () -> Unit,
    navigateToScanner: () -> Unit,
    backOnTopBar: () -> Unit,
) {

    BasicScreenUI(
        toolbarTitle = "Add To Cart",
        backOnTopBarOnClick = backOnTopBar
    ) {

        val addCartDataState by cartViewModel.addCartDataState.collectAsState()
        var showLoading by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
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
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(12.dp),
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
                    val expanded = remember { mutableStateOf(false) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(12.dp)
                            .noRippleClickable {
                                expanded.value = !expanded.value
                            },
                        elevation = CardDefaults.cardElevation(10.dp),
                        shape = myShapes.small,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                                .padding(vertical = 10.dp, horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .fillMaxWidth()
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
                                                addCartDataState.productType = selectedOption
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

            }

            // submit
            Column(
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 10.dp),
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
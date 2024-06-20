package ui.screen.main.cart.view_model

import androidx.lifecycle.ViewModel
import business.data_state.CartState
import business.data_state.ItemData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel: ViewModel() {

    private var _cartListState = MutableStateFlow(emptyList<CartState>())
    val cartListState: StateFlow<List<CartState>> = _cartListState

    private var _addCartDataState = MutableStateFlow(CartState())
    val addCartDataState: StateFlow<CartState> = _addCartDataState

    init {
        // 初始化一些数据用来显示
        val currentState = _cartListState.value
        _cartListState.value = currentState.toMutableList().apply {
            repeat(10) {
                add(
                    CartState(
                        productId = (it + 1).toString(),
                        productType = "Fruit",
                        productItemList = mutableListOf(
                            ItemData(0, "Apple"),
                            ItemData(1, "Banana"),
                        )
                    )
                )
            }
        }
    }

    // 删除cart
    fun deleteCartToState(index: Int) {
        val currentState = _cartListState.value
        _cartListState.value = currentState.toMutableList().apply {
            remove(this[index])
        }
    }

    // 更新product ID
    fun updateProductId(productId: String) {
        val currentState = _addCartDataState.value
        _addCartDataState.value = currentState.copy(
            productId = productId
        )
    }

    // 更新product type
    fun updateProductType(productType: String) {
        val currentState = _addCartDataState.value
        _addCartDataState.value = currentState.copy(
            productType = productType
        )
    }

    // 添加新的product item
    fun addProductItem(productItemText: String) {
        val currentState = _addCartDataState.value
        val newItemIndex = if (currentState.productItemList.size == 0) {
            0
        } else {
            currentState.productItemList[currentState.productItemList.size - 1].itemId + 1
        }

        val updatedItemList = currentState.productItemList.toMutableList().apply {
                add(
                    ItemData(
                        itemId = newItemIndex,
                        itemText = productItemText
                    )
                )
            }

        _addCartDataState.value = currentState.copy(productItemList = updatedItemList)
    }

    // 更改选中的product item
    fun updateProductItem(productItemId: Int, productItemText: String) {
        val currentState = _addCartDataState.value
        val updatedItemList = currentState.productItemList.toMutableList().apply {
            this.find {
                it.itemId == productItemId
            }?.itemText = productItemText
        }
        _addCartDataState.value = currentState.copy(productItemList = updatedItemList)
    }

    // 删除选中的product item
    fun deleteProductItem(productItemId: Int) {
        val currentState = _addCartDataState.value
        val newProductList = currentState.productItemList.toMutableList().apply {
            remove(
                currentState.productItemList.find {
                    it.itemId == productItemId
                }
            )
        }
        _addCartDataState.value = currentState.copy(productItemList = newProductList)
    }

    // 将新创建的cart data更新到cartListState
    fun updateDeliveryItemList() {
        val currentState = _cartListState.value
        _cartListState.value = currentState.toMutableList().apply {
            add(
                addCartDataState.value
            )
        }
    }

}
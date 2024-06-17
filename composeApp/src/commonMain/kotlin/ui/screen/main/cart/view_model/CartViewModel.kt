package ui.screen.main.cart.view_model

import androidx.lifecycle.ViewModel
import business.data_state.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel(): ViewModel() {

    private var _cartListState = MutableStateFlow(emptyList<CartState>())
    val cartListState: StateFlow<List<CartState>> = _cartListState

    private var _addCartDataState = MutableStateFlow(CartState())
    val addCartDataState: StateFlow<CartState> = _addCartDataState

    init {
        val currentState = _cartListState.value
        _cartListState.value = currentState.toMutableList().apply {
            var index = 1
            repeat(10) {
                add(
                    CartState(
                        productId = index.toString(),
                        productType = "Fruit",
                        productItem = mutableListOf("Apple","Banana")
                    )
                )
                index++
            }
        }
    }

    fun deleteCartToState(index: Int) {
        val currentState = _cartListState.value
        _cartListState.value = currentState.toMutableList().apply {
            remove(this[index])
        }
    }

    fun updateProjectId(productId: String) {
        _addCartDataState.value.productId = productId
    }

    fun updateProductType(productType: String) {
        val currentState = _addCartDataState.value
        _addCartDataState.value = currentState.copy(
            productType = productType
        )
    }

    fun deleteProductItem(productItem: String) {
        val currentState = _addCartDataState.value
        val newItemList = currentState.productItem.toMutableList().apply {
            remove(productItem)
        }
        _addCartDataState.value = currentState.copy(productItem = newItemList)
    }

    fun updateProductItem(index: Int, productItem: String) {
        val currentState = _addCartDataState.value
        if (index in currentState.productItem.indices) { // 确保索引在列表范围内
            val updatedItemList = currentState.productItem.toMutableList().apply {
                this[index] = productItem // 通过索引更新列表中的产品
            }
            _addCartDataState.value = currentState.copy(productItem = updatedItemList)
        }
    }

    fun addProductItem(productItem: String) {
        val currentState = _addCartDataState.value
        _addCartDataState.value = currentState.copy(
            productItem = currentState.productItem.toMutableList().apply {
                add(productItem)
            }
        )
    }

}
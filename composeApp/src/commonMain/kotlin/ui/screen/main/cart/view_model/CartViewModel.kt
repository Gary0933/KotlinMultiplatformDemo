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

}
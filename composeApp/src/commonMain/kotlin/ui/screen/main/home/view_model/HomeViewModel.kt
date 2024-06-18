package ui.screen.main.home.view_model

import androidx.lifecycle.ViewModel
import business.data_state.CartState
import business.data_state.CartStateTest
import business.data_state.ItemObj
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(): ViewModel() {

    private var _addCartDataState = MutableStateFlow(CartStateTest())
    val addCartDataState: StateFlow<CartStateTest> = _addCartDataState

    init {
        repeat(10) {
            _addCartDataState.value.productItem.add(
                it,
                ItemObj(id = it,name = "Item : $it"),
            )
        }
    }

    fun filterProductItem(index: Int) {
        val currentState = _addCartDataState.value
        val newItemList = currentState.productItem.filter {
            it.id != index
        }.toMutableList()
        _addCartDataState.value = currentState.copy(productItem = newItemList)
    }

    fun deleteProductItem(index: Int) {
        val currentState = _addCartDataState.value
        val newItemList = currentState.productItem.toMutableList().apply {
            remove(currentState.productItem.find {
                it.id == index
            })
        }
        _addCartDataState.value = currentState.copy(productItem = newItemList)
    }

}
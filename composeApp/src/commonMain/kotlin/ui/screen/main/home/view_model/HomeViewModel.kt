package ui.screen.main.home.view_model

import androidx.lifecycle.ViewModel
import business.data_state.DeliveryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel(): ViewModel() {

    private val _deliveryState = MutableStateFlow(DeliveryState())
    val deliveryState: StateFlow<DeliveryState> = _deliveryState

    fun addProductToState(product: String) {
        val currentState = _deliveryState.value
        _deliveryState.value = currentState.copy(
            product = currentState.product
                .toMutableList()
                .apply {
                    add(product)
                }
        )

    }

    fun updateProductToState(index: Int, product: String) {
        val currentState = _deliveryState.value
        if (index in currentState.product.indices) { // 确保索引在列表范围内
            val updatedProductList = currentState.product.toMutableList().apply {
                this[index] = product // 通过索引更新列表中的产品
            }
            _deliveryState.value = currentState.copy(product = updatedProductList)
        }
    }

}
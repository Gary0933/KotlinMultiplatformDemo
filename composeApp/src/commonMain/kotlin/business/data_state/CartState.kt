package business.data_state

data class CartState (
    var productId: String = "",
    var productType: String = "",
    var productItem: MutableList<String> = mutableListOf()
)
package business.data_state

data class CartState (
    var productId: String = "",
    var productType: String = "",
    var productItem: MutableList<String> = mutableListOf()
)


data class CartStateTest (
    var productId: String = "",
    var productType: String = "",
    var productItem: MutableList<ItemObj> = mutableListOf()
)

data class ItemObj (
    var id: Int,
    var name: String
)
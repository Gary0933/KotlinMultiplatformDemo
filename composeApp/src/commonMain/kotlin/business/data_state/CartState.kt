package business.data_state

data class CartState(
    var productId: String = "",
    var productType: String = "",
    var productItemList: MutableList<ItemData> = mutableListOf(),
)

data class ItemData(
    val itemId: Int,
    var itemText: String,
)
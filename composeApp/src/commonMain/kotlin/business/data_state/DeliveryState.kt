package business.data_state

data class DeliveryState (
    var supplier: String = "111",
    var product: MutableList<String> = mutableListOf("CCC")
)
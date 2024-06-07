package business.data_state

data class ManageUiState(
    val showLoadingBar: Boolean = false,
    val enableTextField: Boolean = true,
    var showAlert: Boolean = false
)
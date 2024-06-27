package business.data_state

data class ManageUiState(
    var showLoadingBar: Boolean = false,
    var enableTextField: Boolean = true,
    var showAlert: Boolean = false,
    var alertMessage: String = "Successful",
)
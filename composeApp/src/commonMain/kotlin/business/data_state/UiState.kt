package business.data_state

data class ManageUiState(
    val showLoadingBar: Boolean = false,
    val enableTextField: Boolean = true,
    var showRegisterSuccessAlert: Boolean = false,
    var registerSuccessAlertMessage: String = "Register Successful"
)
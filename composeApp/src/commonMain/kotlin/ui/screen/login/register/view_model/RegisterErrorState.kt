package ui.screen.login.register.view_model

data class RegisterErrorState (
    var nameError: Boolean = false,
    var emailError: Boolean = false,
    var passwordError: Boolean = false,
    var confirmPasswordError: Boolean = false,

    var nameErrorDescription: String = "Name length need between 1 to 12 digits",
    var emailErrorDescription: String = "Please validate email format",
    var passwordErrorDescription: String = "Password must between 6 to 12 digits",
    var confirmPasswordErrorDescription: String = "Confirm password were not same as password",
)
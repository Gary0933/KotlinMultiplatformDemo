package ui.screen.login.register.view_model

import androidx.lifecycle.ViewModel
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterViewModel(
    db: DbEngine
): ViewModel() {

    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db) // 操作userinfo表的数据

    var registerErrorState: MutableStateFlow<RegisterErrorState> = MutableStateFlow(RegisterErrorState())

    fun register(
        userInfoModel: UserInfoModel
    ) {
        userInfoHandler.insertUserInfo(
            userInfoModel
        )
    }
}
package ui.screen.login.register.view_model

import androidx.lifecycle.ViewModel
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel

class RegisterViewModel(
    db: DbEngine
): ViewModel() {

    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db) // 操作userinfo表的数据

    fun register(
        userInfoModel: UserInfoModel
    ) {
        userInfoHandler.insertUserInfo(
            userInfoModel
        )
    }
}
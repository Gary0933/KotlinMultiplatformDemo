package ui.screen.main.profile.settings.view_model

import androidx.lifecycle.ViewModel
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel

class SettingViewModel(
    db: DbEngine,
): ViewModel() {

    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db) // 操作userinfo表的数据

    fun logout() {
        val userInfo: UserInfoModel? = userInfoHandler.getLoginUser()
        if (userInfo != null) {
            userInfoHandler.updateLoginStatus(0, userInfo.Id)
        }
    }
}
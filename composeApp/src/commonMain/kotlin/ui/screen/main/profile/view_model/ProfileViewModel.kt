package ui.screen.main.profile.view_model

import androidx.lifecycle.ViewModel
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel(db: DbEngine) : ViewModel() {

    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db) // 操作userinfo表的数据

    private val _currentUserInfo = MutableStateFlow(UserInfoModel())
    var currentUserInfo: StateFlow<UserInfoModel> = _currentUserInfo

    init {
        val result = userInfoHandler.getLoginUser()
        if (result != null) {
            _currentUserInfo.value = result
        }
    }

}
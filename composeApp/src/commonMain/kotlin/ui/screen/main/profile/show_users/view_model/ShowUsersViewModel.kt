package ui.screen.main.profile.show_users.view_model

import androidx.lifecycle.ViewModel
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShowUsersViewModel(
    db: DbEngine
): ViewModel() {

    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db)

    private val _userListState = MutableStateFlow(emptyList<UserInfoModel>())
    val userListState: StateFlow<List<UserInfoModel>> = _userListState

    suspend fun getUsersData() {
        coroutineScope { // 创建一个携程
            launch {
                userInfoHandler.getAllUserInfoFlow(null).collect{ userInfoList ->
                    _userListState.value = userInfoList // 获取用户的实时数据
                }
            }
        }
    }

    fun deleteUsersDataById(
        userInfoModel: UserInfoModel
    ) {
        userInfoHandler.deleteUserInfo(userInfoModel)
    }
}
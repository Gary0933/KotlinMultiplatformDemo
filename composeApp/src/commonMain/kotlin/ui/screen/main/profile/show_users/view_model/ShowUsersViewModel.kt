package ui.screen.main.profile.show_users.view_model

import androidx.lifecycle.ViewModel
import common.DatabaseDriverFactory
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShowUsersViewModel(
    //db: DbEngine
): ViewModel() {
    var db: DbEngine = DbEngine.instance
    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db)

    //private val _userListState = MutableStateFlow(emptyList<UserInfoModel>())
    //val userListState: StateFlow<List<UserInfoModel>> = _userListState
    val userListState = MutableStateFlow(emptyList<UserInfoModel>())

    /*
    suspend fun getUsersData() {
        coroutineScope { // 创建一个携程
            launch {
                userInfoHandler.getAllUserInfoFlow(null).collect{ userInfoList ->
                    _userListState.value = userInfoList // 获取用户的实时数据
                }
            }
        }
    }
     */

    init {
        getUsersData()
    }

    private fun getUsersData() {
        userListState.value = userInfoHandler.getAllUserInfo(null)
    }

    fun deleteUsersDataById(
        userInfoModel: UserInfoModel
    ) {
        userInfoHandler.deleteUserInfo(userInfoModel)
        getUsersData()
    }

}
package ui.screen.main.profile.show_users.view_model

import androidx.lifecycle.ViewModel
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel
import kotlinx.coroutines.flow.MutableStateFlow

class ShowUsersViewModel(
    db: DbEngine
): ViewModel() {

    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db)

    var userListState: MutableStateFlow<List<UserInfoModel>> = MutableStateFlow( emptyList())

    init {
        userListState.value = getUsersData()
    }

    private fun getUsersData(): List<UserInfoModel> {
        return userInfoHandler.getAllUserInfo(null)
    }

    fun deleteUsersDataById(
        userInfoModel: UserInfoModel
    ) {
        userInfoHandler.deleteUserInfo(userInfoModel)
        userListState.value = getUsersData()
    }
}
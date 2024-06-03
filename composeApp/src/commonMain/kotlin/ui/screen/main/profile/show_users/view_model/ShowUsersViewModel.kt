package ui.screen.main.profile.show_users.view_model

import androidx.lifecycle.ViewModel
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel

class ShowUsersViewModel(
    db: DbEngine
): ViewModel() {

    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db)

    fun getUsersData(): List<UserInfoModel> {
        return userInfoHandler.getAllUserInfo(null)
    }
}
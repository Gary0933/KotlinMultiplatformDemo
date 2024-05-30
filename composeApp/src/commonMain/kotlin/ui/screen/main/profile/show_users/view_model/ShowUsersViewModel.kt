package ui.screen.main.profile.show_users.view_model

import androidx.lifecycle.ViewModel
import common.DatabaseDriverFactory
import database.DbEngine
import database.entity.UserInfoModel
import database.entity.getAllUserInfo
import db.util.AppDatabaseQueries

class ShowUsersViewModel: ViewModel() {

    private val dbQuery: AppDatabaseQueries = DbEngine(DatabaseDriverFactory()).dbQuery

    fun getUsersData(): List<UserInfoModel>{
        return getAllUserInfo(dbQuery,null)
    }
}
package ui.screen.login.register.view_model

import androidx.lifecycle.ViewModel
import common.DatabaseDriverFactory
import database.DbEngine
import database.entity.UserInfoModel
import database.entity.insertUserInfo
import db.util.AppDatabaseQueries

class RegisterViewModel: ViewModel() {

    private val dbQuery: AppDatabaseQueries = DbEngine(DatabaseDriverFactory()).dbQuery

    fun register(
        userInfoModel: UserInfoModel
    ) {
        insertUserInfo(
            dbQuery,
            userInfoModel
        )
    }
}
package ui.screen.main.profile.my_coupons.view_model

import androidx.lifecycle.ViewModel
import database.DbEngine
import database.entity.UserInfoModel
import database.entity.getAllUserInfo
import db.util.AppDatabaseQueries

class ShowUsersViewModel: ViewModel() {

    private val dbQuery: AppDatabaseQueries = DbEngine().dbQuery

    fun getUsersData(): List<UserInfoModel>{
        return getAllUserInfo(dbQuery,null)
    }
}
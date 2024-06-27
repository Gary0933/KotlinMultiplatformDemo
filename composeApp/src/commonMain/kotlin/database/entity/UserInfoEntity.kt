package database.entity

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import business.constants.DB_COROUTINE_CONTEXT
import database.DbEngine
import db.util.GetLoginStatusById
import db.util.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext


data class UserInfoModel(
    val Id: Int = 0,
    val UserName: String = "",
    val UserEmail: String = "",
    val UserPassword: String = "",
    val IsLogin: Int = 0,
    val CreateDate: String? = null, // 可以为空
)

class UserInfoHandler(
    private var db: DbEngine,
    private var coroutineContext: CoroutineContext = DB_COROUTINE_CONTEXT,
) {
    // 由于数据库的类型跟kotlin定义的类型有区别，需要转换
    private fun mapUserInfoList(
        Id: Long,
        UserName: String,
        UserEmail: String,
        UserPassword: String,
        IsLogin: Long?,
        CreateDate: String?,
    ): UserInfoModel {
        return UserInfoModel(
            Id = Id.toInt(),
            UserName = UserName,
            UserEmail = UserEmail,
            UserPassword = UserPassword,
            IsLogin = IsLogin?.toInt() ?: 0,
            CreateDate = CreateDate ?: "",
        )
    }

    internal fun getAllUserInfo(userInfoModel: UserInfoModel?): List<UserInfoModel> {
        return if (userInfoModel == null) {
            db.dbQuery.getAllUserInfo(::mapUserInfoList).executeAsList()
        } else {
            db.dbQuery.getUserInfoById(
                userInfoModel.Id.toLong(),
                ::mapUserInfoList,
            ).executeAsList()
        }
    }

    internal fun getAllUserInfoFlow(userInfoModel: UserInfoModel?): Flow<List<UserInfoModel>> {  // 返回一个存储实时数据的Flow
        return if (userInfoModel == null) {
            db.dbQuery.getAllUserInfo(::mapUserInfoList).asFlow().mapToList(coroutineContext)
        } else {
            db.dbQuery.getUserInfoById(
                userInfoModel.Id.toLong(),
                ::mapUserInfoList,
            ).asFlow().mapToList(coroutineContext)
        }
    }

    internal fun insertUserInfo(item: UserInfoModel) {
        db.dbQuery.transaction {
            item.run {
                db.dbQuery.insertUserInfo(
                    UserInfo(
                        Id.toLong(),
                        UserName,
                        UserEmail,
                        UserPassword,
                        IsLogin.toLong(),
                        CreateDate,
                    )
                )
            }
        }
    }

    internal fun deleteUserInfo(userId: Int?) {
        db.dbQuery.transaction {
            if (userId == null) {
                db.dbQuery.deleteAllUserInfo()
            } else {
                db.dbQuery.deleteUserInfoById(userId.toLong())
            }
        }
    }

    internal fun updateLoginStatus(isLogin: Int, userId: Int) {
        db.dbQuery.transaction {
            db.dbQuery.updateLoginStatus(
                isLogin.toLong(),
                userId.toLong(),
            )
        }
    }

    internal fun checkLoginStatus(userInfoModel: UserInfoModel): Int {
        val result: GetLoginStatusById? = db.dbQuery.getLoginStatusById(userInfoModel.Id.toLong()).executeAsOneOrNull()
        return if (result == null) {
            0
        } else {
            result.IsLogin?.toInt() ?: 0
        }
    }

    internal fun isExistUserEmail(userEmail: String): Boolean {
        val result: UserInfo? = db.dbQuery.getUserInfoByEmail(userEmail).executeAsOneOrNull()
        return result != null
    }

    internal fun validateUserInfo(userEmail: String, userPassword: String): Int{
        val result: UserInfo? = db.dbQuery.validateUserInfo(
            userEmail,
            userPassword
        ).executeAsOneOrNull()
        return result?.Id?.toInt() ?: 0
    }

    internal fun getLoginUser(): UserInfoModel? {
        return db.dbQuery.getLoginUser(::mapUserInfoList).executeAsOneOrNull()
    }
}





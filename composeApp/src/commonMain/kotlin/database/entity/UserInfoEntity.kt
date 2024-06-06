package database.entity

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import database.DbEngine
import db.util.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext


data class UserInfoModel(
    val Id: Int = 0,
    val UserName: String,
    val UserEmail: String,
    val UserPassword: String,
    val CreateDate: String? = null // 可以为空
)

class UserInfoHandler(
    private var db: DbEngine,
    private var coroutineContext: CoroutineContext = Dispatchers.IO
) {
    // 由于数据库的类型跟kotlin定义的类型有区别，需要转换
    private fun mapUserInfoList(
        Id: Long,
        UserName: String,
        UserEmail: String,
        UserPassword: String,
        CreateDate: String?,
    ): UserInfoModel {
        return UserInfoModel(
            Id = Id.toInt(),
            UserName = UserName,
            UserEmail = UserEmail,
            UserPassword = UserPassword,
            CreateDate = CreateDate ?: ""
        )
    }

    internal fun getAllUserInfo(
        userInfoModel: UserInfoModel?
    ): List<UserInfoModel> {
        return if (userInfoModel == null) {
            db.dbQuery.getAllUserInfo(::mapUserInfoList).executeAsList()
        } else {
            db.dbQuery.getUserInfoById(
                userInfoModel.Id.toLong(),
                ::mapUserInfoList
            ).executeAsList()
        }
    }

    internal fun getAllUserInfoFlow(
        userInfoModel: UserInfoModel?
    ): Flow<List<UserInfoModel>> {  // 返回一个存储实时数据的Flow
        return if (userInfoModel == null) {
            db.dbQuery.getAllUserInfo(::mapUserInfoList).asFlow().mapToList(coroutineContext)
        } else {
            db.dbQuery.getUserInfoById(
                userInfoModel.Id.toLong(),
                ::mapUserInfoList
            ).asFlow().mapToList(coroutineContext)
        }
    }

    internal fun insertUserInfo(
        item: UserInfoModel
    ) {
        db.dbQuery.transaction {
            item.run {
                db.dbQuery.insertUserInfo(
                    UserInfo(
                        Id.toLong(),
                        UserName,
                        UserEmail,
                        UserPassword,
                        CreateDate
                    )
                )
            }
        }
    }

    internal fun deleteUserInfo(
        userInfoModel: UserInfoModel?
    ) {
        db.dbQuery.transaction {
            if (userInfoModel == null) {
                db.dbQuery.deleteAllUserInfo()
            } else {
                db.dbQuery.deleteUserInfoById(userInfoModel.Id.toLong())
            }
        }
    }
}





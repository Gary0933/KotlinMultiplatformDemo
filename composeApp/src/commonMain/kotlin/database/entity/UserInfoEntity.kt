package database.entity

import database.DbEngine
import db.util.UserInfo


data class UserInfoModel(
    val Id: Int = 0,
    val UserName: String,
    val UserEmail: String,
    val UserPassword: String,
    val CreateDate: String? = null // 可以为空
)

class UserInfoHandler(
    private var db: DbEngine
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





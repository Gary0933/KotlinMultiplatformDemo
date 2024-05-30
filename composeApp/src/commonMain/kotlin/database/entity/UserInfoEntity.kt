package database.entity

import db.util.AppDatabaseQueries
import db.util.UserInfo

data class UserInfoModel(
    val Id: Int,
    val UserName: String,
    val UserEmail: String,
    val UserPassword: String,
    val CreateDate: String? // 可以为空
)

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
    dbQuery: AppDatabaseQueries,
    userInfoModel: UserInfoModel?
): List<UserInfoModel> {
    return if (userInfoModel == null) {
        dbQuery.getAllUserInfo(::mapUserInfoList).executeAsList()
    } else {
        dbQuery.getUserInfoById(
            userInfoModel.Id.toLong(),
            ::mapUserInfoList
        ).executeAsList()
    }
}

internal fun insertUserInfo(
    dbQuery: AppDatabaseQueries,
    item: UserInfoModel
) {
    item.run {
        dbQuery.insertUserInfo(
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

internal fun deleteUserInfo(
    dbQuery: AppDatabaseQueries,
    userInfoModel: UserInfoModel?
) {
    if (userInfoModel == null) {
        dbQuery.deleteAllUserInfo()
    }
    else {
        dbQuery.deleteUserInfoById(userInfoModel.Id.toLong())
    }
}



package business.data_state

import database.entity.UserInfoModel

sealed class DbOperationState<T> {
    data class OperateState<T> (var status: String): DbOperationState<T> ()
    data class InsertDataState<T> (var insertData: UserInfoModel): DbOperationState<T> ()
}
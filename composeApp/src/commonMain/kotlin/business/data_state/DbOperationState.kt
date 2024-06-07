package business.data_state

import database.entity.UserInfoModel

sealed class DbOperationState<T> {
    data class OperateState<T> (var status: Boolean): DbOperationState<T> ()
    data class InsertDataState<T> (var insertData: UserInfoModel): DbOperationState<T> ()
}
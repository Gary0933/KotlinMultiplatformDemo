package business.data_state


sealed class DbOperationState<T> {
    data class OperateStatus<T>(var inProcess: Boolean) : DbOperationState<T>()
    data class OperateData<T>(var data: Any) : DbOperationState<T>()
}
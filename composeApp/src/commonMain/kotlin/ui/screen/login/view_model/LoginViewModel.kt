package ui.screen.login.view_model

import androidx.lifecycle.ViewModel
import business.constants.DB_COROUTINE_CONTEXT
import business.data_state.DbOperationState
import business.data_state.ManageUiState
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class LoginViewModel(
    db: DbEngine,
): ViewModel() {

    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db) // 操作userinfo表的数据

    private val _loginUiState = MutableStateFlow(ManageUiState())
    var loginUiState: StateFlow<ManageUiState> = _loginUiState

    private val _registerUiState = MutableStateFlow(ManageUiState())
    var registerUiState: StateFlow<ManageUiState> = _registerUiState

    fun login (
        userName: String,
        userPassword: String
    ): Boolean {
       val userId: Int =  userInfoHandler.validateUserInfo(
           userName,
           userPassword
        )

        return if (userId != 0) {
            userInfoHandler.updateLoginStatus(1, userId)
            true
        } else {
            _loginUiState.value = _loginUiState.value.copy(
                showAlert = true,
                alertMessage = "Login failed"
            )
            false
        }
    }

    fun register(
        userInfoModel: UserInfoModel,
    ) {
        flow<DbOperationState<Any>> {
            emit(DbOperationState.OperateState(true)) // 状态更新,现在处于操作数据的阶段
            delay(2000) // 延迟2秒提交到数据库,模拟网络请求,测试异步
            emit(DbOperationState.InsertDataState(userInfoModel)) // 只用于打印数据
            userInfoHandler.insertUserInfo( // 数据插入数据库
                userInfoModel
            )
            emit(DbOperationState.OperateState(false)) // 状态更新,现在处于完整操作数据的阶段
        }.onEach {operationState -> // 实时获取flow里通过emit推送来的数据
            when(operationState) {
                is DbOperationState.OperateState -> {
                    _registerUiState.value = _registerUiState.value.copy(
                        showLoadingBar = operationState.status, // 根据数据库更新的状态,来改变是否显示loading的状态
                        enableTextField = !operationState.status, // 根据数据库更新的状态,来改变是否允许编辑TextField的状态
                        showAlert = !operationState.status, // 数据库更新结束后, 弹窗提示完成
                        alertMessage = "Register Successful"
                    )
                }
                is DbOperationState.InsertDataState -> {
                    println("Insert data: ${operationState.insertData}") //打印insert的数据
                }
            }
        }.onCompletion {cause -> // flow里的代码执行结束后执行这里
            if (cause == null) { // 没有错误出现
                println("Register done")
            } else {
                println("Error on register")
            }
        }.launchIn(CoroutineScope(DB_COROUTINE_CONTEXT)) // 通过创建一个携程来运行flow
    }

    suspend fun closeRegisterSuccessAlert() {
        delay(1500)
        _registerUiState.value = _registerUiState.value.copy(showAlert = false)
    }

    suspend fun closeLoginSuccessAlert() {
        delay(1500)
        _loginUiState.value = _loginUiState.value.copy(showAlert = false)
    }

    fun updateShowLoadingButtonStatus(show: Boolean) {
        _loginUiState.value = _loginUiState.value.copy(showLoadingBar = show)
    }

}
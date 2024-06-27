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

class LoginViewModel(db: DbEngine) : ViewModel() {

    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db) // 操作userinfo表的数据

    private val _loginUiState = MutableStateFlow(ManageUiState())
    var loginUiState: StateFlow<ManageUiState> = _loginUiState

    private val _registerUiState = MutableStateFlow(ManageUiState())
    var registerUiState: StateFlow<ManageUiState> = _registerUiState

    var registerResultState = MutableStateFlow(false)

    fun login(
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
        var alertMessage = ""
        registerResultState.value = false // 初始化注册结果的状态
        // 创建一个flow来发射数据(数据库操作的状态)
        flow<DbOperationState<Any>> {
            if (userInfoHandler.isExistUserEmail(userInfoModel.UserEmail)) { // 如果插入的数据中邮箱已存在，禁止插入
                alertMessage = "Register failed, user email already exist."
                emit(DbOperationState.OperateStatus(false)) // 发射数据,数据库操作结束
            } else {
                emit(DbOperationState.OperateStatus(true)) // 发射数据，数据库开始操作
                delay(2000) // 延迟2秒提交到数据库,模拟网络请求,测试异步
                emit(DbOperationState.OperateData(userInfoModel)) // 发射数据，插入的数据类

                userInfoHandler.insertUserInfo( // 数据插入数据库
                    userInfoModel
                )
                alertMessage = "Successfully registered."
                registerResultState.value = true
                emit(DbOperationState.OperateStatus(false)) // 发射数据,数据库操作结束
            }
        }.onEach {operationState -> // 对flow发射的数据在收集之前，进行业务处理
            when(operationState) {
                is DbOperationState.OperateStatus -> {
                    _registerUiState.value = _registerUiState.value.copy(
                        showLoadingBar = operationState.inProcess, // 根据数据库更新的状态，来改变是否显示loading的状态
                        enableTextField = !operationState.inProcess, // 根据数据库更新的状态，来改变是否允许编辑TextField的状态
                        showAlert = !operationState.inProcess, // 数据库更新结束后, 弹窗提示完成
                        alertMessage = alertMessage
                    )
                }
                is DbOperationState.OperateData -> {
                    println("Insert data: ${operationState.data}") //打印insert的数据
                }
            }
        }.onCompletion {cause -> // flow发射完所有的数据后执行这里
            if (cause == null) { // 没有错误出现
                println("Register done")
            } else {
                println("Error on register")
            }
        }.launchIn(CoroutineScope(DB_COROUTINE_CONTEXT)) // 启动一个携程来订阅并收集这个flow
    }

    suspend fun closeRegisterResultAlert() {
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
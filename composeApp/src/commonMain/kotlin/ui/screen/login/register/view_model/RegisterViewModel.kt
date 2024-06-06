package ui.screen.login.register.view_model

import androidx.lifecycle.ViewModel
import business.data_state.DbOperationState
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(
    db: DbEngine
): ViewModel() {

    private var coroutineContext: CoroutineContext = Dispatchers.IO
    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db) // 操作userinfo表的数据

    fun register(
        userInfoModel: UserInfoModel
    ) {
        flow<DbOperationState<Any>> {
            emit(DbOperationState.OperateState("Insert start"))
            delay(1000)
            emit(DbOperationState.InsertDataState(userInfoModel))
            delay(20000) // 延迟30秒提交到数据库，测试异步
            userInfoHandler.insertUserInfo(
                userInfoModel
            )
            emit(DbOperationState.OperateState("Insert completed"))
            delay(1000)
        }.onEach {operationState -> // 实时获取flow里通过emit推送来的数据
            when(operationState) {
                is DbOperationState.OperateState -> {
                    println(operationState.status)
                }
                is DbOperationState.InsertDataState -> {
                    println(operationState.insertData)
                }
            }
        }.onCompletion {cause ->
            if (cause == null) {
                println("Register done") // flow里的代码执行结束后执行这里
            } else {
                println("Error on register")
            }
        }.launchIn(CoroutineScope(coroutineContext)) // 通过创建一个携程来运行flow
    }

}
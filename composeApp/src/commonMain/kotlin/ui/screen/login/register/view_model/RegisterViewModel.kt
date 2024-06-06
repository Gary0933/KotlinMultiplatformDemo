package ui.screen.login.register.view_model

import androidx.lifecycle.ViewModel
import common.DatabaseDriverFactory
import database.DbEngine
import database.entity.UserInfoHandler
import database.entity.UserInfoModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(
    //db: DbEngine
): ViewModel() {

    //private var coroutineContext: CoroutineContext = Dispatchers.IO
    var db: DbEngine = DbEngine.instance
    private var userInfoHandler: UserInfoHandler = UserInfoHandler(db) // 操作userinfo表的数据

    fun register(
        userInfoModel: UserInfoModel
    ) {
        //doRegisterAsFlow(userInfoModel).launchIn(CoroutineScope(coroutineContext))
        userInfoHandler.insertUserInfo(userInfoModel)
    }

    /*
    private fun doRegisterAsFlow(
        userInfoModel: UserInfoModel
    ): Flow<String> = flow {
        delay(30000) // 延迟30秒提交到数据库，测试异步
        userInfoHandler.insertUserInfo(
            userInfoModel
        )
    }

     */
}
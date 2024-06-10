package di

import common.DatabaseDriverFactory
import database.DbEngine
import org.koin.dsl.module
import ui.screen.login.view_model.LoginViewModel
import ui.screen.main.profile.show_users.view_model.ShowUsersViewModel


fun appModule() = module {

    single {
        DbEngine(databaseDriverFactory = get())
    }
    single {
        DatabaseDriverFactory()
    }
    factory { LoginViewModel(db = get()) }
    factory { ShowUsersViewModel(db = get()) }
}
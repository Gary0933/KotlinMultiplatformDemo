package di

import common.DatabaseDriverFactory
import database.DbEngine
import ui.screen.login.register.view_model.RegisterViewModel
import ui.screen.main.profile.show_users.view_model.ShowUsersViewModel

/*
fun appModule() = module {


    single {
        DbEngine(databaseDriverFactory = get())
    }
    single {
        DatabaseDriverFactory()
    }
    factory { RegisterViewModel(db = get()) }
    factory { ShowUsersViewModel(db = get()) }
}

 */
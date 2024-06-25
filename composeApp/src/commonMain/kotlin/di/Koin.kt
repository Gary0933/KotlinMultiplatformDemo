package di

import common.DatabaseDriverFactory
import database.DbEngine
import org.koin.dsl.module
import ui.screen.login.view_model.LoginViewModel
import ui.screen.main.MainViewModel
import ui.screen.main.cart.view_model.CartViewModel
import ui.screen.main.home.view_model.HomeViewModel
import ui.screen.main.profile.settings.view_model.SettingViewModel
import ui.screen.main.profile.show_users.view_model.ShowUsersViewModel
import ui.screen.main.profile.view_model.ProfileViewModel


fun appModule() = module {

    single {
        DbEngine(databaseDriverFactory = get())
    }
    single {
        DatabaseDriverFactory()
    }
    factory { LoginViewModel(db = get()) }
    factory { ShowUsersViewModel(db = get()) }
    factory { HomeViewModel() }
    factory { CartViewModel() }
    factory { SettingViewModel(db = get()) }
    factory { ProfileViewModel(db = get()) }
    single {
        MainViewModel()
    }
}
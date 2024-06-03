package di

import common.DatabaseDriverFactory
import database.DbEngine
import org.koin.dsl.module


fun appModule() = module {

    single {
        DbEngine(databaseDriverFactory = get())
    }
    single {
        DatabaseDriverFactory()
    }
}
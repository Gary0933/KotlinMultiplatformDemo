package database

import common.DatabaseDriverFactory
import db.util.AppDatabase

class DbEngine(databaseDriverFactory: DatabaseDriverFactory) {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DbEngine(DatabaseDriverFactory())
        }
    }

    private val database = AppDatabase(databaseDriverFactory.createDriver())
    val dbQuery = database.appDatabaseQueries
}
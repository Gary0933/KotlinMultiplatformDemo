package database

import common.DatabaseDriverFactory
import db.util.AppDatabase

internal class DbEngine(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    val dbQuery = database.appDatabaseQueries
}
package database

import common.createDriver
import db.util.AppDatabase

internal class DbEngine {
    private val database = AppDatabase(createDriver())
    val dbQuery = database.appDatabaseQueries
}
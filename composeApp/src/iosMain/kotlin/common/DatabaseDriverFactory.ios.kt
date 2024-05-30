package common

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import db.util.AppDatabase

actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(AppDatabase.Schema, "kmp.db")
}
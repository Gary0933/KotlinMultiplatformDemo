package common

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.blankj.utilcode.util.ActivityUtils
import db.util.AppDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            AppDatabase.Schema,
            ActivityUtils.getTopActivity(), // 利用第三方插件获取android的Context
            "kmd.db",
        )
    }
}

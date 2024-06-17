import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.sqldelight) // SQLDelight
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            //implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.compose.navigation) // 导航(route配置)
            implementation(compose.material3) // UI库
            api(libs.coil3) // 图片处理
            api(libs.koin.core) // 依赖注入框架
            api(libs.koin.compose) // 依赖注入框架对于compose的扩展
            implementation(libs.sqldelight.runtime) // SQLDelight
            implementation(libs.sqldelight.coroutines) // SQLDelight
            implementation(libs.kotlinx.coroutines.core) // 携程支持
            implementation(libs.qr.code) // qr 扫描
        }

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.system.ui.controller)
            implementation(libs.sqldelight.android.driver) // SQLDelight
            api(libs.blankj.utilcode) // 获取android context用的到
            implementation(libs.kotlinx.coroutines.andriod) // 携程支持

        }

        iosMain.dependencies {
            implementation(libs.sqldelight.native.driver) // SQLDelight
            implementation(libs.stately.common) // 修复SQLDelight在IOS编译时报错 Could not find "co.touchlab:stately-common"
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.sqldelight.desktop.driver) // SQLDelight
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") { // 自定义数据库名
            packageName.set("db.util") // 自定义包名
        }
    }
}

android {
    namespace = "org.example.demo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    //sourceSets["main"].res.srcDirs("src/commonMain/composeResources", "src/androidMain/res") // 设置资源文件路径

    defaultConfig {
        applicationId = "org.example.demo"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.demo"
            packageVersion = "1.0.0"
            modules("java.sql") // SQLDelight
        }
    }
}

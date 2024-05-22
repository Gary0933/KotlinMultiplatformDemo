package di

import org.koin.dsl.module


class ComponentA()

fun appModule() = module {
    single { ComponentA() }
}
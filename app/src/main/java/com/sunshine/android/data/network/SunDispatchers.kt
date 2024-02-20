package com.sunshine.android.data.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val sunDispatcher: SunDispatchers)

enum class SunDispatchers {
    Default, IO,
}

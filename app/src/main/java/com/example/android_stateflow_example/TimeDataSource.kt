package com.example.android_stateflow_example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TimeDataSource(private val refreshIntervalMs: Long = 1000) {

    val latestTime: Flow<Long> = flow {
        while (true) {
            val latestTime = System.currentTimeMillis()
            emit(latestTime) // Emits the result of the request to the flow
            delay(refreshIntervalMs) // Suspends the coroutine for some time
        }
    }

}
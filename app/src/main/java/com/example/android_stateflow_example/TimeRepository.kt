package com.example.android_stateflow_example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TimeRepository {

    private val timeDataSource: TimeDataSource = TimeDataSource()

    val time: Flow<Long> = timeDataSource.latestTime
            .map { value: Long -> value } // Intermediate operation to filter the list of favorite topics
//            .onEach { l: Long ->  saveInCache(l) } // Intermediate operation to save the latest news in the cache
}
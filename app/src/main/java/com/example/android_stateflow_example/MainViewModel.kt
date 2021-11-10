package com.example.android_stateflow_example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val timeRepository: TimeRepository = TimeRepository()

    private val _uiState = MutableStateFlow(LatestTimeUiState.Success(0)) // Backing property to avoid state updates from other classes
    val uiState: StateFlow<LatestTimeUiState> = _uiState // The UI collects from this StateFlow to get its state updates

    init {
        viewModelScope.launch {
            timeRepository.time
                    // Update View with the latest time
                    // Writes to the value property of MutableStateFlow,
                    // adding a new element to the flow and updating all
                    // of its collectors
                    .collect { value ->
                        _uiState.value = LatestTimeUiState.Success(value)
                    }
        }
    }
}

// Represents different states for the LatestTime screen
sealed class LatestTimeUiState {
    data class Success(val time: Long) : LatestTimeUiState()
    data class Error(val exception: Throwable) : LatestTimeUiState()
}
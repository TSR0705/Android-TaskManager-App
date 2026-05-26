package com.example.taskmanager.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI state for the home screen.
 */
data class HomeUiState(
    val tasks: List<Task> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val error: String? = null
)

/**
 * ViewModel for the home screen that manages task listing, searching,
 * and task completion toggling.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    private var latestQuery = ""

    init {
        refreshTasks()
    }

    /**
     * Loads or refreshes the task list based on the current search query.
     */
    private fun refreshTasks() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val flow = if (latestQuery.isBlank()) {
                repository.getAllTasks()
            } else {
                repository.searchTasks(latestQuery)
            }
            flow.collectLatest { tasks ->
                _uiState.value = HomeUiState(
                    tasks = tasks,
                    searchQuery = latestQuery,
                    isLoading = false
                )
            }
        }
    }

    /**
     * Updates the current search query which triggers a re-filtered list.
     */
    fun onSearchQueryChange(query: String) {
        latestQuery = query
        refreshTasks()
    }

    /**
     * Toggles the completion status of a task.
     */
    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            try {
                repository.toggleTaskCompletion(task.id)
            } catch (_: Exception) {
                // Silently fail; Room triggers flow update on next emit
            }
        }
    }

    /**
     * Deletes a task from the database.
     */
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.deleteTask(task)
            } catch (_: Exception) {
                // Silently fail; flow will refresh on next emission
            }
        }
    }

    /**
     * Deletes all tasks. Use with caution.
     */
    fun deleteAllTasks() {
        viewModelScope.launch {
            try {
                repository.deleteAllTasks()
            } catch (_: Exception) {
                _uiState.value = _uiState.value.copy(error = "Failed to delete all tasks")
            }
        }
    }
}

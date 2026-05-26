package com.example.taskmanager.ui.screens.addedit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.data.model.TaskPriority
import com.example.taskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI state for the add/edit screen.
 */
data class AddEditUiState(
    val title: String = "",
    val description: String = "",
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val error: String? = null,
    val isEditMode: Boolean = false,
    val saveComplete: Boolean = false
)

/**
 * ViewModel for the add/edit task screen.
 */
@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: TaskRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddEditUiState(isLoading = true))
    val uiState: StateFlow<AddEditUiState> = _uiState

    private var taskId: Long? = null

    init {
        val id = savedStateHandle.get<Long>("taskId")
        if (id != null && id != -1L) {
            taskId = id
            _uiState.value = _uiState.value.copy(isEditMode = true)
            loadTask(id)
        } else {
            _uiState.value = AddEditUiState(isLoading = false)
        }
    }

    private fun loadTask(id: Long) {
        viewModelScope.launch {
            try {
                val task = repository.getTaskById(id)
                task?.let {
                    _uiState.value = _uiState.value.copy(
                        title = it.title,
                        description = it.description,
                        priority = it.priority,
                        isLoading = false
                    )
                } ?: run {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Task not found"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun onTitleChange(value: String) {
        _uiState.value = _uiState.value.copy(title = value, error = null)
    }

    fun onDescriptionChange(value: String) {
        _uiState.value = _uiState.value.copy(description = value)
    }

    fun onPriorityChange(value: TaskPriority) {
        _uiState.value = _uiState.value.copy(priority = value)
    }

    fun saveTask() {
        val state = _uiState.value
        if (state.title.isBlank()) {
            _uiState.value = state.copy(error = "Title is required")
            return
        }

        _uiState.value = state.copy(isSaving = true)
        viewModelScope.launch {
            try {
                val task = Task(
                    id = taskId ?: 0L,
                    title = state.title.trim(),
                    description = state.description.trim(),
                    priority = state.priority
                )
                if (state.isEditMode && taskId != null) {
                    repository.updateTask(task)
                } else {
                    repository.insertTask(task)
                }
                _uiState.value = _uiState.value.copy(isSaving = false, saveComplete = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSaving = false,
                    error = e.message ?: "Failed to save task"
                )
            }
        }
    }
}

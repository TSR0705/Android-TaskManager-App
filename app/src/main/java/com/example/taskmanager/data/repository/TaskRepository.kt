package com.example.taskmanager.data.repository

import com.example.taskmanager.data.local.TaskDao
import com.example.taskmanager.data.local.toEntity
import com.example.taskmanager.data.local.toModel
import com.example.taskmanager.data.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for task-related data operations.
 *
 * Acts as a single source of truth for task data and mediates between
data layers (Room database) and the ViewModel layer.

 * All Room operations are exposed as coroutine-based functions and Flows
 * so the UI can observe reactive changes automatically.
 */
@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    /**
     * Retrieves all tasks as a reactive Flow.
     * Emissions are automatically triggered on database changes.
     */
    fun getAllTasks(): Flow<List<Task>> =
        taskDao.getAllTasks().map { entities ->
            entities.map { it.toModel() }
        }

    /**
     * Retrieves a single task by ID. Returns null if not found.
     */
    suspend fun getTaskById(taskId: Long): Task? =
        taskDao.getTaskById(taskId)?.toModel()

    /**
     * Inserts a new task and returns its generated ID.
     */
    suspend fun insertTask(task: Task): Long =
        taskDao.insertTask(task.toEntity())

    /**
     * Updates an existing task.
     */
    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task.toEntity())
    }

    /**
     * Deletes a task from the database.
     */
    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task.toEntity())
    }

    /**
     * Deletes a task by its ID.
     */
    suspend fun deleteTaskById(taskId: Long) {
        taskDao.deleteTaskById(taskId)
    }

    /**
     * Searches tasks matching the given query in title or description.
     */
    fun searchTasks(query: String): Flow<List<Task>> =
        taskDao.searchTasks(query).map { entities ->
            entities.map { it.toModel() }
        }

    /**
     * Toggles the completion status of a task.
     */
    suspend fun toggleTaskCompletion(taskId: Long) {
        taskDao.toggleTaskCompletion(taskId)
    }

    /**
     * Returns the total number of tasks.
     */
    suspend fun getTaskCount(): Int =
        taskDao.getTaskCount()

    /**
     * Deletes all tasks. Use with caution.
     */
    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }
}

package com.example.taskmanager.data.model

/**
 * Domain model representing a single task.
 *
 * This is the clean domain model used throughout the UI and ViewModel layers.
 * Room entities are mapped to and from this model to keep the domain layer
 * independent from the data persistence layer.
 *
 * @property id Unique identifier for the task. Auto-generated if 0L.
 * @property title The task title.
 * @property description Optional description for the task.
 * @property priority Priority level of the task.
 * @property isCompleted Whether the task has been marked as done.
 * @property createdAt Timestamp of when the task was created (epoch milliseconds).
 * @property dueDate Optional due date in epoch milliseconds.
 */
data class Task(
    val id: Long = 0L,
    val title: String,
    val description: String = "",
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val dueDate: Long? = null
)

/**
 * Priority levels for tasks. Ordered from lowest to highest priority.
 */
enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH
}

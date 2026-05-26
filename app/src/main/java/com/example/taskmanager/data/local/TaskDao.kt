package com.example.taskmanager.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for task-related database operations.
 *
 * Provides CRUD operations and search functionality using Room's
 * coroutine-friendly Flow return types.
 */
@Dao
interface TaskDao {

    /**
     * Retrieves all tasks ordered by creation date descending (newest first).
     */
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    /**
     * Retrieves a single task by its ID. Returns null if not found.
     */
    @Query("SELECT * FROM tasks WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Long): TaskEntity?

    /**
     * Inserts a new task. Returns the generated row ID.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    /**
     * Updates an existing task.
     */
    @Update
    suspend fun updateTask(task: TaskEntity)

    /**
     * Deletes a task from the database.
     */
    @Delete
    suspend fun deleteTask(task: TaskEntity)

    /**
     * Deletes a task by its ID.
     */
    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Long)

    /**
     * Searches tasks by title or description.
     * Uses SQL LIKE for case-insensitive matching.
     */
    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun searchTasks(query: String): Flow<List<TaskEntity>>

    /**
     * Retrieves only the completed tasks.
     */
    @Query("SELECT * FROM tasks WHERE isCompleted = 1 ORDER BY createdAt DESC")
    fun getCompletedTasks(): Flow<List<TaskEntity>>

    /**
     * Retrieves only the pending (not completed) tasks.
     */
    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY createdAt DESC")
    fun getPendingTasks(): Flow<List<TaskEntity>>

    /**
     * Toggles the completion status of a task by its ID.
     */
    @Query("UPDATE tasks SET isCompleted = CASE WHEN isCompleted = 1 THEN 0 ELSE 1 END WHERE id = :taskId")
    suspend fun toggleTaskCompletion(taskId: Long)

    /**
     * Returns the total count of tasks.
     */
    @Query("SELECT COUNT(*) FROM tasks")
    suspend fun getTaskCount(): Int

    /**
     * Deletes all tasks from the database. Use with caution.
     */
    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
}

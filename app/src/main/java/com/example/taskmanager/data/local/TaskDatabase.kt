package com.example.taskmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room database for the TaskManager app.
 *
 * Holds the local SQLite database and provides access to the [TaskDao].
 * Version is incremented whenever the schema changes.
 */
@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {

    /**
     * Provides the DAO for task-related database operations.
     */
    abstract fun taskDao(): TaskDao

    companion object {
        const val DATABASE_NAME = "task_database"
    }
}

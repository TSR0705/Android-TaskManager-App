package com.example.taskmanager.data.di

import android.content.Context
import androidx.room.Room
import com.example.taskmanager.data.local.TaskDao
import com.example.taskmanager.data.local.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides Room database and DAO instances.
 *
 * The database is a singleton living for the entire app lifecycle.
 * Uses destructive migration to handle schema changes during development.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(
        @ApplicationContext context: Context
    ): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        )
            // Recreate database on schema changes. For production,
            // use proper Room migrations instead.
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: TaskDatabase): TaskDao {
        return database.taskDao()
    }
}

package com.example.taskmanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the TaskManager app.
 *
 * Annotated with [HiltAndroidApp] to set up Hilt for dependency injection
 * across the entire application lifecycle.
 */
@HiltAndroidApp
class TaskApp : Application()

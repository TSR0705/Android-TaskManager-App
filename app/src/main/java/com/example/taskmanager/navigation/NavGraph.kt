package com.example.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.taskmanager.ui.screens.addedit.AddEditScreen
import com.example.taskmanager.ui.screens.home.HomeScreen
import com.example.taskmanager.ui.screens.settings.SettingsScreen
import com.example.taskmanager.ui.screens.splash.SplashScreen

/**
 * Route definitions for all destinations in the app.
 *
 * Using a sealed class with route strings ensures type safety when navigating.
 */
sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object AddEdit : Screen("add_edit/{taskId}") {
        fun createRoute(taskId: Long = -1L) = "add_edit/$taskId"
    }
    data object Settings : Screen("settings")
}

/**
 * Navigation graph for the TaskManager app.
 *
 * Defines all composable destinations and their routes using Jetpack Navigation.
 */
@Composable
fun TaskNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onTimeout = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToAddEdit = { taskId ->
                    navController.navigate(Screen.AddEdit.createRoute(taskId))
                },
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(
            route = Screen.AddEdit.route,
            arguments = listOf(
                navArgument("taskId") { type = NavType.LongType; defaultValue = -1L }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getLong("taskId") ?: -1L
            AddEditScreen(
                taskId = if (taskId == -1L) null else taskId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

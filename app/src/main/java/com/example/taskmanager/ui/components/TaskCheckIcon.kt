package com.example.taskmanager.ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Custom vector icon used in the app for the app logo and completed task indicator.
 */
val TaskCheckIcon: ImageVector = ImageVector.Builder(
    name = "TaskCheck",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(Color.Black),
        pathFillType = PathFillType.NonZero
    ) {
        // Checkmark icon with circular background suggestion
        moveTo(12f, 2f)
        curveTo(6.48f, 2f, 2f, 6.48f, 2f, 12f)
        reflectiveCurveTo(6.48f, 22f, 12f, 22f)
        reflectiveCurveTo(22f, 17.52f, 22f, 12f)
        reflectiveCurveTo(17.52f, 2f, 12f, 2f)
        close()
        moveTo(10f, 17f)
        lineTo(5f, 12f)
        lineTo(6.41f, 10.59f)
        lineTo(10f, 14.17f)
        lineTo(17.59f, 6.58f)
        lineTo(19f, 8f)
        lineTo(10f, 17f)
        close()
    }
}.build()

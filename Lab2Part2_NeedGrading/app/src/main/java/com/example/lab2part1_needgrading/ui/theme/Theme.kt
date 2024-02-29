package com.example.lab2part1_needgrading.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define your color scheme here
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    // Assuming you want to set dark theme colors as well
    // Add other colors as needed for dark theme
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Black, // Text color on buttons and potentially app bar text, icons
    secondary = Color.White, // Button background color
    tertiary = Color(0xFFFFEB3B), // Your light yellow color for backgrounds
    // Other colors can be set as needed for your theme
    background = Color(0xFFFFEB3B), // Main background color
    surface = Color.White, // Cards, sheets, and menus background
    onPrimary = Color.White, // Content color on top of primary color
    onSecondary = Color.Black, // Content color on top of secondary color
    onTertiary = Color.Black, // Content color on top of tertiary color
    onBackground = Color.Black, // Text color on main background
    onSurface = Color.Black, // Text color on surface color
    // You can continue to override other colors as needed
)


@Composable
fun Lab2Part1_NeedGradingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
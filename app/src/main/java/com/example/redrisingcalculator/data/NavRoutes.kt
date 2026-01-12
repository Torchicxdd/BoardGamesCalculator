package com.example.redrisingcalculator.data

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes("home")
    data object Scores : NavRoutes("scores")
    data object Settings : NavRoutes("settings")
}

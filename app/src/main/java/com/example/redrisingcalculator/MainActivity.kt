package com.example.redrisingcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.redrisingcalculator.data.NavRoutes
import com.example.redrisingcalculator.screens.HomeScreen
import com.example.redrisingcalculator.screens.ScoreScreen
import com.example.redrisingcalculator.screens.SettingsScreen
import com.example.redrisingcalculator.ui.theme.RedRisingCalculatorTheme
import com.example.redrisingcalculator.viewmodel.RedRisingViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RedRisingCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val sharedViewModel: RedRisingViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.Home.route
                    ) {
                        composable(NavRoutes.Home.route) {
                            HomeScreen(onButtonClicked = { numPlayers ->
                                sharedViewModel.clearPlayers()
                                repeat(numPlayers) {
                                    sharedViewModel.addPlayer()
                                }
                                navController.navigate(NavRoutes.Scores.route)
                            })
                        }

                        composable(NavRoutes.Scores.route) {
                            ScoreScreen(
                                sharedViewModel.playersUI,
                                onCalculatePlayerScores = { sharedViewModel.calculatePlayerScores() },
                                onSettingsClicked = { navController.navigate(NavRoutes.Settings.route) },
                                onCalculateInfluence = { sharedViewModel.calculateInfluence() },
                                onSovereigntyChecked = { player, isChecked ->
                                    sharedViewModel.onSovereigntyChecked(player, isChecked)
                                }
                            )
                        }

                        composable(NavRoutes.Settings.route) {
                            SettingsScreen(
                                sharedViewModel.playersUI,
                                onBackClicked = { navController.popBackStack() },
                                onAddPlayerClicked = { sharedViewModel.addPlayer() },
                                onDeletePlayer = { playerIndex ->
                                    sharedViewModel.removePlayer(playerIndex)
                                },
                                onPlayerNameChanged = { playerIndex, newName ->
                                    sharedViewModel.updatePlayerName(playerIndex, newName)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
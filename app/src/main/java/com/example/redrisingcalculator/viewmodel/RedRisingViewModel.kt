package com.example.redrisingcalculator.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.redrisingcalculator.ui.player.PlayerUiState

class RedRisingViewModel : ViewModel() {
    private val _playersUI = mutableStateListOf<PlayerUiState>()
    val playersUI: List<PlayerUiState> = _playersUI

    fun addPlayer() {
        _playersUI.add(PlayerUiState("Player ${_playersUI.size + 1}"))
    }

    fun removePlayer(playerIndex: Int) {
        if (_playersUI.size > 1) {
            _playersUI.removeAt(playerIndex)
        }
    }

    fun clearPlayers() {
        _playersUI.clear()
    }

    fun updatePlayerName(playerIndex: Int, newName: String) {
        _playersUI[playerIndex].name = newName
    }

    fun calculatePlayerScores() {
        _playersUI.forEach {
            it.calculateTotalScore()
        }
    }

    fun calculateInfluence() {
        val sortedPlayers = _playersUI.sortedByDescending { it.influence.amount }
        var currentRank = 0
        var lastAmount: Int? = null

        sortedPlayers.forEachIndexed { index, player ->
            currentRank = if (lastAmount == player.influence.amount) currentRank else index
            player.influence.playerPosition = currentRank
            player.influence.calculate()
            lastAmount = player.influence.amount
        }
    }

    fun onSovereigntyChecked(player: PlayerUiState, isChecked: Boolean) {
        if (isChecked) {
            _playersUI.forEach {
                it.sovereignty.hasSovereignty = false
                it.sovereignty.calculate()
            }
        }
        player.sovereignty.hasSovereignty = isChecked
        player.sovereignty.calculate()
    }
}
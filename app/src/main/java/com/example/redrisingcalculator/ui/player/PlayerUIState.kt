package com.example.redrisingcalculator.ui.player

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.redrisingcalculator.data.ScoreConditions

class PlayerUiState(
    name: String,
    endGameAbilities: ScoreConditions.ScoreConditions.EndGameAbilities = ScoreConditions.ScoreConditions.EndGameAbilities(),
    characterCards: ScoreConditions.ScoreConditions.CharacterCards = ScoreConditions.ScoreConditions.CharacterCards(),
    fleetTrack: ScoreConditions.ScoreConditions.FleetTrack = ScoreConditions.ScoreConditions.FleetTrack(),
    helium: ScoreConditions.ScoreConditions.Helium = ScoreConditions.ScoreConditions.Helium(),
    sovereignty: ScoreConditions.ScoreConditions.Sovereignty = ScoreConditions.ScoreConditions.Sovereignty(),
    influence: ScoreConditions.ScoreConditions.Influence = ScoreConditions.ScoreConditions.Influence(),
    excessCards: ScoreConditions.ScoreConditions.ExcessCards = ScoreConditions.ScoreConditions.ExcessCards(),
    score: Int = 0
) {
    var name by mutableStateOf(name)
    var endGameAbilities by mutableStateOf(endGameAbilities)
    var characterCards by mutableStateOf(characterCards)
    var fleetTrack by mutableStateOf(fleetTrack)
    var helium by mutableStateOf(helium)
    var sovereignty by mutableStateOf(sovereignty)
    var influence by mutableStateOf(influence)
    var excessCards by mutableStateOf(excessCards)
    var score by mutableStateOf(score)

    fun calculateTotalScore() {
        score = endGameAbilities.score +
                characterCards.score +
                fleetTrack.score +
                helium.score +
                sovereignty.score +
                influence.score +
                excessCards.score
    }
}
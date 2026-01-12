package com.example.redrisingcalculator.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.redrisingcalculator.R

class ScoreConditions {
    abstract class ScoreCondition {
        var amount by mutableStateOf(0)
        var score: Int = 0
        abstract var iconRes: Int
        abstract var textRes: Int
        abstract fun calculate()
    }

    object ScoreConditions {
        class EndGameAbilities : ScoreCondition() {
            override var iconRes: Int
                get() = R.drawable.abilities_ic
                set(value) {}
            override var textRes: Int
                get() = R.string.endgame_abilities
                set(value) {}

            override fun calculate() {
                score = amount
            }
        }

        class CharacterCards : ScoreCondition() {
            override var iconRes: Int
                get() = R.drawable.character_cards_ic
                set(value) {}
            override var textRes: Int
                get() = R.string.character_cards
                set(value) {}

            override fun calculate() {
                score = amount
            }
        }

        class FleetTrack : ScoreCondition() {
            override var iconRes: Int
                get() = R.drawable.fleet_ic
                set(value) {}
            override var textRes: Int
                get() = R.string.fleet_track
                set(value) {}

            override fun calculate() {
                score = amount
            }
        }

        class Helium : ScoreCondition() {
            override var iconRes: Int
                get() = R.drawable.helium_ic
                set(value) {}
            override var textRes: Int
                get() = R.string.helium
                set(value) {}

            override fun calculate() {
                score = amount * 3
            }
        }

        class Sovereignty : ScoreCondition() {
            override var iconRes: Int
                get() = R.drawable.sovereignty_ic
                set(value) {}
            override var textRes: Int
                get() = R.string.sovereignty
                set(value) {}

            var hasSovereignty by mutableStateOf(false)

            override fun calculate() {
                score = if (hasSovereignty) {
                    10
                } else {
                    0
                }
            }
        }

        class Influence : ScoreCondition() {
            override var iconRes: Int
                get() = R.drawable.influence_ic
                set(value) {}
            override var textRes: Int
                get() = R.string.influence
                set(value) {}

            var playerPosition by mutableStateOf(3)

            override fun calculate() {
                score = when (playerPosition) {
                    0 -> amount * 4
                    1 -> amount * 2
                    2 -> amount
                    else -> 0
                }
            }
        }

        class ExcessCards : ScoreCondition() {
            override var iconRes: Int
                get() = R.drawable.excess_cards_ic
                set(value) {}
            override var textRes: Int
                get() = R.string.excess_cards
                set(value) {}

            override fun calculate() {
                if (amount > 7) {
                    score = (amount - 7) * -10
                }
                score = 0
            }
        }
    }
}
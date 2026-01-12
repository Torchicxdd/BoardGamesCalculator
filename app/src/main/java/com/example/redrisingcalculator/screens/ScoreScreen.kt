package com.example.redrisingcalculator.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.redrisingcalculator.R
import com.example.redrisingcalculator.composables.PlayerCard
import com.example.redrisingcalculator.composables.ScoreCheckbox
import com.example.redrisingcalculator.composables.ScoreInput
import com.example.redrisingcalculator.ui.player.PlayerUiState

@Composable
fun ScoreScreen(
    players: List<PlayerUiState>,
    onCalculatePlayerScores: () -> Unit,
    onSettingsClicked: () -> Unit,
    onCalculateInfluence: () -> Unit,
    onSovereigntyChecked: (player: PlayerUiState, isChecked: Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.player_scores).uppercase(),
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onSettingsClicked
                ) {
                    Icon(
                        painter = painterResource(R.drawable.settings_ic),
                        contentDescription = "Settings Button",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            players.forEach { player ->
                item {
                    PlayerCard(player) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            ScoreInput(player.endGameAbilities) {
                                player.endGameAbilities.calculate()
                                onCalculatePlayerScores()
                            }
                            ScoreInput(player.characterCards) {
                                player.characterCards.calculate()
                                onCalculatePlayerScores()
                            }
                            ScoreInput(player.fleetTrack) {
                                player.fleetTrack.calculate()
                                onCalculatePlayerScores()
                            }
                            ScoreInput(player.helium) {
                                player.helium.calculate()
                                onCalculatePlayerScores()
                            }
                            ScoreCheckbox(
                                player.sovereignty,
                                player.sovereignty.hasSovereignty
                            ) { isChecked ->
                                onSovereigntyChecked(player, isChecked)
                                onCalculatePlayerScores()
                            }
                            ScoreInput(player.influence) {
                                onCalculateInfluence()
                                onCalculatePlayerScores()
                            }
                            ScoreInput(player.excessCards) {
                                player.excessCards.calculate()
                                onCalculatePlayerScores()
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}
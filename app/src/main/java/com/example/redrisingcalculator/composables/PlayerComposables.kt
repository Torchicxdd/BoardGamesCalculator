package com.example.redrisingcalculator.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.redrisingcalculator.R
import com.example.redrisingcalculator.data.ScoreConditions
import com.example.redrisingcalculator.ui.player.PlayerUiState
import com.example.redrisingcalculator.ui.theme.LocalExtraColors

@Composable
fun PlayerCard(
    player: PlayerUiState,
    expandedContent: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = LocalExtraColors.current.tertiaryBorder,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(16.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    expanded = !expanded
                }
        ) {
            Text(
                text = player.name,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onTertiary
            )

            Text(
                text = "${player.score} ${stringResource(R.string.points).uppercase()}",
                modifier = Modifier.padding(end = 8.dp)
            )

            Icon(
                painter = painterResource(if (expanded) R.drawable.collapse_ic else R.drawable.expand_ic),
                contentDescription = "Expand/Collapse",
                modifier = Modifier.size(12.dp)
            )
        }

        if (expanded) {
            expandedContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreInput(
    condition: ScoreConditions.ScoreCondition,
    onValueChanged: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = condition.iconRes),
            contentDescription = "Score Condition Icon",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
                .offset(y = 4.dp)
        )

        OutlinedTextField(
            value = if (condition.amount == 0) "" else condition.amount.toString(),
            onValueChange = { newText ->
                if (newText.isEmpty() || newText.all { it.isDigit() }) {
                    condition.amount = newText.toIntOrNull() ?: 0
                    onValueChanged()
                }
            },
            label = {
                Text(
                    text = stringResource(condition.textRes),
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = LocalExtraColors.current.tertiaryBorder
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ScoreCheckbox(
    condition: ScoreConditions.ScoreCondition,
    isChecked: Boolean,
    onCheckedChanged: (isChecked: Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .offset(y = 4.dp)
    ) {
        Icon(
            painter = painterResource(id = condition.iconRes),
            contentDescription = "Score Condition Icon",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
        )

        Checkbox(
            checked = isChecked,
            onCheckedChanged
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerInputs(
    playerName: String,
    onTextChanged: (String) -> Unit,
    onPlayerDeleted: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = playerName,
            onValueChange = onTextChanged,
            label = { Text(stringResource(R.string.player_name)) },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = LocalExtraColors.current.tertiaryBorder
            ),
            modifier = Modifier
                .weight(1f)
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .offset(y = 4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.tertiary)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onPlayerDeleted()
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_close_24),
                contentDescription = "Delete Player Icon",
                tint = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
package com.example.redrisingcalculator.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.redrisingcalculator.R
import com.example.redrisingcalculator.ui.theme.RedRisingCalculatorTheme
import com.example.redrisingcalculator.ui.theme.TextColourSecondaryFixed

@Composable
fun HomeScreen(
    onButtonClicked: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var numberText by remember { mutableStateOf("3") }

        Image(
            painter = painterResource(id = R.drawable.home_background_image),
            contentDescription = "Home Red Rising Background",
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.red_rising).uppercase(),
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = stringResource(id = R.string.calculator).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = TextColourSecondaryFixed
            )

            Spacer(modifier = Modifier.height(64.dp))

            OutlinedTextField(
                value = numberText,
                onValueChange = { newText ->
                    // Only allow digits
                    if (newText.all { it.isDigit() }) {
                        numberText = newText
                    }
                },
                label = { Text(stringResource(id = R.string.num_players)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val number = numberText.toIntOrNull() ?: 0
                    onButtonClicked(number)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.create_new_game),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(96.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RedRisingCalculatorTheme {
        HomeScreen({})
    }
}
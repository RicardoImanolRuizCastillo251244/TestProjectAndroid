package com.example.testappmovil.ui.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaySelector(selectedDay: String, onDaySelected: (String) -> Unit) {
    val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedDay,
            onValueChange = {},
            readOnly = true,
            label = { Text("Día") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            dias.forEach { dia ->
                DropdownMenuItem(
                    text = { Text(dia) },
                    onClick = {
                        onDaySelected(dia)
                        expanded = false
                    }
                )
            }
        }
    }
}

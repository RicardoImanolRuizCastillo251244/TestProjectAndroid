package com.example.testprojectmovil.ui.schedule

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.testprojectmovil.ui.components.*

@Composable
fun ScheduleScreen() {
    var showDialog by remember { mutableStateOf(false) }
    val hours = (8..16).map { "${if (it < 10) "0$it" else it}:00" }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir materia")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Mi Horario Escolar",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(hours) { hour ->
                    TimeSlotRow(hour = hour) {
                        // Aquí es donde en el futuro pondremos la SubjectCard
                        // si el ID de la materia coincide con la hora
                    }
                }
            }
        }

        if (showDialog) {
            AddSubjectDialog(
                onDismiss = { showDialog = false },
                onConfirm = { name, prof ->
                    println("Materia añadida: $name de $prof")
                    showDialog = false
                }
            )
        }
    }
}
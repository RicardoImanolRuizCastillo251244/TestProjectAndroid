package com.example.testappmovil.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testappmovil.ui.viewmodel.HorarioViewModel
import com.example.testappmovil.ui.viewmodel.MateriaViewModel
import com.example.testappmovil.ui.viewmodel.ProfesorViewModel

@Composable
fun HorarioSemanalScreen(
    horarioViewModel: HorarioViewModel,
    materiaViewModel: MateriaViewModel,
    profesorViewModel: ProfesorViewModel,
    onNavigateToAddHorario: () -> Unit,
    onNavigateToDetalleMateria: (Long) -> Unit,
    onNavigateToGestion: () -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) }
    val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")

    val horarios by horarioViewModel.horarios.collectAsState()
    val materias by materiaViewModel.materias.collectAsState()
    val profesores by profesorViewModel.profesores.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAddHorario) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Button(onClick = onNavigateToGestion) {
                Text("Gestionar Datos")
            }
            TabRow(selectedTabIndex = tabIndex) {
                dias.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
            val filteredHorarios = horarios.filter { it.dia.equals(dias[tabIndex], ignoreCase = true) }
            LazyColumn {
                items(filteredHorarios) { horario ->
                    val materia = materias.find { it.id == horario.materiaId }
                    val profesor = profesores.find { it.id == horario.profesorId }
                    Text(
                        text = "${materia?.nombre} - ${profesor?.nombre} - ${horario.horaInicio} a ${horario.horaFin}",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onNavigateToDetalleMateria(horario.materiaId) }
                    )
                }
            }
        }
    }
}

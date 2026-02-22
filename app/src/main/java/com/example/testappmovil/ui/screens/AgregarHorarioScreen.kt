package com.example.testappmovil.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.testappmovil.data.local.db.model.Materia
import com.example.testappmovil.data.local.db.model.Profesor
import com.example.testappmovil.ui.screens.components.DaySelector
import com.example.testappmovil.ui.screens.components.EntitySelector
import com.example.testappmovil.ui.screens.components.TimeSelector
import com.example.testappmovil.ui.viewmodel.HorarioViewModel
import com.example.testappmovil.ui.viewmodel.MateriaViewModel
import com.example.testappmovil.ui.viewmodel.ProfesorViewModel
import com.example.testappmovil.ui.viewmodel.UIState

@Composable
fun AgregarHorarioScreen(
    materiaViewModel: MateriaViewModel,
    profesorViewModel: ProfesorViewModel,
    horarioViewModel: HorarioViewModel,
    onNavigateBack: () -> Unit
) {
    val materias by materiaViewModel.materias.collectAsState()
    val profesores by profesorViewModel.profesores.collectAsState()
    val uiState by horarioViewModel.uiState.collectAsState()

    var dia by remember { mutableStateOf("Lunes") }
    var horaInicio by remember { mutableStateOf(8) }
    var horaFin by remember { mutableStateOf(9) }

    var selectedMateria by remember { mutableStateOf<Materia?>(null) }
    var selectedProfesor by remember { mutableStateOf<Profesor?>(null) }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Button(onClick = {
                horarioViewModel.clearUIState()
                onNavigateBack()
            }) {
                Text("Volver")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Asignar Horario", style = MaterialTheme.typography.headlineSmall)

            EntitySelector(
                label = "Materia",
                items = materias,
                selectedItem = selectedMateria,
                onItemSelected = { selectedMateria = it },
                getItemName = { it.nombre }
            )

            Spacer(modifier = Modifier.height(8.dp))

            EntitySelector(
                label = "Profesor",
                items = profesores,
                selectedItem = selectedProfesor,
                onItemSelected = { selectedProfesor = it },
                getItemName = { it.nombre }
            )

            Spacer(modifier = Modifier.height(8.dp))

            DaySelector(selectedDay = dia, onDaySelected = { dia = it })
            TimeSelector("Hora Inicio", horaInicio) { horaInicio = it }
            TimeSelector("Hora Fin", horaFin) { horaFin = it }

            uiState?.let {
                when(it){
                    is UIState.Success -> Text(it.message, color = Color.Green, modifier = Modifier.padding(vertical = 8.dp))
                    is UIState.Error -> Text(it.message, color = Color.Red, modifier = Modifier.padding(vertical = 8.dp))
                }
            }

            Button(
                onClick = {
                    if (selectedMateria != null && selectedProfesor != null) {
                        horarioViewModel.addHorario(selectedMateria!!.id, selectedProfesor!!.id, dia, horaInicio, horaFin)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Horario")
            }
        }
    }
}

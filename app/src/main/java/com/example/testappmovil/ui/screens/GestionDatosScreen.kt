package com.example.testappmovil.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testappmovil.ui.screens.components.GestionEntidad
import com.example.testappmovil.ui.viewmodel.MateriaViewModel
import com.example.testappmovil.ui.viewmodel.ProfesorViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GestionDatosScreen(
    materiaViewModel: MateriaViewModel,
    profesorViewModel: ProfesorViewModel,
    onNavigateBack: () -> Unit
) {
    val materias by materiaViewModel.materias.collectAsState()
    val errorMateria = materiaViewModel.errorState
    val profesores by profesorViewModel.profesores.collectAsState()
    val errorProfesor = profesorViewModel.errorState

    Scaffold {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Button(onClick = onNavigateBack) {
                Text("Volver al Horario")
            }
            Spacer(modifier = Modifier.height(16.dp))

            GestionEntidad(
                title = "Materias",
                items = materias,
                error = errorMateria.collectAsState(),
                onAddItem = { materiaViewModel.addMateria(it) },
                getItemName = { it.nombre },
                onClearError = { materiaViewModel.clearError() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            GestionEntidad(
                title = "Profesores",
                items = profesores,
                error = errorProfesor.collectAsState(),
                onAddItem = { profesorViewModel.addProfesor(it) },
                getItemName = { it.nombre },
                onClearError = { profesorViewModel.clearError() }
            )
        }
    }
}

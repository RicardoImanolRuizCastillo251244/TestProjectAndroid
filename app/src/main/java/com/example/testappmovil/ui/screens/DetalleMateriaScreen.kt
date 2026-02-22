package com.example.testappmovil.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testappmovil.ui.viewmodel.ImagenViewModel
import com.example.testappmovil.ui.viewmodel.MateriaViewModel

@Composable
fun DetalleMateriaScreen(
    materiaId: Long,
    materiaViewModel: MateriaViewModel,
    imagenViewModel: ImagenViewModel,
    onNavigateBack: () -> Unit
) {
    val materias by materiaViewModel.materias.collectAsState()
    val imagenes by imagenViewModel.imagenes.collectAsState()
    val materia = materias.find { it.id == materiaId }
    var descripcion by remember { mutableStateOf("") }

    LaunchedEffect(materiaId) {
        imagenViewModel.loadImagenes(materiaId)
    }

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
            Text("Materia: ${materia?.nombre}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción de la imagen") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = { 
                // Lógica para tomar foto próximamente
                imagenViewModel.addImagen(materiaId, "ruta/de/imagen.jpg", descripcion)
             }) {
                Text("Agregar Apunte (Foto)")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Apuntes Guardados:", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(imagenes) {
                    Text("${it.descripcion} - ${it.ruta}")
                }
            }
        }
    }
}

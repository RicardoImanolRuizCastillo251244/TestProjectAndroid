package com.example.testappmovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.testappmovil.data.local.db.AppDatabase
import com.example.testappmovil.data.repository.HorarioRepository
import com.example.testappmovil.data.repository.ImagenRepository
import com.example.testappmovil.data.repository.MateriaRepository
import com.example.testappmovil.data.repository.ProfesorRepository
import com.example.testappmovil.data.repository.UsuarioRepository
import com.example.testappmovil.ui.screens.AgregarHorarioScreen
import com.example.testappmovil.ui.screens.DetalleMateriaScreen
import com.example.testappmovil.ui.screens.GestionDatosScreen
import com.example.testappmovil.ui.screens.HorarioSemanalScreen
import com.example.testappmovil.ui.screens.LoginScreen
import com.example.testappmovil.ui.screens.RegistroScreen
import com.example.testappmovil.ui.theme.TestAppMovilTheme
import com.example.testappmovil.ui.viewmodel.HorarioViewModel
import com.example.testappmovil.ui.viewmodel.HorarioViewModelFactory
import com.example.testappmovil.ui.viewmodel.ImagenViewModel
import com.example.testappmovil.ui.viewmodel.ImagenViewModelFactory
import com.example.testappmovil.ui.viewmodel.LoginState
import com.example.testappmovil.ui.viewmodel.MateriaViewModel
import com.example.testappmovil.ui.viewmodel.MateriaViewModelFactory
import com.example.testappmovil.ui.viewmodel.ProfesorViewModel
import com.example.testappmovil.ui.viewmodel.ProfesorViewModelFactory
import com.example.testappmovil.ui.viewmodel.UsuarioViewModel
import com.example.testappmovil.ui.viewmodel.UsuarioViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database by lazy { AppDatabase.getDatabase(this) }
        val materiaRepository by lazy { MateriaRepository(database.materiaDao()) }
        val profesorRepository by lazy { ProfesorRepository(database.profesorDao()) }
        val horarioRepository by lazy { HorarioRepository(database.horarioDao()) }
        val imagenRepository by lazy { ImagenRepository(database.imagenDao()) }
        val usuarioRepository by lazy { UsuarioRepository(database.usuarioDao()) }

        val materiaViewModel: MateriaViewModel by viewModels { MateriaViewModelFactory(materiaRepository) }
        val profesorViewModel: ProfesorViewModel by viewModels { ProfesorViewModelFactory(profesorRepository) }
        val horarioViewModel: HorarioViewModel by viewModels { HorarioViewModelFactory(horarioRepository) }
        val imagenViewModel: ImagenViewModel by viewModels { ImagenViewModelFactory(imagenRepository) }
        val usuarioViewModel: UsuarioViewModel by viewModels { UsuarioViewModelFactory(usuarioRepository) }

        setContent {
            TestAppMovilTheme {
                val loginState by usuarioViewModel.loginState.collectAsState()
                var currentScreen by remember { mutableStateOf("horario") }
                var selectedMateriaId by remember { mutableStateOf<Long?>(null) }

                if (loginState is LoginState.LoggedIn) {
                    when (currentScreen) {
                        "horario" -> HorarioSemanalScreen(
                            horarioViewModel = horarioViewModel,
                            materiaViewModel = materiaViewModel,
                            profesorViewModel = profesorViewModel,
                            onNavigateToAddHorario = { currentScreen = "agregar_horario" },
                            onNavigateToDetalleMateria = {
                                selectedMateriaId = it
                                currentScreen = "detalle_materia"
                            },
                            onNavigateToGestion = { currentScreen = "gestion_datos" }
                        )
                        "agregar_horario" -> AgregarHorarioScreen(
                            materiaViewModel = materiaViewModel,
                            profesorViewModel = profesorViewModel,
                            horarioViewModel = horarioViewModel,
                            onNavigateBack = { currentScreen = "horario" }
                        )
                        "detalle_materia" -> DetalleMateriaScreen(
                            materiaId = selectedMateriaId!!,
                            materiaViewModel = materiaViewModel,
                            imagenViewModel = imagenViewModel,
                            onNavigateBack = { currentScreen = "horario" }
                        )
                        "gestion_datos" -> GestionDatosScreen(
                            materiaViewModel = materiaViewModel,
                            profesorViewModel = profesorViewModel,
                            onNavigateBack = { currentScreen = "horario" }
                        )
                    }
                } else {
                    var showRegisterScreen by remember { mutableStateOf(false) }
                    if (showRegisterScreen) {
                        RegistroScreen(
                            viewModel = usuarioViewModel,
                            onRegisterSuccess = { showRegisterScreen = false })
                    } else {
                        LoginScreen(
                            loginState = loginState,
                            onLogin = { user, pass -> usuarioViewModel.login(user, pass) },
                            onNavigateToRegister = { showRegisterScreen = true })
                    }
                }
            }
        }
    }
}

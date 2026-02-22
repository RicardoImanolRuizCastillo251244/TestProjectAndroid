package com.example.testappmovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testappmovil.data.local.db.model.Horario
import com.example.testappmovil.data.repository.HorarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HorarioViewModel(private val repository: HorarioRepository) : ViewModel() {

    private val _horarios = MutableStateFlow<List<Horario>>(emptyList())
    val horarios: StateFlow<List<Horario>> = _horarios

    private val _uiState = MutableStateFlow<UIState?>(null)
    val uiState: StateFlow<UIState?> = _uiState

    init {
        loadHorarios()
    }

    private fun loadHorarios() {
        viewModelScope.launch {
            _horarios.value = repository.getAllHorarios().sortedBy { it.horaInicio }
        }
    }

    fun addHorario(materiaId: Long, profesorId: Long, dia: String, horaInicio: Int, horaFin: Int) {
        viewModelScope.launch {
            if (horaInicio < 8 || horaFin > 16 || horaInicio >= horaFin) {
                _uiState.value = UIState.Error("El rango de horas no es válido.")
                return@launch
            }

            val horariosDelDia = repository.getHorariosByDia(dia)
            val hayConflicto = horariosDelDia.any {
                (horaInicio < it.horaFin) && (horaFin > it.horaInicio)
            }

            if (hayConflicto) {
                _uiState.value = UIState.Error("Conflicto de horario detectado.")
            } else {
                val horario = Horario(materiaId = materiaId, profesorId = profesorId, dia = dia, horaInicio = horaInicio, horaFin = horaFin)
                repository.insertHorario(horario)
                loadHorarios()
                _uiState.value = UIState.Success("Horario agregado con éxito")
            }
        }
    }
    fun clearUIState() {
        _uiState.value = null
    }
}

sealed class UIState {
    data class Success(val message: String) : UIState()
    data class Error(val message: String) : UIState()
}

class HorarioViewModelFactory(private val repository: HorarioRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HorarioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HorarioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

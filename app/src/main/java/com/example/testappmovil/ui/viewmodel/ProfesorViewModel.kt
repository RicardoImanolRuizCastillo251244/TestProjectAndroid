package com.example.testappmovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testappmovil.data.local.db.model.Profesor
import com.example.testappmovil.data.repository.ProfesorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfesorViewModel(private val repository: ProfesorRepository) : ViewModel() {

    private val _profesores = MutableStateFlow<List<Profesor>>(emptyList())
    val profesores: StateFlow<List<Profesor>> = _profesores

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    init {
        loadProfesores()
    }

    private fun loadProfesores() {
        viewModelScope.launch {
            _profesores.value = repository.getAllProfesores()
        }
    }

    fun addProfesor(nombre: String) {
        viewModelScope.launch {
            val nombreEnMayusculas = nombre.uppercase()
            val profesorExistente = _profesores.value.any { it.nombre.equals(nombreEnMayusculas, ignoreCase = true) }

            if (profesorExistente) {
                _errorState.value = "El profesor ya existe."
            } else {
                val profesor = Profesor(nombre = nombreEnMayusculas)
                repository.insertProfesor(profesor)
                loadProfesores()
                _errorState.value = null
            }
        }
    }

    fun clearError() {
        _errorState.value = null
    }
}

class ProfesorViewModelFactory(private val repository: ProfesorRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfesorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfesorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

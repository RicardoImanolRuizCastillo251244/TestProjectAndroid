package com.example.testappmovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testappmovil.data.local.db.model.Materia
import com.example.testappmovil.data.repository.MateriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MateriaViewModel(private val repository: MateriaRepository) : ViewModel() {

    private val _materias = MutableStateFlow<List<Materia>>(emptyList())
    val materias: StateFlow<List<Materia>> = _materias

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    init {
        loadMaterias()
    }

    private fun loadMaterias() {
        viewModelScope.launch {
            _materias.value = repository.getAllMaterias()
        }
    }

    fun addMateria(nombre: String) {
        viewModelScope.launch {
            val nombreEnMayusculas = nombre.uppercase()
            val materiaExistente = _materias.value.any { it.nombre.equals(nombreEnMayusculas, ignoreCase = true) }

            if (materiaExistente) {
                _errorState.value = "La materia ya existe."
            } else {
                val materia = Materia(nombre = nombreEnMayusculas)
                repository.insertMateria(materia)
                loadMaterias()
                _errorState.value = null
            }
        }
    }

    fun clearError() {
        _errorState.value = null
    }
}

class MateriaViewModelFactory(private val repository: MateriaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MateriaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MateriaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package com.example.testprojectmovil.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprojectmovil.data.local.entities.Subject
import com.example.testprojectmovil.data.repository.SubjectRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ScheduleViewModel(private val repository: SubjectRepository) : ViewModel() {

    // Esta variable "observa" la base de datos en tiempo real
    val allSubjects: StateFlow<List<Subject>> = repository.allSubjects
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Función para agregar una materia desde la interfaz
    fun addSubject(name: String, professor: String, day: Int, start: String, end: String) {
        viewModelScope.launch {
            val newSubject = Subject(
                name = name,
                professorName = professor,
                dayOfWeek = day,
                startTime = start,
                endTime = end,
                color = 0xFFBB86FC.toInt() // Un color por defecto
            )
            repository.insertSubject(newSubject)
        }
    }
}
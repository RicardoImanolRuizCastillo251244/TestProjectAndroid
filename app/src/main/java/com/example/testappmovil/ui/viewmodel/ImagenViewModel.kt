package com.example.testappmovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testappmovil.data.local.db.model.Imagen
import com.example.testappmovil.data.repository.ImagenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ImagenViewModel(private val repository: ImagenRepository) : ViewModel() {

    private val _imagenes = MutableStateFlow<List<Imagen>>(emptyList())
    val imagenes: StateFlow<List<Imagen>> = _imagenes

    fun loadImagenes(materiaId: Long) {
        viewModelScope.launch {
            _imagenes.value = repository.getImagesForMateria(materiaId)
        }
    }

    fun addImagen(materiaId: Long, ruta: String, descripcion: String) {
        viewModelScope.launch {
            val imagen = Imagen(materiaId = materiaId, ruta = ruta, descripcion = descripcion)
            repository.insertImagen(imagen)
            loadImagenes(materiaId)
        }
    }
}

class ImagenViewModelFactory(private val repository: ImagenRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImagenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImagenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

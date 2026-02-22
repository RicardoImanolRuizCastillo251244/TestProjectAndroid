package com.example.testappmovil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testappmovil.data.local.db.model.Usuario
import com.example.testappmovil.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.LoggedOut)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, contrasena: String) {
        viewModelScope.launch {
            val usuario = repository.getUsuario(username)
            if (usuario != null && usuario.contrasena == contrasena) {
                _loginState.value = LoginState.LoggedIn
            } else {
                _loginState.value = LoginState.Error("Usuario o contraseña incorrectos")
            }
        }
    }

    fun register(username: String, contrasena: String) {
        viewModelScope.launch {
            if (repository.getUsuario(username) != null) {
                _loginState.value = LoginState.Error("El usuario ya existe")
            } else {
                val nuevoUsuario = Usuario(username = username, contrasena = contrasena)
                repository.insertUsuario(nuevoUsuario)
                _loginState.value = LoginState.LoggedIn
            }
        }
    }

    fun logout(){
        _loginState.value = LoginState.LoggedOut
    }
}

sealed class LoginState {
    object LoggedIn : LoginState()
    object LoggedOut : LoginState()
    data class Error(val message: String) : LoginState()
}

class UsuarioViewModelFactory(private val repository: UsuarioRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsuarioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

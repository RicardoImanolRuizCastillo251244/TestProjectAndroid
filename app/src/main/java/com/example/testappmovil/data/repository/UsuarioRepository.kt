package com.example.testappmovil.data.repository

import com.example.testappmovil.data.local.db.dao.UsuarioDao
import com.example.testappmovil.data.local.db.model.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun insertUsuario(usuario: Usuario) {
        usuarioDao.insert(usuario)
    }

    suspend fun getUsuario(username: String): Usuario? {
        return usuarioDao.getUserByUsername(username)
    }
}

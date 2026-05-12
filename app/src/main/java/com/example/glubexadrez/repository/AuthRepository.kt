package com.example.glubexadrez.repository

import com.example.glubexadrez.baseDedados.Auth


class AuthRepository {


    private val datasource = Auth()

    fun register(
        nome: String,
        sobrenome: String,
        email: String,
        senha: String,
        message: (String) -> Unit
    ) {
        datasource.register(nome, sobrenome, email, senha, message)
    }

    fun login(
        email: String,
        senha: String,
        message: (String) -> Unit
    ) {

        datasource.login(email, senha, message)

    }

}
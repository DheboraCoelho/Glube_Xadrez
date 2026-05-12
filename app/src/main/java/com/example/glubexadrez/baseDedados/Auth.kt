package com.example.glubexadrez.baseDedados

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Auth {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun register(
        nome: String,
        sobrenome: String,
        email: String,
        senha: String,
        message: (String) -> Unit
    ) {

        if (
            nome.isEmpty() ||
            sobrenome.isEmpty() ||
            email.isEmpty() ||
            senha.isEmpty()
        ) {
            message("Preencha todos os campos!")
            return
        }

        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { result ->

                if (result.isSuccessful) {

                    val userId = auth.currentUser?.uid

                    val userMap = hashMapOf(
                        "nome" to nome,
                        "sobrenome" to sobrenome,
                        "email" to email
                    )

                    userId?.let {
                        db.collection("usuarios")
                            .document(it)
                            .set(userMap)
                            .addOnSuccessListener {
                                message("Cadastro realizado com sucesso!")
                            }
                            .addOnFailureListener {
                                message("Erro ao salvar dados!")
                            }
                    }

                } else {
                    message("Erro ao cadastrar usuário!")
                }
            }
    }
    fun login(
        email: String,
        senha: String,
        message: (String) -> Unit
    ) {

        if (email.isEmpty() || senha.isEmpty()) {

            message("Preencha todos os campos!")
            return
        }

        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { result ->

                if (result.isSuccessful) {

                    message("Login realizado com sucesso!")

                } else {

                    message("Email ou senha inválidos!")
                }
            }
    }
}
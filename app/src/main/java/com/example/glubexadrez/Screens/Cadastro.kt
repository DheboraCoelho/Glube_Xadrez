package com.example.glubexadrez.Screens

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glubexadrez.repository.AuthRepository




@Composable
fun Cadastro() {
    val repository = AuthRepository()

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Cadastrar",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = sobrenome,
            onValueChange = { sobrenome = it },
            label = { Text("Sobrenome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                repository.register(
                    nome,
                    sobrenome,
                    email,
                    senha
                ) {
                    message = it
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = message, fontSize = 18.sp)
    }
}
@Preview(showBackground = true)
@Composable
fun CadastroPreview() {
    Cadastro()
}

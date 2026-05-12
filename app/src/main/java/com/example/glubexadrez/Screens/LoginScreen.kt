package com.example.glubexadrez.Screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glubexadrez.R
import com.example.glubexadrez.repository.AuthRepository

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {

    val repository = AuthRepository()

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // FUNDO
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            // SETA
            IconButton(
                onClick = onBackClick
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color(0xFF6A2C91),
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // LOGO
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            CampoTexto("INSIRA USUÁRIO:")

            CampoInput(
                value = email,
                onValueChange = {
                    email = it
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            CampoTexto("INSIRA SENHA:")

            CampoInput(
                value = senha,
                onValueChange = {
                    senha = it
                },
                isPassword = true
            )

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = {

                    repository.login(
                        email,
                        senha
                    ) {

                        message = it

                        if (it == "Login realizado com sucesso!") {

                            onLoginSuccess()

                        }
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB23AC7)
                )
            ) {

                Text(
                    text = "ENTRAR",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = message,
                color = Color.Red,
                fontSize = 16.sp
            )
        }
    }
}
package com.example.glubexadrez.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glubexadrez.R


@Composable
fun InicioScreen(
    onLoginClick: () -> Unit,
    onCadastroClick: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // IMAGEM DE FUNDO
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Fundo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // CONTEÚDO
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // LOGO
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                // BOTÃO LOGIN
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A2C91)
                    )
                ) {
                    Text(
                        text = "ENTRAR COM LOGIN",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // BOTÃO CADASTRO
                Button(
                    onClick = onCadastroClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A2C91)
                    )
                ) {
                    Text(
                        text = "CADASTRAR-SE",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    InicioScreen(
        onLoginClick = {},
        onCadastroClick = {}
    )
}
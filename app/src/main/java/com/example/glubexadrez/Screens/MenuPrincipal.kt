package com.example.glubexadrez.Screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glubexadrez.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun MenuPrincipal( onLogout: () -> Unit) {

    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val currentUser = auth.currentUser

    LaunchedEffect(Unit) {

        currentUser?.uid?.let { uid ->

            db.collection("usuarios")
                .document(uid)
                .get()
                .addOnSuccessListener { document ->

                    nome = document.getString("nome") ?: ""
                    email = document.getString("email") ?: ""
                }
        }
    }

    Scaffold(

        bottomBar = {

            NavigationBar(
                containerColor = Color(0xFF6A2C91)
            ) {

                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Ranking")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Perfil")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Config")
                    }
                )
            }
        }

    ) { padding ->

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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
            ) {

                item {

                    // CARD PERFIL
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xCC6A2C91)
                        ),
                        shape = RoundedCornerShape(25.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // FOTO
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = nome.take(1),
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF6A2C91)
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {

                                Text(
                                    text = nome,
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = email,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Ranking #4 ♟️",
                                    color = Color.Yellow,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    // CLASSIFICAÇÃO
                    Text(
                        text = "Classificação",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6A2C91)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    RankingCard("1° João", "1800 pts")
                    RankingCard("2° Carlos", "1720 pts")
                    RankingCard("3° Ana", "1680 pts")
                    RankingCard("4° Você", "1600 pts")

                    Spacer(modifier = Modifier.height(30.dp))

                    // CONFIGURAÇÕES
                    Text(
                        text = "Configurações",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6A2C91)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ConfigButton("Alterar Dados")
                    ConfigButton("Alterar Senha")
                    ConfigButton(texto = "Sair",
                        onClick = {

                            FirebaseAuth.getInstance().signOut()

                            onLogout()

                        })
                }
            }
        }
    }
}

@Composable
fun RankingCard(
    nome: String,
    pontos: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xCC7B2E83)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = nome,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = pontos,
                color = Color.Yellow
            )
        }
    }
}

@Composable
fun ConfigButton(
    texto: String,
    onClick: () -> Unit = {}
) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
            .height(55.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFB23AC7)
        )
    ) {

        Text(
            text = texto,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
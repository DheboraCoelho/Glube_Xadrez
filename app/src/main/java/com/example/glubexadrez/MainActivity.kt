package com.example.glubexadrez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.glubexadrez.Screens.Cadastro
import com.example.glubexadrez.ui.theme.GlubeXadrezTheme
import com.google.firebase.Firebase
import com.example.glubexadrez.Screens.InicioScreen
import com.example.glubexadrez.Screens.LoginScreen


import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.glubexadrez.Screens.MenuPrincipal

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GlubeXadrezTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "inicio"
                ) {
                    composable("menu") {

                        MenuPrincipal(

                            onLogout = {

                                navController.navigate("inicio") {

                                    popUpTo("menu") {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    // tela login
                    composable("login") {

                        LoginScreen(

                            onBackClick = {

                                navController.popBackStack()

                            },

                            onLoginSuccess = {

                                navController.navigate("menu")

                            }
                        )
                    }


                    // TELA INICIAL
                    composable("inicio") {

                        InicioScreen(

                            onLoginClick = {

                                navController.navigate("login")

                            },

                            onCadastroClick = {

                                navController.navigate("cadastro")

                            }
                        )
                    }

                    // TELA CADASTRO
                    composable("cadastro") {

                        Cadastro( onBackClick = {
                            navController.popBackStack()}
                        )

                    }
                }
            }



        }
    }


}



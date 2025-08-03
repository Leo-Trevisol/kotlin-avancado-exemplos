package com.example.kotlinavancadoexemplos.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

// Classe principal da Activity do app (ponto de entrada da tela)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ativa suporte a conteúdos desenhados até a borda da tela (tela cheia moderna)
        enableEdgeToEdge()

        // Define a UI da tela usando Jetpack Compose
        setContent {
            UserScreen() // Chama o Composable que desenha a tela dos usuários
        }
    }
}

// Composable que representa a tela de exibição de usuários
@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    // LaunchedEffect com chave Unit significa que este bloco será executado apenas uma vez
    // quando o Composable entrar na composição pela primeira vez
    LaunchedEffect(Unit) {
        viewModel.loadUsers() // Dispara o carregamento dos dados (assíncrono)
    }

    // Layout vertical (coluna) ocupando toda a tela, com padding de 16dp
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Título da tela
        Text("Usuários", style = MaterialTheme.typography.headlineMedium)

        // Se os dados ainda estão sendo carregados, mostra um spinner (indicador de progresso)
        if (viewModel.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else {
            // Quando os dados estiverem prontos, exibe a lista com LazyColumn
            LazyColumn {
                // `items` percorre a lista de usuários vinda do ViewModel
                items(viewModel.users) { user ->

                    // Cada item da lista é exibido em um Card estilizado
                    Card(
                        modifier = Modifier
                            .fillMaxWidth() // Ocupa toda a largura disponível
                            .padding(vertical = 4.dp), // Espaço vertical entre os cards
                        elevation = CardDefaults.cardElevation(4.dp) // Sombra do card
                    ) {
                        // Coluna com os dados do usuário
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Nome do usuário com estilo de título
                            Text(
                                text = user.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            // E-mail do usuário com estilo de texto comum
                            Text(
                                text = user.email,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

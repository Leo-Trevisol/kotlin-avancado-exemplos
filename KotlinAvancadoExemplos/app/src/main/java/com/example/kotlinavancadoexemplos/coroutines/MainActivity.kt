package com.example.kotlinavancadoexemplos.coroutines

// Importações necessárias para a Activity, Compose e ViewModel
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

// Classe principal da aplicação, que representa a tela principal
class MainActivity : ComponentActivity() {

    // Método chamado quando a Activity é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que o conteúdo da tela ocupe toda a área (inclusive por trás de status bar)
        enableEdgeToEdge()

        // Define o conteúdo da tela utilizando Jetpack Compose
        setContent {
            UserScreen() // Chama o composable que desenha a interface da tela de usuários
        }
    }
}

// Composable responsável por renderizar a UI de usuários
@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    // Observa o estado da UI vindo do ViewModel usando Flow (StateFlow)
    val uiState by viewModel.uiState.collectAsState()

    // Coluna vertical que organiza os elementos da tela de forma empilhada
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo o espaço disponível na tela
            .padding(16.dp) // Adiciona preenchimento interno (margem) em todos os lados
    ) {

        // Título da tela
        Text(
            "Usuários",
            style = MaterialTheme.typography.headlineMedium // Estilo do texto definido pelo tema Material 3
        )

        // Verifica qual é o estado atual da UI para exibir o conteúdo apropriado
        when (uiState) {

            // Caso esteja carregando os dados
            is UiState.Loading -> {
                // Mostra um indicador circular de carregamento
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            // Caso os dados tenham sido carregados com sucesso
            is UiState.Success -> {
                // Acessa a lista de usuários vinda do estado de sucesso
                val users = (uiState as UiState.Success).users

                // Exibe a lista em uma rolagem vertical eficiente
                LazyColumn {
                    // Para cada item (usuário) da lista, cria um item na LazyColumn
                    items(users) { user ->
                        // Cria um cartão para exibir os dados do usuário
                        Card(
                            modifier = Modifier
                                .fillMaxWidth() // Faz o cartão ocupar toda a largura disponível
                                .padding(vertical = 4.dp), // Espaçamento entre os cartões
                            elevation = CardDefaults.cardElevation(4.dp) // Sombra do cartão
                        ) {
                            // Organiza os textos do usuário em coluna, dentro do cartão
                            Column(modifier = Modifier.padding(16.dp)) {
                                // Nome do usuário
                                Text(
                                    text = user.name,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                // Email do usuário
                                Text(
                                    text = user.email,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }

            // Caso ocorra um erro ao buscar os dados
            is UiState.Error -> {
                // Acessa a mensagem de erro do estado de erro
                val errorMessage = (uiState as UiState.Error).message

                // Exibe a mensagem de erro em vermelho
                Text(
                    text = "Erro ao carregar: $errorMessage",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

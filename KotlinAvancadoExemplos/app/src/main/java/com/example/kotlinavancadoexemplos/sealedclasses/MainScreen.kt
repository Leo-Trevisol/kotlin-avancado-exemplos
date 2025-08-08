package com.example.kotlinavancadoexemplos.sealedclasses

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Função composable principal da tela.
 * É responsável por observar o estado da UI (UiState) e renderizar
 * diferentes layouts conforme o estado atual (Loading, Success ou Error).
 *
 * @param viewModel O ViewModel associado à tela, que expõe o estado da UI.
 */
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {

    // Observa o fluxo de estados exposto pelo ViewModel e converte para um State do Compose.
    // O "by" delega o valor diretamente para a variável uiState.
    val uiState by viewModel.uiState.collectAsState()

    // Estrutura condicional para verificar o estado atual da UI.
    // Aqui usamos o "when" para diferenciar a lógica para cada estado da sealed class UiState.
    when (uiState) {

        // Caso o estado seja "Loading", exibimos um indicador de progresso centralizado.
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        // Caso o estado seja "Success", exibimos os dados recebidos em uma lista vertical.
        is UiState.Success -> {
            // Extrai a lista de dados do estado Success.
            val data = (uiState as UiState.Success).data

            // LazyColumn: lista que carrega e renderiza itens de forma eficiente (renderização sob demanda).
            LazyColumn {
                // Para cada item da lista, criamos um elemento na tela.
                items(data) { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth() // Faz o texto ocupar toda a largura disponível.
                            .padding(16.dp) // Espaçamento interno.
                    )
                    Divider() // Linha divisória entre os itens da lista.
                }
            }
        }

        // Caso o estado seja "Error", exibimos a mensagem de erro centralizada na tela.
        is UiState.Error -> {
            // Extrai a mensagem de erro do estado Error.
            val message = (uiState as UiState.Error).message

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = message)
            }
        }
    }
}

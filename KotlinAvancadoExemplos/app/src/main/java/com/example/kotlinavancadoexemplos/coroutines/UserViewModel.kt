package com.example.kotlinavancadoexemplos.coroutines

// Importa a classe base ViewModel para gerenciamento de UI com ciclo de vida
import androidx.lifecycle.ViewModel

// Fornece um escopo de corrotina atrelado ao ciclo de vida do ViewModel
import androidx.lifecycle.viewModelScope

// Importa os tipos para controle reativo do estado da UI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

// ViewModel responsável por gerenciar os dados de usuário e estado da UI
class UserViewModel : ViewModel() {

    // Instância do repositório que fornece os dados dos usuários
    private val repository = UserRepository()

    // _uiState é um fluxo mutável que representa o estado atual da UI
    // Começa com o estado UiState.Loading
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)

    // uiState é a versão somente leitura (imutável) exposta para a UI
    // A UI vai observar esse fluxo e reagir automaticamente às mudanças
    val uiState: StateFlow<UiState> = _uiState

    // Bloco init é executado ao criar o ViewModel
    // Aqui iniciamos o carregamento dos dados automaticamente
    init {
        loadUsers() // Dispara a função para buscar os usuários
    }

    // Função que inicia a coleta do fluxo de usuários do repositório
    fun loadUsers() {
        // Inicia uma nova coroutine no escopo do ViewModel
        // Assim, a operação é cancelada automaticamente se o ViewModel for destruído
        viewModelScope.launch {

            // Inicia o fluxo retornado por fetchUsers()
            repository.fetchUsers()
                // onStart é executado antes do primeiro emit()
                // Aqui setamos o estado como "carregando"
                .onStart { _uiState.value = UiState.Loading }

                // catch captura qualquer exceção que aconteça dentro do fluxo
                // Atualizamos o estado para erro, exibindo a mensagem apropriada
                .catch { e -> _uiState.value = UiState.Error(e.message ?: "Erro desconhecido") }

                // collect coleta os valores emitidos pelo fluxo
                // Aqui recebemos a lista de usuários e atualizamos o estado da UI para sucesso
                .collect { users ->
                    _uiState.value = UiState.Success(users)
                }
        }
    }
}

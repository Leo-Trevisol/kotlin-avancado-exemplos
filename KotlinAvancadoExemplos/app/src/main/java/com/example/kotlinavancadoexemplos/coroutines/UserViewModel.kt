package com.example.kotlinavancadoexemplos.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class UserViewModel : ViewModel() {

    // Instância do repositório que fornece os dados (padrão Repository)
    private val repository = UserRepository()

    // Estado reativo que indica se os dados estão sendo carregados
    // Usado para exibir um loading spinner na tela (compose reage a esse estado)
    var isLoading by mutableStateOf(false)

    // Lista reativa de usuários que será exibida na tela
    // Compose observará mudanças nesta lista e atualizará automaticamente a UI
    var users = mutableStateListOf<User>()

    // Função pública que dispara o carregamento dos dados de usuários
    fun loadUsers() {
        // viewModelScope é uma CoroutineScope atrelada ao ciclo de vida do ViewModel.
        // Ou seja, se o ViewModel for destruído, a coroutine também é cancelada automaticamente.
        viewModelScope.launch {

            // Início do carregamento: ativa o estado de loading
            isLoading = true

            try {
                // Chamada suspensa que busca os usuários de forma assíncrona
                // Essa chamada NÃO bloqueia a UI, mesmo que demore
                val result = repository.fetchUsers()

                // Limpa a lista atual e adiciona os novos usuários
                users.clear()
                users.addAll(result)

            } catch (e: Exception) {
                // Tratamento de erro: idealmente você deveria atualizar algum estado de erro
                // para exibir uma mensagem ao usuário
                // Por exemplo: errorMessage = e.message
            } finally {
                // Final do carregamento: desativa o estado de loading
                isLoading = false
            }
        }
    }
}

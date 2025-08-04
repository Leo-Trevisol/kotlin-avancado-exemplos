package com.example.kotlinavancadoexemplos.coroutines

// Representa os diferentes estados possíveis da UI durante a busca de dados
// Uma sealed class (classe selada) permite definir um conjunto fechado de subclasses
// Isso é útil para modelar estados finitos da interface de forma segura e clara
sealed class UiState {

    // Estado "Loading" indica que os dados estão sendo carregados (ex: mostrar spinner)
    object Loading : UiState()

    // Estado "Success" indica que os dados foram carregados com sucesso
    // Ele carrega a lista de usuários retornada pela API
    data class Success(val users: List<User>) : UiState()

    // Estado "Error" representa uma falha ao tentar carregar os dados
    // Contém uma mensagem de erro para exibição
    data class Error(val message: String) : UiState()
}

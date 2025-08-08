package com.example.kotlinavancadoexemplos.sealedclasses

// UiState.kt
// -----------------------------------------------------------------------------
// Esta sealed class representa os possíveis estados da UI (interface do usuário).
// O uso de sealed classes permite garantir que todos os estados possíveis sejam
// conhecidos e tratados de forma segura no código.
// -----------------------------------------------------------------------------

// Definição da sealed class UiState.
// Uma sealed class (classe selada) restringe a herança a este arquivo.
// Isso significa que todos os tipos que herdam de UiState devem ser definidos aqui.
sealed class UiState {

    // Estado que representa "carregando".
    // Usamos "object" porque é um estado único (singleton), não precisa de parâmetros.
    object Loading : UiState()

    // Estado que representa sucesso.
    // Aqui usamos data class para armazenar dados obtidos com sucesso,
    // no caso uma lista de strings.
    data class Success(val data: List<String>) : UiState()

    // Estado que representa erro.
    // Também é uma data class, mas armazena uma mensagem de erro.
    data class Error(val message: String) : UiState()
}

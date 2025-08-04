package com.example.kotlinavancadoexemplos.coroutines

// Importa a classe Flow, usada para criar e representar um fluxo de dados assíncronos
import kotlinx.coroutines.flow.Flow

// Importa a função flow, que permite construir um Flow de forma imperativa
import kotlinx.coroutines.flow.flow

// Classe responsável por buscar dados de usuários (simula um repositório)
class UserRepository {

    // Função que retorna um fluxo (Flow) de uma lista de usuários
    // O Flow permite que os dados sejam emitidos de forma assíncrona e observados de forma reativa
    fun fetchUsers(): Flow<List<User>> = flow {

        // Simula uma chamada de rede suspensa (usando Retrofit)
        // Essa chamada só será executada quando alguém coletar o Flow
        val users = RetrofitInstance.api.getUsers()

        // Emite a lista de usuários para os coletores do Flow
        // É aqui que os dados "começam a fluir" para o ViewModel ou quem estiver coletando
        emit(users)
    }
}

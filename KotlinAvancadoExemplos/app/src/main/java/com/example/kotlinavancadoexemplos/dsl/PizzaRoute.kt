package com.example.kotlinavancadoexemplos.dsl
// Define o pacote onde a função está localizada

import io.ktor.server.application.*
// Importa classes para manipular a aplicação Ktor, incluindo o 'call' para requisições e respostas

import io.ktor.server.response.*
// Importa funções para enviar respostas HTTP, como 'call.respond'

import io.ktor.server.routing.*
// Importa funcionalidades de routing, permitindo criar rotas HTTP

fun Route.pizzaRoute() {
    // Extende a classe Route do Ktor criando uma função customizada (DSL)
    // Isso permite escrever 'routing { pizzaRoute() }' de forma fluida

    post("/pizza") {
        // Define uma rota HTTP POST no caminho "/pizza"
        // Esse bloco é executado sempre que houver uma requisição POST para "/pizza"

        val minhaPizza = pizza {
            // Cria uma instância da classe Pizza usando nossa DSL personalizada
            nome = "Pizza Kotlin"
            // Define o nome da pizza
            tamanho = "Média"
            // Define o tamanho da pizza
            ingrediente("Queijo")
            // Adiciona o ingrediente "Queijo"
            ingrediente("Bacon")
            // Adiciona o ingrediente "Bacon"
        }

        call.respond(minhaPizza)
        // Responde à requisição HTTP com o objeto 'minhaPizza'
        // O Ktor usa automaticamente ContentNegotiation para transformar em JSON
    }
}

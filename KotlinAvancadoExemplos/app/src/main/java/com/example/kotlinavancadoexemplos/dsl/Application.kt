package com.example.kotlinavancadoexemplos.dsl
// Define o pacote onde a classe está localizada, útil para organização do projeto

import io.ktor.server.application.*
// Importa classes básicas para criar aplicações Ktor

import io.ktor.server.engine.*
// Importa classes para rodar o servidor embutido (embeddedServer)

import io.ktor.server.netty.*
// Importa a engine Netty, que será usada para executar o servidor

import io.ktor.server.routing.*
// Importa funcionalidades de routing, para definir rotas HTTP

import io.ktor.server.plugins.contentnegotiation.*
// Importa o plugin de ContentNegotiation, que permite serializar/deserializar dados automaticamente

import io.ktor.serialization.kotlinx.json.*
// Importa a integração com kotlinx.serialization para JSON


fun main() {
    // Função principal que será executada ao iniciar a aplicação
    embeddedServer(Netty, port = 8080) {
        // Cria um servidor embutido usando a engine Netty na porta 8080
        install(ContentNegotiation) {
            // Instala o plugin ContentNegotiation no servidor
            json()
            // Configura o plugin para lidar com JSON usando kotlinx.serialization
        }
        routing {
            // Define o bloco de rotas HTTP do servidor
            pizzaRoute()
            // Chama uma função (DSL) que define rotas relacionadas a "pizza"
        }
    }.start(wait = true)
    // Inicia o servidor e espera indefinidamente (bloqueia a thread principal)
}

package com.example.kotlinavancadoexemplos.nullsafetysmartcasts

// Nosso modelo de usuário
data class Usuario(val nome: String?, val idade: Int?)

// Função que imprime dados do usuário, usando null safety e smart casts
fun imprimirUsuario(usuario: Usuario?) {
    // Safe call + Elvis: evita null pointer
    val nomeFormatado = usuario?.nome ?: "Nome não informado"
    val idadeFormatada = usuario?.idade ?: 0

    println("👤 Nome: $nomeFormatado")
    println("🎂 Idade: $idadeFormatada anos")

    // Smart cast com "is"
    if (usuario != null) {
        // Aqui o Kotlin sabe que "usuario" não é mais null
        println("Usuário válido, processando informações...")
    } else {
        println("Usuário é nulo!")
    }
}

// Função que retorna tamanho do nome, usando not-null assertion (!!)
fun tamanhoDoNome(usuario: Usuario?): Int {
    // !! = força o compilador a assumir que não é null
    // ATENÇÃO: se for null, estoura NullPointerException
    return usuario!!.nome!!.length
}

fun main() {
    val usuario1 = Usuario("Ana", 25)
    val usuario2 = Usuario(null, null)
    val usuario3: Usuario? = null

    println("=== Exemplo 1 ===")
    imprimirUsuario(usuario1)

    println("\n=== Exemplo 2 ===")
    imprimirUsuario(usuario2)

    println("\n=== Exemplo 3 ===")
    imprimirUsuario(usuario3)

    println("\n=== Usando !! ===")
    try {
        println("Tamanho do nome: ${tamanhoDoNome(usuario1)}")
        println("Tamanho do nome: ${tamanhoDoNome(usuario2)}") // Vai dar erro!
    } catch (e: NullPointerException) {
        println("⚠ Erro: Tentou acessar algo nulo usando !!")
    }
}

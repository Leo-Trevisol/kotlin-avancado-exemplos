package com.example.kotlinavancadoexemplos.lambdashigherorderfunctions

// ^ Define o pacote do arquivo. Em Kotlin/JVM, isso deve refletir a estrutura de pastas do projeto.

/**
 * Ponto de entrada da aplicação (execução via JVM).
 * Aqui demonstramos:
 *  - Criação e uso de lambdas
 *  - Uso de higher-order functions que recebem e retornam funções
 *  - Comparações e exemplos práticos com listas
 */
fun main() {
    // ------------------------- LAMBDA BÁSICA -------------------------

    // Declara uma variável do tipo função: (Int, Int) -> Int
    // Significa: recebe dois Int e devolve um Int.
    // À direita, uma lambda que soma os parâmetros 'a' e 'b'.
    val soma: (Int, Int) -> Int = { a, b -> a + b }

    // Invoca a função-lambda como se fosse uma função normal.
    println("Soma com lambda: ${soma(10, 5)}") // Esperado: 15

    // ------------------ HIGHER-ORDER FUNCTION (RECEBE FUNÇÃO) ------------------

    // Chama uma higher-order function (executarOperacao) passando uma lambda como último parâmetro.
    // Pelo Kotlin, quando a lambda é o último argumento, podemos usar trailing lambda: fora dos parênteses.
    val multiplicacao = executarOperacao(4, 3) { x, y -> x * y }
    println("Multiplicação com higher-order function (lambda): $multiplicacao") // Esperado: 12

    // Também podemos passar uma função nomeada usando referência ::nomeDaFuncao
    // Aqui, passamos a função 'subtrair' (definida em Operacoes.kt) como argumento.
    val sub = executarOperacao(20, 5, ::subtrair)
    println("Subtração com referência de função ::subtrair: $sub") // Esperado: 15

    // ------------------ HIGHER-ORDER FUNCTION (RETORNA FUNÇÃO) ------------------

    // 'fabricarOperacao' retorna uma função (Int, Int) -> Int de acordo com o símbolo enviado.
    val operacaoSoma = fabricarOperacao('+')   // Devolve uma função que soma.
    val operacaoDiv  = fabricarOperacao('/')   // Devolve uma função que divide (tratamento simples).

    // Usamos as funções retornadas como qualquer outra função.
    println("Operação fabricada '+' em (7, 8): ${operacaoSoma(7, 8)}")   // Esperado: 15
    println("Operação fabricada '/' em (20, 4): ${operacaoDiv(20, 4)}") // Esperado: 5

    // ------------------------- LISTAS + LAMBDAS -------------------------

    // Lista base para exemplos
    val numeros = listOf(1, 2, 3, 4, 5, 6)

    // Exemplo prático: filtrar somente pares, passando uma lambda como critério.
    val pares = filtrarLista(numeros) { it % 2 == 0 }
    println("Números pares (filter com lambda): $pares") // Esperado: [2, 4, 6]

    // Versão genérica do filtro (funciona para qualquer tipo T).
    val palavras = listOf("Kotlin", "Compose", "Flow", "Coroutines")
    val longas = filtrarGenerico(palavras) { it.length >= 6 }
    println("Palavras com 6+ caracteres: $longas") // Esperado: [Kotlin, Compose, Coroutines]

    // Transformação genérica: aplica função de transformação elemento a elemento (map customizado).
    val dobrados = transformarLista(numeros) { it * 2 }
    println("Números dobrados (map com lambda): $dobrados") // Esperado: [2, 4, 6, 8, 10, 12]

    // ----------------------- COMPARAÇÃO DECLARATIVA VS IMPERATIVA -----------------------

    // Abordagem imperativa (sem lambdas): filtrar pares "na mão".
    val paresImperativo = mutableListOf<Int>()
    for (n in numeros) {
        if (n % 2 == 0) {
            paresImperativo.add(n)
        }
    }
    println("Pares (estilo imperativo): $paresImperativo") // Mesmo resultado, mais verboso.

    // Abordagem funcional/declarativa (com lambdas): mais concisa e expressiva.
    val paresDeclarativo = numeros.filter { it % 2 == 0 }
    println("Pares (estilo funcional/declarativo): $paresDeclarativo")
}

package com.example.kotlinavancadoexemplos.lambdashigherorderfunctions
// ^ Mesmo pacote dos demais arquivos para facilitar import e uso direto.

/**
 * Higher-Order Function que RECEBE outra função como parâmetro.
 *
 * @param a Primeiro operando inteiro.
 * @param b Segundo operando inteiro.
 * @param operacao Função que define como combinar 'a' e 'b' (ex.: soma, subtração, multiplicação...).
 * @return O resultado de aplicar 'operacao' sobre (a, b).
 *
 * Exemplo de uso:
 *   executarOperacao(4, 3) { x, y -> x * y }   // 12
 *   executarOperacao(20, 5, ::subtrair)        // 15
 */
fun executarOperacao(a: Int, b: Int, operacao: (Int, Int) -> Int): Int {
    // Apenas delega o cálculo à função recebida,
    // permitindo que o "como calcular" seja decidido por quem chama.
    return operacao(a, b)
}

/**
 * Função nomeada simples para demonstrar referência de função (::subtrair).
 * Pode ser passada para 'executarOperacao' sem criar uma lambda.
 */
fun subtrair(a: Int, b: Int): Int = a - b
// ^ Corpo de expressão (single-expression function) — sintaxe curta e idiomática.

/**
 * Higher-Order Function que RETORNA uma função.
 *
 * @param simbolo Determina qual operação será "fabricada": '+', '-', '*', '/'.
 * @return Uma função (Int, Int) -> Int correspondente ao símbolo.
 *
 * Observação: implementação simples, com tratamento básico de divisão por zero.
 * Em cenários reais, prefira retornar Result/selar erros ou lançar exceções tratadas.
 */
fun fabricarOperacao(simbolo: Char): (Int, Int) -> Int {
    // Retorna uma lambda específica conforme o símbolo.
    return when (simbolo) {
        '+' -> { x, y -> x + y }                         // Soma
        '-' -> { x, y -> x - y }                         // Subtração
        '*' -> { x, y -> x * y }                         // Multiplicação
        '/' -> { x, y -> if (y != 0) x / y else 0 }      // Divisão simples (evita divisão por zero)
        else -> { _, _ -> 0 }                            // Caso padrão: operação "nula"
    }
}

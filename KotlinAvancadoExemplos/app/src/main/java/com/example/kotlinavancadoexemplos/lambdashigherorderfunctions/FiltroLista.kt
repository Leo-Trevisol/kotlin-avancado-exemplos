package com.example.kotlinavancadoexemplos.lambdashigherorderfunctions

// ^ Mesmo pacote: os três arquivos pertencem ao módulo de exemplos de Lambdas/HOFs.

/**
 * Filtro específico para List<Int>, mostrando como passar uma lambda como critério.
 *
 * @param lista Lista de inteiros a ser filtrada.
 * @param criterio Função que recebe um Int e devolve Boolean. Se retornar true, o elemento permanece.
 * @return Nova lista contendo apenas os elementos que satisfazem o critério.
 *
 * Exemplo:
 *   filtrarLista(listOf(1,2,3,4)) { it % 2 == 0 }  // [2, 4]
 */
fun filtrarLista(lista: List<Int>, criterio: (Int) -> Boolean): List<Int> {
    // Poderíamos delegar direto para 'filter', mas mantemos explícito para fins didáticos.
    return lista.filter(criterio)
}

/**
 * Versão GENÉRICA do filtro: funciona para qualquer tipo T.
 *
 * @param lista Lista de elementos de tipo genérico T.
 * @param criterio Função que recebe T e retorna Boolean (true = mantém; false = descarta).
 * @return Lista filtrada contendo apenas os elementos que atendem ao critério.
 *
 * Exemplo:
 *   filtrarGenerico(listOf("Kotlin","Flow")) { it.length >= 5 } // ["Kotlin"]
 */
fun <T> filtrarGenerico(lista: List<T>, criterio: (T) -> Boolean): List<T> {
    // O compilador infere T com base no argumento fornecido pelo chamador.
    return lista.filter(criterio)
}

/**
 * Transformação GENÉRICA (equivalente a um 'map' didático).
 *
 * @param lista Lista de elementos de tipo T.
 * @param transformador Função que converte T em R (novo tipo).
 * @return Nova lista contendo os elementos transformados para o tipo R.
 *
 * Exemplo:
 *   transformarLista(listOf(1,2,3)) { it * 2 } // [2, 4, 6]
 */
fun <T, R> transformarLista(lista: List<T>, transformador: (T) -> R): List<R> {
    // Aplica a função 'transformador' a cada elemento, produzindo uma nova lista de R.
    return lista.map(transformador)
}

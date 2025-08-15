package com.example.kotlinavancadoexemplos.dsl
// Define o pacote onde essas classes e funções estão localizadas

data class Pizza(
    var nome: String = "",
    // Propriedade que guarda o nome da pizza, padrão é string vazia

    var tamanho: String = "",
    // Propriedade que guarda o tamanho da pizza, padrão é string vazia

    val ingredientes: MutableList<String> = mutableListOf()
    // Lista mutável que armazenará os ingredientes da pizza
    // Inicialmente vazia, pode ser adicionada com a função auxiliar 'ingrediente'
)
// Data class usada para armazenar dados da pizza de forma simples, com getters, setters, equals e toString gerados automaticamente

// Função principal da DSL
fun pizza(builder: Pizza.() -> Unit): Pizza {
    // 'builder' é uma lambda com receiver, ou seja, podemos acessar diretamente as propriedades de Pizza dentro do bloco

    return Pizza().apply(builder)
    // Cria uma nova instância de Pizza e aplica o bloco 'builder' a ela
    // 'apply' retorna a própria instância após executar o bloco
}

// Função auxiliar para adicionar ingredientes
fun Pizza.ingrediente(nome: String) {
    // Extensão da classe Pizza, permite chamar 'ingrediente("Queijo")' dentro do bloco da DSL

    ingredientes.add(nome)
    // Adiciona o ingrediente informado à lista de ingredientes da pizza
}

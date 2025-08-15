package com.example.kotlinavancadoexemplos.dsl
// Define o pacote onde a tela composable está localizada

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
// Importações necessárias para Jetpack Compose, layout, estilo e dimensões

@Composable
fun PizzaScreen() {
    // Função composable que representa a tela da pizza
    // Pode ser usada dentro de outras funções composable ou NavHost

    val minhaPizza = pizza {
        nome = "Pizza Kotlin"
        tamanho = "Grande"
        ingrediente("Queijo")
        ingrediente("Tomate")
        ingrediente("Orégano")
    }
    // Cria uma instância da classe Pizza usando a DSL que definimos
    // Nome, tamanho e ingredientes são configurados de forma declarativa

    Column(modifier = Modifier.padding(16.dp)) {
        // Organiza os elementos em coluna vertical com padding de 16dp

        Text("🍕 ${minhaPizza.nome}", style = MaterialTheme.typography.headlineSmall)
        // Exibe o nome da pizza com emoji e estilo de headline pequeno do MaterialTheme

        Text("Tamanho: ${minhaPizza.tamanho}")
        // Mostra o tamanho da pizza

        Text("Ingredientes:")
        // Título da lista de ingredientes

        minhaPizza.ingredientes.forEach {
            Text("- $it")
            // Para cada ingrediente da lista, exibe uma linha com "-"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PizzaScreenPreview() {
    MaterialTheme {
        PizzaScreen()
    }
}

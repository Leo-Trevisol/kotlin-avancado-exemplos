// Define o pacote onde está localizada esta classe
package com.example.kotlinavancadoexemplos.extensionfuntions

// Importações necessárias para Android e Compose
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// Classe principal da Activity que usa Jetpack Compose
class MainActivity : ComponentActivity() {
    // Método chamado quando a Activity é criada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Define o conteúdo da tela usando Compose
        setContent {
            // Chama a função Composable que define a UI
            ExtensaoComposeApp()
        }
    }
}

// Função Composable que desenha a interface e aplica extensões
@Composable
fun ExtensaoComposeApp() {
    // Contexto do Android, necessário para mostrar Toasts
    val context = LocalContext.current

    // Estado que armazena o texto digitado pelo usuário
    var inputText by remember { mutableStateOf("") }

    // Lista de strings para demonstrar extensão de List
    val lista = listOf("Kotlin", "Compose", "Jetpack", "Coroutines")

    // Layout em coluna com espaçamento entre os itens
    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupa toda a tela
            .padding(24.dp), // Aplica margem interna de 24dp
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espaço entre elementos
    ) {
        // Título ou instrução de entrada de texto
        Text("Digite algo:", style = MaterialTheme.typography.titleMedium)

        // Campo de texto básico para digitação do usuário
        BasicTextField(
            value = inputText, // Valor atual do campo
            onValueChange = { inputText = it }, // Atualiza o estado ao digitar
            modifier = Modifier
                .fillMaxWidth() // Campo ocupa toda a largura
                .padding(8.dp)  // Padding ao redor do campo
        )

        // Botão que aplica extensão capitalizeWords() ao texto
        Button(onClick = {
            val capitalizado = inputText.capitalizeWords() // Aplica a extensão
            Toast.makeText(context, "Capitalizado: $capitalizado", Toast.LENGTH_SHORT).show() // Mostra resultado
        }) {
            Text("Capitalizar Texto") // Texto do botão
        }

        // Botão que mostra a data formatada usando toFormattedDate()
        Button(onClick = {
            val dataFormatada = System.currentTimeMillis().toFormattedDate() // Aplica a extensão
            Toast.makeText(context, "Hoje é $dataFormatada", Toast.LENGTH_SHORT).show() // Mostra resultado
        }) {
            Text("Mostrar Data Atual") // Texto do botão
        }

        // Botão que mostra a lista formatada como string
        Button(onClick = {
            Toast.makeText(
                context,
                "Lista: ${lista.toCommaSeparated()}", // Aplica a extensão na lista
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Text("Mostrar Lista") // Texto do botão
        }
    }
}

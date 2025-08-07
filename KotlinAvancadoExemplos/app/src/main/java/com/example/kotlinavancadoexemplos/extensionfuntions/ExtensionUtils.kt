// Define o pacote do projeto onde esse arquivo está inserido
package com.example.kotlinavancadoexemplos.extensionfuntions

// Importações necessárias para manipulação de contexto, views e logs
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import java.text.SimpleDateFormat
import java.util.*

// ------------------------
// Funções de Extensão Úteis
// ------------------------

/**
 * Extensão para String
 * Capitaliza a primeira letra de cada palavra da string
 * Ex: "ola mundo" → "Ola Mundo"
 */
fun String.capitalizeWords(): String =
    // Divide a string por espaço, transforma cada palavra e junta novamente
    split(" ").joinToString(" ") {
        it.lowercase().replaceFirstChar(Char::uppercaseChar)
    }

/**
 * Extensão para List<T>
 * Concatena os elementos da lista em uma única string separados por vírgulas
 * Ex: listOf("A", "B", "C") → "A, B, C"
 */
fun <T> List<T>.toCommaSeparated(): String = joinToString(", ")

/**
 * Extensão para Long (timestamp em milissegundos)
 * Converte o valor para uma data formatada no padrão dd/MM/yyyy
 * Ex: 1667683200000 → "06/11/2022"
 */
fun Long.toFormattedDate(): String {
    // Cria um formatador de data usando o padrão brasileiro
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    // Converte o long para Date e depois para String
    return sdf.format(Date(this))
}

/**
 * Extensão para View
 * Exibe o teclado virtual (soft keyboard) para esta view
 */
fun View.showKeyboard() {
    // Obtém o serviço de input do sistema
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    // Solicita que o teclado seja exibido para a view atual
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * Extensão para View
 * Oculta o teclado virtual associado a esta view
 */
fun View.hideKeyboard() {
    // Obtém o serviço de input do sistema
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    // Solicita que o teclado seja escondido da janela da view
    imm.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Extensão para EditText (aplicável em Views XML clássicas)
 * Executa uma ação toda vez que o texto for alterado
 * Uso: editText.onTextChanged { novoTexto -> ... }
 */
fun EditText.onTextChanged(action: (String) -> Unit) {
    // Listener que captura a alteração e executa o lambda
    doOnTextChanged { text, _, _, _ ->
        action(text.toString())
    }
}

/**
 * Extensão para Throwable
 * Loga o stacktrace e mensagem de erro de forma prática
 * Pode definir um tag customizado para o log (default: "Error")
 */
fun Throwable.logStackTrace(tag: String = "Error") {
    // Usa Log.e para exibir o erro e sua stacktrace no Logcat
    Log.e(tag, this.message ?: "Erro desconhecido", this)
}

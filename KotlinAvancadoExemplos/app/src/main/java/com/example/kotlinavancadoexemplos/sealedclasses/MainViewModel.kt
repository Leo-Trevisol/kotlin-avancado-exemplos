
package com.example.kotlinavancadoexemplos.sealedclasses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Declara a classe MainViewModel que estende ViewModel.
// Aqui colocamos toda a lógica de preparação/fornecimento de dados para a UI.
class MainViewModel : ViewModel() {

    // Cria um MutableStateFlow privado chamado _uiState com valor inicial UiState.Loading.
    // A convenção de prefixo "_" indica que é o campo interno (backing field) que pode ser modificado internamente.
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)

    // Expõe o StateFlow como uma propriedade pública apenas de leitura.
    // Assim, quem observa uiState não pode mutá-lo diretamente — protege o estado contra alterações externas.
    val uiState: StateFlow<UiState> = _uiState

    // Bloco init — executado quando a instância do ViewModel é criada.
    // Aqui usamos para iniciar carregamento de dados automaticamente ao criar o ViewModel.
    init {
        loadData() // Chama a função que simula/realiza o carregamento de dados.
    }

    // Declaração de uma função privada responsável por carregar dados (simulação).
    // Mantemos privada porque só o ViewModel deve controlar quando o carregamento ocorre.
    private fun loadData() {
        // Usa viewModelScope.launch para iniciar uma coroutine segura vinculada ao ViewModel.
        // Tudo que for assíncrono (delay, chamadas de rede, etc.) deve rodar em coroutines, não na UI thread.
        viewModelScope.launch {
            // Simula um atraso (por exemplo, uma requisição de rede) de 2000 ms.
            // delay não bloqueia a thread — suspende a coroutine.
            delay(2000) // Simula carregamento

            // Variável local que representa sucesso/falha da operação.
            // Em um caso real, aqui você avaliaria o resultado da requisição/DAO/etc.
            val success = true

            // Atualiza o valor do MutableStateFlow com um novo estado dependendo do resultado.
            // Atribuir a _uiState.value emite o novo estado para todos os coletores/observadores.
            _uiState.value = if (success) {
                // Se sucesso, define UiState.Success com uma lista de itens (poderia vir da API).
                UiState.Success(listOf("Item 1", "Item 2", "Item 3"))
            } else {
                // Se falha, define UiState.Error com uma mensagem descritiva.
                UiState.Error("Falha ao carregar dados")
            }
        } // fim da coroutine lançada por viewModelScope.launch
    } // fim da função loadData
} // fim da classe MainViewModel

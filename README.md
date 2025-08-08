<h1 align="center">Kotlin Avançado - Exemplos Práticos</h1>

<p align="center">
  Repositório com pequenos exemplos para dominar conceitos avançados de <strong>Kotlin</strong>, essenciais para desenvolvimento Android moderno e backend com Ktor.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-1.8+-blue?logo=kotlin" />
  <img src="https://img.shields.io/badge/Jetpack_Compose-UI-green?logo=jetpack-compose" />
  <img src="https://img.shields.io/badge/Coroutines-Async-yellow?logo=jetbrains" />
  <img src="https://img.shields.io/badge/Flow-Reactive-purple?logo=jetbrains" />
  <img src="https://img.shields.io/badge/Ktor-Backend-lightgrey?logo=ktor" />
</p>

<hr/>

<h2>📚 O que você vai encontrar aqui?</h2>

<ul>
  <li><strong>⚙️ Coroutines e Flow</strong><br/>
    - Programação assíncrona e reativa<br/>
    - Uso básico e avançado de coroutines<br/>
    - Fluxos de dados com Flow e operadores
  </li>
  <li><strong>🧩 Funções de Extensão</strong><br/>
    - Como criar e usar funções que estendem classes existentes<br/>
    - Exemplos práticos para deixar seu código mais limpo e expressivo
  </li>
  <li><strong>🛡️ Sealed Classes</strong><br/>
    - Modelagem segura de hierarquias de tipos<br/>
    - Exemplos para tratar estados e eventos
  </li>
  <li><strong>🌀 Lambdas e Higher-Order Functions</strong><br/>
    - Funções como parâmetros e retorno<br/>
    - Expressões lambda para código funcional
  </li>
  <li><strong>🚫 Null Safety e Smart Casts</strong><br/>
    - Tratamento seguro de valores nulos<br/>
    - Uso de smart casts para evitar verificações manuais
  </li>
  <li><strong>🛠️ DSLs Kotlin</strong><br/>
    - Criação de Domain Specific Languages simples<br/>
    - Exemplos aplicados a Jetpack Compose e Ktor
  </li>
</ul>


<h2>⚙️ Coroutines: Programação Assíncrona Moderna com Kotlin</h2>

<p>
  As <strong>Coroutines</strong> são uma das principais ferramentas para lidar com <em>concorrência</em> e <em>assíncronia</em> de forma eficiente, segura e legível no Kotlin moderno. Elas substituem abordagens antigas como <code>Thread</code>, <code>Handler</code>, <code>AsyncTask</code> e <code>Callbacks</code> com uma API muito mais clara e fluente.
</p>

<h3>✅ O que são Coroutines?</h3>
<p>
  Coroutines são blocos de código que podem ser pausados e retomados de forma cooperativa, sem bloquear a thread principal. Isso permite realizar tarefas demoradas (como chamadas de rede ou acesso a banco de dados) de forma assíncrona sem complicar a estrutura do seu código.
</p>

<h3>📌 Por que usar Coroutines?</h3>
<ul>
  <li>✅ <strong>Não bloqueiam a UI:</strong> tarefas longas rodam em background sem travar a interface.</li>
  <li>✅ <strong>Mais legível que callbacks:</strong> código parece síncrono, mas executa de forma assíncrona.</li>
  <li>✅ <strong>Gerenciamento automático de ciclo de vida:</strong> com <code>viewModelScope</code>, coroutines são canceladas automaticamente.</li>
  <li>✅ <strong>Menos código boilerplate:</strong> sem necessidade de <code>Runnable</code>, <code>Executor</code> ou <code>Callback</code>.</li>
  <li>✅ <strong>Altamente escalável:</strong> milhares de coroutines podem rodar simultaneamente com pouco custo.</li>
</ul>

<h3>🔁 Exemplo comparativo simples</h3>

<p><strong>❌ Antes: Callback com Retrofit</strong></p>
<pre><code class="language-kotlin">
api.getUsers().enqueue(object : Callback&lt;List&lt;User&gt;&gt; {
    override fun onResponse(...) { ... }
    override fun onFailure(...) { ... }
})
</code></pre>

<p><strong>✅ Agora: Coroutine com Retrofit + <code>suspend</code></strong></p>
<pre><code class="language-kotlin">
viewModelScope.launch {
    val users = api.getUsers() // chamada suspensa
    // continua aqui após o retorno, sem callback
}
</code></pre>

<h3>🧪 Exemplo prático usado neste repositório</h3>
<pre><code class="language-kotlin">
fun loadUsers() {
    viewModelScope.launch {
        isLoading = true
        try {
            val result = repository.fetchUsers() // chamada suspensa
            users.clear()
            users.addAll(result)
        } catch (e: Exception) {
            // tratar erro
        } finally {
            isLoading = false
        }
    }
}
</code></pre>

<h3>💡 Recursos envolvidos</h3>
<ul>
  <li><code>suspend fun</code>: usada para declarar funções que podem ser "pausadas".</li>
  <li><code>viewModelScope</code>: escopo de execução atrelado ao ciclo de vida do ViewModel.</li>
  <li><code>launch</code>: inicia uma coroutine.</li>
  <li><code>withContext</code>: muda o contexto da coroutine (ex: <code>Dispatchers.IO</code>).</li>
</ul>

<h3>📚 Aprenda mais</h3>
<p>
  📖 Documentação oficial: 
  <a href="https://kotlinlang.org/docs/coroutines-overview.html" target="_blank">Kotlin Coroutines</a><br/>
  📦 Biblioteca: <code>org.jetbrains.kotlinx:kotlinx-coroutines-core</code>
</p>

<h2>🔄 Flow: Fluxos de Dados Reativos com Coroutines</h2>

<p>
  <strong>Flow</strong> é uma API do Kotlin para lidar com <em>fluxos de dados assíncronos e reativos</em>, construída sobre o poder das <code>coroutines</code>. Ele permite emitir múltiplos valores ao longo do tempo, de forma sequencial e não bloqueante.
</p>

<h3>🌊 O que é Flow?</h3>
<p>
  Um <code>Flow</code> representa um stream de valores que são produzidos de forma assíncrona. Diferente de uma função <code>suspend</code> (que retorna apenas um valor), um Flow pode emitir <strong>vários valores ao longo do tempo</strong>.
</p>

<p>É uma alternativa moderna e mais segura a ferramentas como <code>LiveData</code>, <code>RxJava</code> ou <code>Callback-based listeners</code>.</p>

<h3>✅ Vantagens do Flow</h3>
<ul>
  <li><strong>Reatividade:</strong> responde automaticamente a mudanças de dados.</li>
  <li><strong>Assíncrono:</strong> usa coroutines internamente e não bloqueia threads.</li>
  <li><strong>Cancelável:</strong> integrado com escopos como <code>viewModelScope</code> ou <code>lifecycleScope</code>.</li>
  <li><strong>Composição poderosa:</strong> oferece operadores como <code>map</code>, <code>filter</code>, <code>debounce</code>, <code>collect</code>, etc.</li>
</ul>

<h3>📌 Flow vs suspend</h3>
<ul>
  <li><code>suspend fun</code>: retorna <strong>um valor único</strong>.</li>
  <li><code>Flow</code>: emite <strong>múltiplos valores ao longo do tempo</strong>.</li>
</ul>

<h3>🧪 Exemplo básico de Flow</h3>
<pre><code class="language-kotlin">
fun countToFive(): Flow&lt;Int&gt; = flow {
    for (i in 1..5) {
        emit(i) // emite cada número com um pequeno atraso
        delay(1000)
    }
}
</code></pre>

<h3>🧪 Coletando o Flow</h3>
<pre><code class="language-kotlin">
viewModelScope.launch {
    countToFive().collect { number ->
        println("Recebido: $number")
    }
}
</code></pre>

<h3>📦 Quando usar Flow?</h3>
<ul>
  <li>Para observar dados em tempo real (ex: banco de dados, rede, sensores).</li>
  <li>Para criar pipelines de transformação de dados reativos.</li>
  <li>Para evitar <code>LiveData</code> em arquiteturas baseadas em Compose.</li>
</ul>

<h3>📚 Aprenda mais</h3>
<p>
  Documentação oficial: 
  <a href="https://kotlinlang.org/docs/flow.html" target="_blank">Kotlin Flow</a><br/>
  Biblioteca: <code>org.jetbrains.kotlinx:kotlinx-coroutines-core</code>
</p>

<h2>🧩 Funções de Extensão no Kotlin</h2>

<p>
  Funções de extensão são uma das características mais poderosas do Kotlin. Elas permitem "adicionar" funcionalidades a classes já existentes sem precisar herdá-las ou usar padrões como <code>Decorator</code>. São amplamente usadas no Android moderno para deixar o código mais limpo, reutilizável e expressivo.
</p>

<h3>🔹 O que é uma função de extensão?</h3>
<p>
  É uma função que pode ser chamada como se fizesse parte da própria classe, mesmo que não tenha sido originalmente declarada nela. É muito usada para <strong>reduzir boilerplate</strong> e <strong>encapsular lógicas reutilizáveis</strong>.
</p>

<h3>💡 Exemplos práticos usados neste repositório</h3>

<pre><code class="language-kotlin">
// Capitaliza a primeira letra de cada palavra
"ola mundo".capitalizeWords() // → "Ola Mundo"

// Converte uma lista em string separada por vírgulas
listOf("A", "B", "C").toCommaSeparated() // → "A, B, C"

// Converte timestamp para data formatada
System.currentTimeMillis().toFormattedDate() // → "07/08/2025"

// Mostrar ou esconder teclado
editText.showKeyboard()
editText.hideKeyboard()

// Escutar mudanças de texto
editText.onTextChanged { novoTexto -> ... }

// Logar erros com stacktrace completo
throwable.logStackTrace()
</code></pre>

<h3>🔗 Benefícios das funções de extensão</h3>
<ul>
  <li><strong>Melhor legibilidade:</strong> transforma funções utilitárias em chamadas fluentes.</li>
  <li><strong>Organização:</strong> permite separar funcionalidades por arquivos sem precisar modificar a classe original.</li>
  <li><strong>Reuso:</strong> fácil de aplicar em múltiplas telas/componentes.</li>
  <li><strong>Compatível com qualquer classe:</strong> pode ser usada com APIs Android, Java e do próprio Kotlin.</li>
</ul>

<h3>✨ Dica:</h3>
<p>
  Use extensões para lidar com <code>String</code>, <code>View</code>, <code>EditText</code>, <code>Throwable</code> e <code>Long</code> para encapsular transformações e comportamentos comuns de forma reutilizável.
</p>

<h3>📚 Aprenda mais</h3>
<p>
  Documentação oficial:
  <a href="https://kotlinlang.org/docs/extensions.html" target="_blank">Funções de Extensão Kotlin</a>
</p>

<h2>🛡️ Sealed Classes no Kotlin</h2>

<p>
  As <strong>Sealed Classes</strong> são um recurso poderoso do Kotlin para modelar hierarquias de tipos de forma <em>restrita e segura</em>. 
  Diferente de classes abertas (<code>open</code>), elas permitem definir um conjunto <strong>fechado</strong> de subclasses possíveis, 
  garantindo que todos os casos sejam tratados em tempo de compilação.
</p>

<h3>🔹 O que são Sealed Classes?</h3>
<p>
  São classes que limitam quais tipos podem herdá-las, geralmente usadas para representar <strong>estados</strong> e <strong>eventos</strong> 
  em fluxos de dados. Todas as subclasses devem ser definidas no mesmo arquivo da sealed class.
</p>

<h3>✅ Vantagens</h3>
<ul>
  <li><strong>Segurança de tipo:</strong> o compilador garante que todos os casos sejam tratados no <code>when</code>.</li>
  <li><strong>Código mais expressivo:</strong> fácil de entender quais estados/eventos existem.</li>
  <li><strong>Manutenção simples:</strong> adicionar um novo estado força o ajuste em todos os locais relevantes.</li>
</ul>

<h3>🧪 Exemplo prático deste repositório</h3>
<pre><code class="language-kotlin">
// UiState.kt
sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List&lt;String&gt;) : UiState()
    data class Error(val message: String) : UiState()
}
</code></pre>

<h3>📌 Uso típico com when</h3>
<pre><code class="language-kotlin">
fun render(state: UiState) {
    when (state) {
        is UiState.Loading -&gt; showLoading()
        is UiState.Success -&gt; showData(state.data)
        is UiState.Error -&gt; showError(state.message)
    }
}
</code></pre>

<h3>💡 Quando usar Sealed Classes?</h3>
<ul>
  <li>Para representar <strong>estados de UI</strong> (Loading, Success, Error).</li>
  <li>Para modelar <strong>eventos de navegação</strong> ou <strong>ações do usuário</strong>.</li>
  <li>Para criar <strong>APIs seguras</strong> onde apenas casos conhecidos são permitidos.</li>
</ul>

<h3>📚 Aprenda mais</h3>
<p>
  Documentação oficial:
  <a href="https://kotlinlang.org/docs/sealed-classes.html" target="_blank">Sealed Classes no Kotlin</a>
</p>





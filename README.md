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
  <li><strong>1. Coroutines e Flow</strong><br/>
    - Programação assíncrona e reativa<br/>
    - Uso básico e avançado de coroutines<br/>
    - Fluxos de dados com Flow e operadores
  </li>
  <li><strong>2. Funções de Extensão</strong><br/>
    - Como criar e usar funções que estendem classes existentes<br/>
    - Exemplos práticos para deixar seu código mais limpo e expressivo
  </li>
  <li><strong>3. Sealed Classes</strong><br/>
    - Modelagem segura de hierarquias de tipos<br/>
    - Exemplos para tratar estados e eventos
  </li>
  <li><strong>4. Lambdas e Higher-Order Functions</strong><br/>
    - Funções como parâmetros e retorno<br/>
    - Expressões lambda para código funcional
  </li>
  <li><strong>5. Null Safety e Smart Casts</strong><br/>
    - Tratamento seguro de valores nulos<br/>
    - Uso de smart casts para evitar verificações manuais
  </li>
  <li><strong>6. DSLs Kotlin</strong><br/>
    - Criação de Domain Specific Languages simples<br/>
    - Exemplos aplicados a Jetpack Compose e Ktor
  </li>
</ul>

<h2>🚀 Coroutines: Programação Assíncrona Moderna com Kotlin</h2>

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


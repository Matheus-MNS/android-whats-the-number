# android-whats-the-number



<p>Foi desenvolvido usando a arquitetura MVVM, foram utilizadas as bibliotecas abaixo:</p>
<p>Koin Libary - Usada para implementar a injeção de dependência;</p>
<p>Navigation library - Usada na implementação dos componentes de navegação;</p>
<p>Lifecycle library - Usada para implementar a ViewModel/LiveData;</p>
<p>Retrofit library - Usada para implementação do consumo de dados entre aplicação e webservice;</p>
<p>Gson library - Usada para a leitura dos arquivos Json enviados pela API;</p>
<p>Coroutines library - Usada para realizar tarefas assíncronas, para não sobrecarregar a thread principal. </p>
<p>Foi usado o conceito de single activity, tornando a main activity o container para os fragments, para que o app seja mais escalável. </p>


<p> O app foi estruturado pensando numa possível escalabilidade, o pacote feature onde podem devem ser criadas as várias funcionalidades ao app, no pacote common teremos utilidade que poderão ser utilizadas em todo o app e o pacote base_app consta com a inicialização da injeção de dependência e a activity principal. Dentro de cada feature encontramos os pacotes:
<p>data: Pacote contendo a camada responsável pelos dados, foi criado uma interface com o método de realização da requisição, que é chamado pelo Remote DataSource que armazena a resposta em um Flow que será decodificado posteriormente pelo coroutines; 
</p>
<p>domain: No domain temos a interface do repositório que serve como assinatura da implementação do repositório e se encontra na camada de dados. O use case é responsável pela comunicação entre camada de domain e presentation; </p> 
<p>presentantion: Na camada de presentation a view model recebe o use case como parâmetro e através dessa instancia é utilizado o coroutines que decodifica a resposta da api entre sucesso e erro. De acordo com a resposta a informação é feita a comparação entre os números e é devolvido para a fragment o estado da comparação ou erro. A fragment observa os valores da view model e faz as alterações necessárias para exibição das informações;</p>
<p>di: No di temos os módulos de injeção de dependência por camada. </p>

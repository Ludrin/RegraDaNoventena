# Regra da Noventena

Esse � um programa desenvolvido por Ernani Ricoy para o processo de sele��o de engenheiro de software da Tracksale. O programa foi desenvolvido em Java.

# Como usar

Foi criada uma interface CMD simples com a finalidade de testar a implementa��o da l�gica realizada na classe _GuavaCache_. Basta rodar a classe _CmdInterface_ do pacote _cmdInterface_ e seguir as instru��es impressas.

# Explica��o Geral

O programa tenta simular a seguinte situa��o: sup�e-se que a Tracksale tenha uma ou mais empresas como clientes (aqui referida como Amazon e Google, por exemplo). Primeiramente os clientes Amazon e Google devem ser registrados em uma base de dados de clientes. Quando se deseja realizar uma pesquisa no cliente � necess�rio checar se o cliente j� foi afetado nos �ltimos 90 dias por uma pesquisa. Caso n�o tenha, uma pesquisa pode ser realizada. Os dados de pesquisa ficam salvos em cache; isto �: __o cache � uma unidade de mem�ria na qual todos os clientes onde uma pesquisa foi realizada nos �ltimos 90 dias ficam salvos__.

Caso uma pesquisa tenha sido feita na Amazon o nome da empresa ficar� salvo em cache por 90 dias. Ap�s esse per�odo o nome da empresa ser� apagado do cache.

Tamb�m deve ser poss�vel listar todos os clientes na base de dados, listar todos os clientes salvos em cache (onde uma pesquisa foi realizada nos �ltimos 90 dias), remover manualmente um ou todos os clientes do cache.

# Explica��o T�cnica

  - A biblioteca Guava foi escolhida por possuir um mecanismo b�sico de cache. Os nomes de empresas com letras todas mai�sculas (convertidas pelo sistema) s�o salvos como chave e valor. Em uma aplica��o real haveria um objeto Empresa com todas os dados da mesma.
  - � poss�vel especificar a quantidade de tempo que um valor fica salvo em cache pela implementa��o Guava. Para tornar os testes desse programa vi�veis ao inv�s de 90 dias de espera para uma entrada do cache ser limpo foi usado 90 segundos. O tempo desejado pode ser alterado passando um par�metro para o construtor da classe _GuavaCache_.
  - O banco de dados no qual os dados dos clientes s�o salvos no programa � representado por um _Set<String>_ onde s�o salvos os nomes das empresas. Isso � para facilitar a execu��o do programa, sem ser necess�rio a instala��o de um banco de dados externo. Em uma aplica��o real haveria um objeto Empresa com todas os dados da mesma.
  - A biblioteca JUnit foi escolhida para a modelagem dos testes unit�rios.
  - A classe _CmdInterface_ n�o possui testes por se tratar de uma interface gr�fica de testes.
  - As bibliotecas _guava-23.0_, _hamercrest-core-2.1_ e _junit-4.13-beta-2_ est�o na pasta lib para facilitar pelo programa ser simples e para facilitar a execu��o do mesmo sem ser necess�rio instala��o por _maven_ ou _gradle_. Em uma aplica��o real essas ferramentas seriam utilizadas.
  
# Cr�ditos

[GitHub do Guava](https://github.com/google/guava)

[Site do JUnit](https://junit.org/junit4/)
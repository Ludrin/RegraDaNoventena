# Regra da Noventena

Esse é um programa desenvolvido por Ernani Ricoy para o processo de seleção de engenheiro de software da Tracksale. O programa foi desenvolvido em Java.

# Como usar

Foi criada uma interface CMD simples com a finalidade de testar a implementação da lógica realizada na classe _GuavaCache_. Basta rodar a classe _CmdInterface_ do pacote _cmdInterface_ e seguir as instruções impressas.

# Explicação Geral

O programa tenta simular a seguinte situação: supõe-se que a Tracksale tenha uma ou mais empresas como clientes (aqui referida como Amazon e Google, por exemplo). Primeiramente os clientes Amazon e Google devem ser registrados em uma base de dados de clientes. Quando se deseja realizar uma pesquisa no cliente é necessário checar se o cliente já foi afetado nos últimos 90 dias por uma pesquisa. Caso não tenha, uma pesquisa pode ser realizada. Os dados de pesquisa ficam salvos em cache; isto é: __o cache é uma unidade de memória na qual todos os clientes onde uma pesquisa foi realizada nos últimos 90 dias ficam salvos__.

Caso uma pesquisa tenha sido feita na Amazon o nome da empresa ficará salvo em cache por 90 dias. Após esse período o nome da empresa será apagado do cache.

Também deve ser possível listar todos os clientes na base de dados, listar todos os clientes salvos em cache (onde uma pesquisa foi realizada nos últimos 90 dias), remover manualmente um ou todos os clientes do cache.

# Explicação Técnica

  - A biblioteca Guava foi escolhida por possuir um mecanismo básico de cache. Os nomes de empresas com letras todas maiúsculas (convertidas pelo sistema) são salvos como chave e valor. Em uma aplicação real haveria um objeto Empresa com todas os dados da mesma.
  - É possível especificar a quantidade de tempo que um valor fica salvo em cache pela implementação Guava. Para tornar os testes desse programa viáveis ao invés de 90 dias de espera para uma entrada do cache ser limpo foi usado 90 segundos. O tempo desejado pode ser alterado passando um parâmetro para o construtor da classe _GuavaCache_.
  - O banco de dados no qual os dados dos clientes são salvos no programa é representado por um _Set<String>_ onde são salvos os nomes das empresas. Isso é para facilitar a execução do programa, sem ser necessário a instalação de um banco de dados externo. Em uma aplicação real haveria um objeto Empresa com todas os dados da mesma.
  - A biblioteca JUnit foi escolhida para a modelagem dos testes unitários.
  - A classe _CmdInterface_ não possui testes por se tratar de uma interface gráfica de testes.
  - As bibliotecas _guava-23.0_, _hamercrest-core-2.1_ e _junit-4.13-beta-2_ estão na pasta lib para facilitar pelo programa ser simples e para facilitar a execução do mesmo sem ser necessário instalação por _maven_ ou _gradle_. Em uma aplicação real essas ferramentas seriam utilizadas.
  
# Créditos

[GitHub do Guava](https://github.com/google/guava)

[Site do JUnit](https://junit.org/junit4/)
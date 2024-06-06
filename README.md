<table>
<tr>
<td>
<a href= "https://vale.com/pt/"><img src="https://upload.wikimedia.org/wikipedia/pt/c/cc/Logotipo_Vale.svg" alt="Vale" border="0" width="60%"></a>
</td>
<td><a href= "https://www.inteli.edu.br/"><img src="./inteli-logo.png" alt="Inteli - Instituto de Tecnologia e Liderança" border="0" width="40%"></a>
</td>
</tr>
</table>

# Introdução

Este é um dos repositórios do projeto de alunos do Inteli em parceria com a Vale no 1º semestre de 2024. Este projeto está sendo desenvolvido por alunos do Módulo 5 do curso de Ciência da Computação.

# Projeto: Aumento da performance logística na distribuição de minério de ferro

# Grupo: *Vale Vision*

# Integrantes:

* <a href="https://www.linkedin.com/in/andr%C3%A9-hutzler-60aa28277/">Andre Hutzler</a>
* <a href="https://www.linkedin.com/in/diogo-pelaes-a34593279/">Diogo Burgierman</a>
* <a href="https://www.linkedin.com/in/eduardo-simonis-ferrari/">Eduardo Ferrari</a>
* <a href="https://www.linkedin.com/in/filipe-calabro-3b3517243/">Filipe Calabro</a>
* <a href="https://www.linkedin.com/in/gabriel-demacedosantos/">Gabriel de Macedo Santos</a>
* <a href="https://www.linkedin.com/in/raissa-paula/">Raissa de Cássia Moraes Paula</a>

# Descrição

&emsp;&emsp;Este projeto visa resolver o desafio de maximizar a eficiência na distribuição de minério de ferro da Vale aos clientes, considerando as diversas características do minério, capacidades de transporte e demandas específicas dos clientes. A solução proposta consiste no desenvolvimento de um software que utilize modelagem em grafos para planejar os fluxos de minério, otimizando os recursos disponíveis na malha logística da empresa. O software receberá como entrada dados como capacidades de produção das usinas, capacidades e lead times de transporte, e demandas dos clientes, gerando como saída os fluxos de minério que atendam cada cliente com seu respectivo tipo de transporte. Inicialmente, o foco estará na região Sudeste da Vale, atendendo às demandas dos clientes até o Porto de Tubarão.

# Configuração de desenvolvimento

## Pré-requisitos

Antes de começar, certifique-se de ter os seguintes pré-requisitos instalados em seu sistema:

- Node.js (https://nodejs.org/en/download)
- .NET SDK (https://dotnet.microsoft.com/pt-br/download/visual-studio-sdks)
- Java JDK (https://www.oracle.com/br/java/technologies/downloads/)

## Instalação e Execução do Projeto

1. **Clonar o Repositório**: Clone este repositório em sua máquina local usando o seguinte comando:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```

2. **Instalação de Dependências do Frontend (Node.js)**:
   - Navegue até o diretório do frontend:
     ```bash
     cd codigo/frontend
     ```
   - Instale as dependências usando npm:
     ```bash
     npm install
     ```

3. **Execução do Aplicativo Frontend**:
   - Após a instalação das dependências, execute o aplicativo frontend com o seguinte comando:
     ```bash
     npm start
     ```


# Releases

* SPRINT1: *Durante a primeira sprint, focamos na análise de negócios da Vale e do projeto e na modelagem matemática da solução além de priorizarmos o entendimento da experiência do usuário na utilização da solução.*
* SPRINT2: *Durante a segunda sprint, refatoramos a modelagem matemática, construímos classes do domínio do problema, criamos um diagrama UML, implementamos testes unitários para cada classe e finalizamos a introdução e metodologia do artigo.*
* *SPRINT3: Durante a terceira sprint, refatoramos a modelagem matemática, construímos classes do domínio do problema, criamos um diagrama UML, implementamos testes unitários para cada classe e concluímos a introdução e a metodologia do artigo. Além disso, realizamos o tratamento dos dados, convertendo todo o XML em um único arquivo CSV.*
* SPRINT4: *Durante a quarta sprint, realizamos a refatoração dos algoritmos para que eles fossem capazes de retornar tanto o fluxo quanto o caminho percorrido nas arestas e nós. Além disso, demonstramos a complexidade e a correção dos algoritmos implementados. Adicionalmente, desenvolvemos o backend da solução para que pudesse executar com eficácia os dados enviados pelo cliente e enviar as respostas dos algoritmos de volta ao usuário.*
* SPRINT5: *Durante esta sprint, foram realizados refinamentos em todos os artefatos já entregues e hotfixes em erros que existiam dentro da nossa aplicação. Além disso, o artigo científico sobre o problema da Vale, agora solucionado, foi concluído, demonstrando resultados, análises e conclusões.*

## 📋 Licença/License

<img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/cc.svg?ref=chooser-v1"><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/by.svg?ref=chooser-v1"><p xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/">

<a property="dct:title" rel="cc:attributionURL">Vale Vision</a> by <a rel="cc:attributionURL dct:creator" property="cc:attributionName">Inteli</a>, <a href="https://www.linkedin.com/in/andr%C3%A9-hutzler-60aa28277/">Andre Hutzler</a>, <a href="https://www.linkedin.com/in/diogo-pelaes-a34593279/">Diogo Burgierman</a>, <a href="https://www.linkedin.com/in/eduardo-simonis-ferrari/">Eduardo Ferrari</a>, <a href="https://www.linkedin.com/in/filipe-calabro-3b3517243/">Filipe Calabro</a>, <a href="https://www.linkedin.com/in/gabriel-demacedosantos/">Gabriel de Macedo Santos</a>, <a href="https://www.linkedin.com/in/raissa-paula/">Raissa de Cássia Moraes Paula</a> is licensed under <a href="https://creativecommons.org/licenses/by/4.0/?ref=chooser-v1" rel="license noopener noreferrer" style="display:inline-block;">Attribution 4.0 International</a>.</p>

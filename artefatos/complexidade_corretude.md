# Complexidade e corretude do algoritmo

## Análises dos Algoritmos Implementados

### Ford Fulkerson
### Algoritmo Ford-Fulkerson

&emsp;&emsp;O algoritmo Ford-Fulkerson é uma técnica para encontrar o fluxo máximo em uma rede de fluxo, que é um grafo direcionado onde cada aresta tem uma capacidade máxima e um fluxo. O problema do fluxo máximo envolve determinar a quantidade máxima de fluxo que pode ser enviado de uma origem para um destino em uma rede.

#### Funcionamento

1. **Inicialização**: Comece com todos os fluxos de todas as arestas definidos como zero.
2. **Encontrar um caminho aumentador**: Encontre um caminho simples (um caminho sem ciclos) da origem ao destino onde há capacidade residual (capacidade máxima - fluxo atual) em cada aresta. Isso pode ser feito usando uma busca em largura ou profundidade.
3. **Atualizar o fluxo**: Aumente o fluxo ao longo do caminho encontrador pelo menor valor de capacidade residual encontrada.
4. **Repetição**: Repita os passos 2 e 3 até não houver mais caminhos aumentadores.

#### Análise de Complexidade Ford Fulkerson

#### Melhor Caso
&emsp;&emsp;No melhor cenário, onde todas as capacidades das arestas são iguais, a complexidade do algoritmo Ford-Fulkerson é geralmente dominada pela busca pelo caminho aumentador. A busca em largura ou profundidade para encontrar um caminho aumentador pode ser realizada em tempo linear em relação ao número de vértices $((V))$ e arestas $((E))$ no grafo. Portanto, a complexidade no melhor caso é tipicamente expressa como $(O(V + E))$.

#### Caso Médio
&emsp;&emsp;A complexidade média do algoritmo Ford-Fulkerson depende da estrutura específica da rede de fluxo. Em média, espera-se que o algoritmo execute um número de iterações proporcional ao fluxo máximo $((f))$ multiplicado pelo número de arestas $((E))$. Assim, a complexidade média é $(O(f \cdot E))$.

### Razões para Escolher o Algoritmo Ford-Fulkerson

1. **Versatilidade**: O algoritmo Ford-Fulkerson pode ser aplicado a uma ampla gama de problemas de fluxo máximo em diferentes domínios, como redes de transporte, redes de comunicação e planejamento de produção.

2. **Facilidade de Implementação**: O algoritmo é conceitualmente simples e pode ser implementado facilmente usando técnicas básicas de grafos, como busca em largura ou profundidade para encontrar caminhos aumentadores.

3. **Eficiência Prática**: Embora o pior caso possa ser exponencial, na prática, o algoritmo pode ser eficiente o suficiente para muitos problemas de tamanho razoável. Além disso, existem variantes do algoritmo, como o algoritmo de Edmonds-Karp, que garantem um desempenho mais previsível.

4. **Ampla Aplicabilidade**: O algoritmo Ford-Fulkerson pode ser aplicado a problemas com múltiplas origens e destinos, bem como a redes com capacidades variáveis nas arestas.

5. **Flexibilidade**: O algoritmo permite a fácil modificação para lidar com requisitos específicos do problema, como capacidades mínimas ou custos associados às arestas.

&emsp;&emsp;Em resumo, o algoritmo Ford-Fulkerson é uma escolha sólida para problemas de fluxo máximo devido à sua versatilidade, facilidade de implementação e eficiência prática. No entanto, é essencial considerar suas complexidades em diferentes cenários e, se necessário, explorar outras abordagens ou variantes do algoritmo para otimização adicional.

### Demonstração da Invariante do Laço:

&emsp;&emsp;Para expressar a invariante do laço matematicamente, definimos $( V' )$ como o conjunto de vértices visitados até o momento atual durante a execução do algoritmo. A invariante do laço é detalhada da seguinte forma:

1. *Inicialização*: No início do algoritmo, apenas o vértice de origem é visitado:$( V' = \{source\} )$.
2. *Manutenção*:  Em cada iteração do laço, um vértice $( u )$ é retirado da pilha e seus vizinhos $( v )$ não visitados, com capacidade residual positiva, são explorados e adicionados à pilha, marcados como visitados. Portanto, para cada vértice  $( v )$ adjacente a $( u )$ com capacidade residual positiva, tal que $( v \notin V' )$, $( V' )$ é atualizado para incluir $( v )$, ou seja, $( V' = V' \cup \{v\} )$.
3. *Término*: O laço termina quando a pilha estiver vazia ou quando o vértice de destino for alcançado. Neste ponto, todos os vértices alcançáveis a partir da origem foram visitados, ou seja, $( V' )$ contém todos os vértices alcançáveis a partir da origem no grafo residual.

&emsp;&emsp;Portanto, matematicamente, a invariante do laço pode ser expressa como:

$$
V' = \{source\} \cup \{v \, | \, v \text{ é alcançável a partir de } source \text{ no grafo residual e foi visitado}\}
$$

&emsp;&emsp;Essa expressão matemática descreve a invariante do laço no algoritmo de busca em profundidade.

### Demonstrar que o Algoritmo Termina:

- O algoritmo Ford-Fulkerson termina quando não há mais caminhos aumentadores na rede residual.
- Como a capacidade mínima das arestas em um caminho aumentador é sempre maior que zero, a cada iteração, o fluxo aumenta.
- Como o valor do fluxo é limitado superiormente pelo fluxo máximo $( F_{max} )$, o algoritmo eventualmente terminará.

### Provar a Corretude do Fluxo Encontrado:

- O algoritmo Ford-Fulkerson atualiza o fluxo ao longo dos caminhos aumentadores.
- Isso é feito aumentando o fluxo ao longo das arestas no caminho e diminuindo a capacidade residual correspondente.
- Para cada iteração do algoritmo, o fluxo aumenta até atingir $( F_{max} )$.
- Portanto, o fluxo encontrado pelo algoritmo é de fato o fluxo máximo.
- O fluxo em qualquer aresta não excede sua capacidade.
- O fluxo total em uma aresta é igual ao fluxo total na direção oposta, pois o fluxo é conservado em cada vértice, exceto no vértice de origem e no vértice de destino.
- O fluxo total na rede é igual ao fluxo total que sai do vértice de origem e entra no vértice de destino.

### Aplicar Indução Matemática:

- Podemos formalizar a corretude do algoritmo Ford-Fulkerson por indução matemática.
- A base da indução é o caso inicial, onde o fluxo é zero.
- A hipótese de indução é que, após $( k )$ iterações, o algoritmo encontra um fluxo máximo.
- O passo de indução mostra que, após $( k + 1 )$ iterações, o algoritmo continua a encontrar um fluxo máximo.
- Portanto, a corretude do algoritmo é estabelecida por indução.

### Conclusão:

&emsp;&emsp;Usando esses artifícios matemáticos, podemos demonstrar a corretude do algoritmo Ford-Fulkerson de forma rigorosa e formal. Ele garante a existência de um fluxo máximo na rede e demonstra que o algoritmo termina e encontra esse fluxo máximo de maneira correta.

### Edmonds-Karp

&emsp;&emsp;O algoritmo de Edmonds-Karp é uma variante do algoritmo Ford-Fulkerson para encontrar o fluxo máximo em uma rede de fluxo. Assim como o Ford-Fulkerson, ele lida com grafos direcionados onde cada aresta possui uma capacidade máxima e um fluxo.

#### Funcionamento

1. **Inicialização**: Comece com todos os fluxos de todas as arestas definidos como zero.
2. **Encontrar um caminho aumentador usando BFS**: Utilize a busca em largura (BFS) para encontrar um caminho mais curto da origem ao destino onde ainda há capacidade residual em cada aresta.
3. **Atualização do fluxo**: Aumente o fluxo ao longo do caminho encontrado pelo menor valor de capacidade residual encontrada.
4. **Repetição**: Repita os passos 2 e 3 até não houver mais caminhos aumentadores.

#### Análise de Complexidade Edmonds-Karp

#### Complexidade
&emsp;&emsp;A complexidade observada no algoritmo Edmonds-Karp é dominada pela busca em largura (BFS) para encontrar o caminho aumentador. A BFS é executada em tempo linear em relação ao número de vértices $(V)$ e arestas $(E)$ no grafo. Portanto, a complexidade do algoritmo Edmonds-Karp pode ser definida tipicamente como $O(VE^2)$.

### Razões para Escolher o Algoritmo Edmonds-Karp

1. **Desempenho Previsível**: O algoritmo de Edmonds-Karp garante um desempenho mais previsível em comparação com o Ford-Fulkerson devido à escolha da BFS para encontrar caminhos aumentadores.

2. **Eficiência Prática**: Apesar da complexidade no pior caso, na prática, o algoritmo de Edmonds-Karp pode ser eficiente o suficiente para muitos problemas de tamanho razoável.

3. **Implementação Simples**: Assim como o Ford-Fulkerson, o algoritmo de Edmonds-Karp é conceitualmente simples e pode ser implementado facilmente usando técnicas básicas de grafos.

4. **Aplicabilidade Geral**: O algoritmo de Edmonds-Karp é aplicável a uma variedade de problemas de fluxo máximo em diferentes domínios.

5. **Flexibilidade**: O algoritmo pode ser facilmente modificado para atender a requisitos específicos do problema, como capacidades mínimas ou custos associados às arestas.

&emsp;&emsp;Em suma, o algoritmo de Edmonds-Karp é uma escolha sólida para problemas de fluxo máximo devido ao seu desempenho previsível, eficiência prática e facilidade de implementação. No entanto, é importante considerar suas complexidades em diferentes cenários e avaliar alternativas se necessário.

### Demonstração da Invariante do Laço:

&emsp;&emsp;A invariante do laço para o algoritmo de Edmonds-Karp usando BFS pode ser expressa matematicamente como:

1. *Inicialização*: No começo do algoritmo, apenas o vértice de origem é visitado: $( V' = \{source\} )$.
2. *Manutenção*: Em cada iteração, o algoritmo visita os vértices alcançáveis a partir da origem, adicionando-os a $( V' )$
em ordem crescente de distância.
3. *Término*: O laço termina quando não há mais vértices alcançáveis a partir da origem ou quando o destino é alcançado. Neste ponto, todos os vértices alcançáveis foram visitados.

&emsp;&emsp;Matematicamente, a invariante do laço pode ser expressa como:

$$
V' = \{ v \mid v \text{ é alcançável a partir de } source \text{ no grafo residual e foi visitado} \}
$$

&emsp;&emsp;Essa expressão matemática descreve a invariante do laço para o algoritmo de Edmonds-Karp usando BFS, onde $( V' )$ representa o conjunto de vértices visitados durante a execução do algoritmo até o momento atual.


### Demonstrar que o Algoritmo Termina:
- O algoritmo Edmonds-Karp é uma variante do Ford-Fulkerson que utiliza a busca em largura para encontrar caminhos aumentadores na rede residual.
- Como a busca em largura sempre encontra o caminho aumentador com o menor número de arestas, e como cada iteração aumenta o fluxo ao longo deste caminho, o fluxo total aumenta ou permanece constante a cada iteração.
- Como a capacidade mínima das arestas em um caminho aumentador é sempre maior que zero e o número de iterações é finito, eventualmente não haverá mais caminhos aumentadores na rede residual.
- Portanto, o algoritmo Edmonds-Karp termina quando não há mais caminhos aumentadores na rede residual.
- Como o valor do fluxo é limitado superiormente pelo fluxo máximo $( F_{max} )$, o algoritmo Edmonds-Karp eventualmente terminará.

### Provar a Corretude do Fluxo Encontrado:
- O algoritmo Edmonds-Karp atualiza o fluxo ao longo dos caminhos aumentadores encontrados pela busca em largura.
- Isso é feito aumentando o fluxo ao longo das arestas no caminho e diminuindo a capacidade residual correspondente.
- A cada iteração do algoritmo, o fluxo aumenta até atingir $( F_{max} )$.
- Portanto, o fluxo encontrado pelo algoritmo é de fato o fluxo máximo.
- O fluxo em qualquer aresta não excede sua capacidade, pois o algoritmo escolhe sempre o caminho aumentador com a menor capacidade residual.
- O fluxo total em uma aresta é igual ao fluxo total na direção oposta, pois o fluxo é conservado em cada vértice, exceto no vértice de origem e no vértice de destino.
- O fluxo total na rede é igual ao fluxo total que sai do vértice de origem e entra no vértice de destino.

### Aplicar Indução Matemática:
- Podemos formalizar a corretude do algoritmo Edmonds-Karp por indução matemática.
- A base da indução é o caso inicial, onde o fluxo é zero.
- A hipótese de indução é que, após $( k )$ iterações, o algoritmo encontra um fluxo máximo.
- O passo de indução mostra que, após $( k + 1 )$ iterações, o algoritmo continua a encontrar um fluxo máximo.
- Portanto, a corretude do algoritmo é estabelecida por indução.
### Conclusão:

&emsp;&emsp;Usando esses artifícios matemáticos, podemos demonstrar a corretude do algoritmo Edmonds-Karp de forma rigorosa e formal. Ele garante a existência de um fluxo máximo na rede e demonstra que o algoritmo termina e encontra esse fluxo máximo de maneira correta.


## Referencias
FEOFILOFF, P. Implementação do algoritmo de Ford-Fulkerson. Disponível em: <https://www.ime.usp.br/~pf/algoritmos_para_grafos/aulas/flowdatastruct.html>. Acesso em: 26 mar. 2024a.


FEOFILOFF, P. Algoritmo de Ford-Fulkerson. Disponível em: <https://www.ime.usp.br/~pf/algoritmos_para_grafos/aulas/flow-FF.html>. Acesso em: 26 mar. 2024b.


Untitled Document. Disponível em: <https://www.ic.unicamp.br/~meidanis/courses/mo417/2003s1/aulas/2003-06-11.html>. Acesso em: 26 mar. 2024.


Disponível em: <https://linux.ime.usp.br/~marcosk/mac0499/files/monografia.pdf>. Acesso em: 26 mar. 2024.


# Diagrama de classes do domínio do problema

&emsp;&emsp;Um Diagrama de Classes UML é uma representação visual que descreve a estrutura estática de um sistema de software. Ele é uma ferramenta fundamental na modelagem orientada a objetos, permitindo aos desenvolvedores entender e comunicar a organização das classes, seus atributos, métodos e relacionamentos dentro do sistema. Para resolver o problema do fluxo máximo da Vale, o diagrama abaixo foi implementado:

<div align="center">
<sub>Figura 1 - Diagrama UML de classes</sub>
<img src="../artefatos/imagens/ValeVisionUML.drawio .png">
<sup>Fonte: Material produzido pelos autores (2024)</sup>
</div>

### Explicação das classes

1. **TransportationMode** (Modo de Transporte):

&emsp;&emsp;Esta classe representa os diferentes modos de transporte disponíveis para a distribuição de minério de ferro da Vale aos clientes, como caminhões, ferrovias, entre outros. Cada modo de transporte possui diferentes capacidades e lead times, influenciando diretamente na eficiência da distribuição.

2. **Path** (Caminho):

&emsp;&emsp;Um Path representa uma rota específica ou caminho que pode ser percorrido por um meio de transporte. Está associado ao TransportationMode e define as rotas possíveis para o transporte do minério, considerando diferentes meios de transporte e suas características, como seus custos.

3. **Node** (Nó):

&emsp;&emsp;Node é uma superclasse que representa pontos de origem, destino ou intermediários na rede logística da Vale. Pode ser uma mina, um cliente, um ponto de armazenamento intermediário ou um porto. A classe Node é fundamental para a modelagem da rede de distribuição de minério.

4. **Client** (Cliente):

&emsp;&emsp;Representa os clientes que recebem o minério de ferro da Vale. Cada Cliente tem uma demanda específica de minério, que deve ser atendida de forma eficiente pela distribuição.

5. **Supplier** (Fornecedor):

&emsp;&emsp;Representa as minas de onde o minério é extraído. Cada Fornecedor tem uma capacidade de produção que influencia diretamente na quantidade de minério disponível para distribuição.

6. **ProcessingPlant** (Usina de Beneficiamento):

&emsp;&emsp;Esta classe representa as usinas de beneficiamento onde o minério de ferro é processado antes de ser distribuído aos clientes. Cada Usina de Beneficiamento possui capacidades produtivas que devem ser consideradas na distribuição do minério.

7. **Warehouse** (Armazém):

&emsp;&emsp;Representa locais de armazenamento intermediário onde o minério pode ser armazenado durante o processo de distribuição. Os Armazéns são importantes para otimizar a logística e garantir o abastecimento contínuo dos clientes.

8. **Port** (Porto):

&emsp;&emsp;Representa os portos onde ocorre o embarque e desembarque do minério de ferro. Os Portos são pontos-chave na distribuição internacional do minério e devem ser considerados na modelagem da rede logística.

9. **ProductQuantity** (Quantidade do Produto):

&emsp;&emsp;Esta classe indica a quantidade de minério de ferro associada a um Cliente, Fornecedor ou Usina de Beneficiamento. A quantidade de minério disponível influencia diretamente na capacidade de atendimento das demandas dos clientes.

10. **Product** (Produto):

&emsp;&emsp;Representa o minério que está sendo transportado e distribuído pela Vale. Cada tipo de minério pode ter diferentes características físico-químicas que devem ser consideradas na distribuição.

11. **Storage** (Armazenamento):

&emsp;&emsp;A classe Storage representa os locais de armazenamento disponíveis ao longo da cadeia logística da Vale, incluindo usinas de beneficiamento, armazéns intermediários e portos. Esses locais são essenciais para garantir a eficiência na distribuição do minério de ferro, permitindo o armazenamento temporário do produto durante o processo de transporte.

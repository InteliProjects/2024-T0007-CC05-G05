namespace Estaiada.Models // Namespace para o modelo específico relacionado ao algoritmo Ford-Fulkerson.
{
    /// <summary>
    /// Representa uma requisição para execução do algoritmo Ford-Fulkerson.
    /// </summary>
    public class FordFulkersonRequest
    {
        /// <summary>
        /// Obtém ou define o caminho do arquivo CSV contendo os nós do grafo.
        /// </summary>
        public string NodesCsvFile { get; set; }

        /// <summary>
        /// Obtém ou define o caminho do arquivo CSV contendo as arestas do grafo.
        /// </summary>
        public string EdgesCsvFile { get; set; }
    }
}

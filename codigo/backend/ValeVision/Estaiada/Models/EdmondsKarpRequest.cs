namespace Estaiada.Models // Namespace para o modelo específico relacionado ao algoritmo Edmonds-Karp.
{
    /// <summary>
    /// Representa uma requisição para execução do algoritmo Edmonds-Karp.
    /// </summary>
    public class EdmondsKarpRequest
    {
        /// <summary>
        /// Obtém ou define o caminho do arquivo CSV contendo os nós do grafo.
        /// </summary>
        public string NodesCsvFile { get; set; }

        /// <summary>
        /// Obtém ou define o caminho do arquivo CSV contendo as arestas do grafo.
        /// </summary>
        public string EdgesCsvFile { get; set; }

        /// <summary>
        /// Obtém ou define os dados dos nós do grafo em formato específico.
        /// </summary>
        public string? NodesData { get; internal set; }

        /// <summary>
        /// Obtém ou define os dados das arestas do grafo em formato específico.
        /// </summary>
        public string? EdgesData { get; internal set; }
    }
}

namespace Estaiada.Models // Namespace para o modelo espec�fico relacionado ao algoritmo Edmonds-Karp.
{
    /// <summary>
    /// Representa uma requisi��o para execu��o do algoritmo Edmonds-Karp.
    /// </summary>
    public class EdmondsKarpRequest
    {
        /// <summary>
        /// Obt�m ou define o caminho do arquivo CSV contendo os n�s do grafo.
        /// </summary>
        public string NodesCsvFile { get; set; }

        /// <summary>
        /// Obt�m ou define o caminho do arquivo CSV contendo as arestas do grafo.
        /// </summary>
        public string EdgesCsvFile { get; set; }

        /// <summary>
        /// Obt�m ou define os dados dos n�s do grafo em formato espec�fico.
        /// </summary>
        public string? NodesData { get; internal set; }

        /// <summary>
        /// Obt�m ou define os dados das arestas do grafo em formato espec�fico.
        /// </summary>
        public string? EdgesData { get; internal set; }
    }
}

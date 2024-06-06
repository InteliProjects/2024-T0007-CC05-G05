using Microsoft.AspNetCore.Mvc; // Namespace para o uso de classes relacionadas ao ASP.NET Core MVC.
using System; // Namespace para o uso de tipos básicos e primitivos do C#.
using System.Diagnostics; // Namespace para o uso de diagnósticos e controle de processos do sistema.
using System.IO; // Namespace para o uso de operações de entrada e saída de arquivos.
using System.Text; // Namespace para o uso de tipos relacionados à codificação de caracteres.
using System.Threading.Tasks; // Namespace para o uso de tarefas assíncronas.
using Estaiada.Models; // Namespace para o modelo específico relacionado ao algoritmo Ford-Fulkerson.

namespace Estaiada.Controllers // Namespace para os controladores do aplicativo.
{
    /// <summary>
    /// Controlador responsável por lidar com as requisições relacionadas ao algoritmo Ford-Fulkerson.
    /// </summary>
    public class FordFulkersonController : Controller
    {
        /// <summary>
        /// Calcula o fluxo máximo usando o algoritmo Ford-Fulkerson.
        /// </summary>
        /// <param name="request">Requisição contendo os dados necessários para a execução do algoritmo.</param>
        /// <returns>Uma ActionResult representando o resultado da execução.</returns>
        [Route("api/FordFulkerson")] // Rota da API para o método de cálculo do fluxo máximo.
        [HttpPost] // Atributo HTTP POST para indicar que este método responde a solicitações POST.
        public async Task<IActionResult> CalculateMaxFlow([FromBody] FordFulkersonRequest request)
        {
            try
            {

                // Salva os dados dos nós em um arquivo temporário.
                string nodesFilePath = Path.GetTempFileName();
                await System.IO.File.WriteAllTextAsync(nodesFilePath, request.NodesCsvFile, Encoding.UTF8);

                // Salva os dados das arestas em um arquivo temporário.
                string edgesFilePath = Path.GetTempFileName();
                await System.IO.File.WriteAllTextAsync(edgesFilePath, request.EdgesCsvFile, Encoding.UTF8);

                // Caminho completo para o arquivo que contém a implementação do algoritmo Ford-Fulkerson em Java.
                string fullPathToJavaAlgorithm = "../../../../modeling/src/main/java/com/inteli/algorithms/FordFulkerson.java";

                // Comando para executar o algoritmo Ford-Fulkerson em Java, passando os arquivos de nós e arestas como argumentos.
                string command = $"java -jar \"{fullPathToJavaAlgorithm}\" \"{nodesFilePath}\" \"{edgesFilePath}\"";

                // Configurações para iniciar um processo para executar o comando Java.
                ProcessStartInfo processStartInfo = new ProcessStartInfo("cmd.exe", "/c " + command)
                {
                    RedirectStandardOutput = true, // Redireciona a saída padrão para que possa ser lida.
                    UseShellExecute = false, // Não usa o shell do sistema para executar o processo.
                    CreateNoWindow = true // Não cria uma janela para o processo.
                };

                // Inicia o processo para executar o comando Java.
                Process process = new Process { StartInfo = processStartInfo };
                process.Start();

                // Lê a saída do processo.
                string result = await process.StandardOutput.ReadToEndAsync();

                // Aguarda até que o processo termine.
                process.WaitForExit();

                // Remove os arquivos temporários.
                System.IO.File.Delete(nodesFilePath);
                System.IO.File.Delete(edgesFilePath);

                // Retorna o resultado como uma resposta OK.
                return Ok(result);
            }
            catch (Exception ex)
            {
                // Retorna uma resposta de erro interno do servidor em caso de exceção.
                return StatusCode(500, ex.Message);
            }
        }
    }
}

using Microsoft.AspNetCore.Mvc;
using System;
using System.Diagnostics;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using Estaiada.Models;

namespace Estaiada.Controllers
{
    public class EdmondsKarpController : Controller
    {
        [Route("api/EdmondsKarp")]
        [HttpPost]
        public async Task<IActionResult> CalculateMaxFlow([FromBody] EdmondsKarpRequest request)
        {
            try
            {
                string nodesFilePath = Path.GetTempFileName();
                await System.IO.File.WriteAllTextAsync(nodesFilePath, request.NodesCsvFile, Encoding.UTF8);

                string edgesFilePath = Path.GetTempFileName();
                await System.IO.File.WriteAllTextAsync(edgesFilePath, request.EdgesCsvFile, Encoding.UTF8);
                
                string fullPathToJavaAlgorithm = "../../../../modeling/src/main/java/com/inteli/algorithms/EdmondsKarp.java";

                string command = $"java -jar \"{fullPathToJavaAlgorithm}\" \"{nodesFilePath}\" \"{edgesFilePath}\"";

                ProcessStartInfo processStartInfo = new ProcessStartInfo("cmd.exe", "/c " + command)
                {
                    RedirectStandardOutput = true,
                    UseShellExecute = false,
                    CreateNoWindow = true
                };

                Process process = new Process { StartInfo = processStartInfo };
                process.Start();

                string result = await process.StandardOutput.ReadToEndAsync();

                process.WaitForExit();

                System.IO.File.Delete(nodesFilePath);
                System.IO.File.Delete(edgesFilePath);

                return Ok(result);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }
    }
}

using Microsoft.AspNetCore.Mvc;
using System;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;

namespace CsvEndpoint.Controllers
{
    [Route("api/Upload")]
    [ApiController]
    public class CsvController : ControllerBase
    {
        private readonly string _projectDirectory = Path.Combine(AppContext.BaseDirectory, "CsvFiles");

        [HttpPost("upload")]
        public async Task<IActionResult> UploadCsvFiles(IFormFile nodes, IFormFile edges)
        {
            try
            {
                if (nodes == null || nodes.Length == 0 || edges == null || edges.Length == 0)
                {
                    return BadRequest("No file uploaded or file is empty.");
                }

                // Process the uploaded files
                await SaveCsvFile(nodes, "nodes.csv");
                await SaveCsvFile(edges, "edges.csv");

                return Ok("CSV files uploaded successfully.");
            }
            catch (Exception ex)
            {
                return StatusCode(500, $"Internal server error: {ex.Message}");
            }
        }

        private async Task SaveCsvFile(IFormFile file, string fileName)
        {
            try
            {
                if (file != null && file.Length > 0)
                {
                    // Check if the file has a CSV extension
                    if (Path.GetExtension(file.FileName).ToLower() != ".csv")
                    {
                        throw new Exception("Uploaded file is not a CSV file.");
                    }

                    string filePath = Path.Combine(_projectDirectory, fileName);
                    using (var stream = new FileStream(filePath, FileMode.Create))
                    {
                        await file.CopyToAsync(stream);
                    }
                }
            }
            catch (Exception ex)
            {
                throw new Exception($"Error saving CSV file '{fileName}': {ex.Message}");
            }
        }
    }
}

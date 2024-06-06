import React from 'react';
import './App.css';
import logo from './img/logo-vale-vision.png'
import logoInteli from './img/logo-inteli.png'
import Graph from 'react-graph-vis';
import Tabela from './data/Tabela';
import graphData from './data/graphDataTest';
import optionsGraph from './data/options';
import axios from 'axios';
import { useState, useEffect } from 'react';
import Popup from 'reactjs-popup';

function App() {

  // State to store information about the selected node
  const [nodeInfo, setNodeInfo] = useState(null);

  // State that stores which algorithm is selected
  const [selectedAlgorithm, setSelectedAlgorithm] = useState('ford-fulkerson');

  // State that stores selected nodes file
  const [selectedNodesFile, setSelectedNodesFile] = useState(null);

  // State that stores selected edges file
  const [selectedEdgesFile, setSelectedEdgesFile] = useState(null);

  // State to store the graph, initialized with graphData, JSON resulting from the algorithm 
  const [graph, setGraph] = useState({ nodes: [], edges: [] });

  // State to store table data, initialized with graphData, JSON resulting from the algorithm 
  const [tableData, setTableData] = useState([]);

  // State to store selected filters
  const [selectedFilters, setSelectedFilters] = useState({
    suppliers: true,
    plants: true,
    warehouses: true,
    customers: true,
    ports: true,
    source: true,
    sink: true,
  });

  // State to store if the algorithm was executed
  const [algorithmExecuted, setAlgorithmExecuted] = useState(false);

  // Function to handle file selection
  const handleFileSelect = (event) => {
    const file = event.target.files[0];
    const inputId = event.target.id;

    // Determine which file input was triggered
    if (inputId === 'fileInput1') {
      setSelectedNodesFile(file);
    } else if (inputId === 'fileInput2') {
      setSelectedEdgesFile(file);
    }
  };

  useEffect(() => {
    if (selectedNodesFile && selectedEdgesFile) {
      // Send the files to the server
      const formData = new FormData();
      formData.append('nodes', selectedNodesFile);
      formData.append('edges', selectedEdgesFile);

      axios
        .post('http://localhost:5297/api/Upload/upload', formData)
        .then((response) => {
          console.log(response.data);
          // Clear the selected files after sending
          setSelectedNodesFile(null);
          setSelectedEdgesFile(null);
          setAlgorithmExecuted(false);
        })
        .catch((error) => {
          console.error('Erro ao enviar arquivos:', error);
          // Clear the selected files in case of error
          setSelectedNodesFile(null);
          setSelectedEdgesFile(null);
          setAlgorithmExecuted(false);
        });
    }
  }, [selectedNodesFile, selectedEdgesFile]);

  const handleAlgorithmChange = (event) => {
    setSelectedAlgorithm(event.target.value);
    setAlgorithmExecuted(false);
  };

  const executarAlgoritmo = () => {
    if (selectedAlgorithm === 'ford-fulkerson') {
      setAlgorithmExecuted(true);
      // Run the algorithm Ford-Fulkerson
      axios.post('http://localhost:5297/api/FordFulkerson').then(response => {
        setGraph(graphData);
      }).catch(error => {
        console.error('Erro ao executar Ford-Fulkerson:', error);
      });
      console.log('Executando algoritmo Ford-Fulkerson...');
    } else if (selectedAlgorithm === 'edmonds-karp') {
      setAlgorithmExecuted(true);
      // Run the algorithm Edmonds-Karp
      axios.post('http://localhost:5297/api/EdmondsKarp').then(response => {
        setGraph(graphData);
      }).catch(error => {
        console.error('Erro ao executar Edmonds-Karp:', error);
      });
      console.log('Executando algoritmo Edmonds-Karp...');
    } else {
      console.log('Nenhum algoritmo selecionado.');
    }
  }  
    
  // Function to handle click on a node in the graph
  const handleNodeClick = (event) => {
    const { nodes } = event;
    if (nodes.length === 1) {
      const nodeId = nodes[0];
      const node = graphData.nodes.find(node => node.id === nodeId);
      if (node) {
        let nodeInfoToShow = { id: node.id, label: node.label };
    
        setNodeInfo(nodeInfoToShow);
      }
    } else {
      setNodeInfo(null);
    }
  }
  

  // Effect to apply filters and update table data whenever filters are changed
  useEffect(() => {
    // Function to apply filters to the graph
    const applyFilters = () => {
        const filteredEdges = graphData.edges.filter(edge => {
            return (
              (selectedFilters.source || edge.from_type !== 'S') &&
              (selectedFilters.suppliers || edge.from_type !== 'FO') &&
              (selectedFilters.warehouses || edge.from_type !== 'PA') &&
              (selectedFilters.customers || edge.from_type !== 'CL') &&
              (selectedFilters.ports || edge.from_type !== 'PO') &&
              (selectedFilters.sink || edge.from_type !== 'T') &&
              (selectedFilters.source || edge.to_type !== 'S') &&
              (selectedFilters.suppliers || edge.to_type !== 'FO') &&
              (selectedFilters.warehouses || edge.to_type !== 'PA') &&
              (selectedFilters.customers || edge.to_type !== 'CL') &&
              (selectedFilters.ports || edge.to_type !== 'PO') &&
              (selectedFilters.sink || edge.to_type !== 'T')
            );
        });

        const filteredNodes = graphData.nodes.filter(node => {
            return (
                (selectedFilters.source || node.type !== 'S') &&
                (selectedFilters.suppliers || node.type !== 'FO') &&
                (selectedFilters.warehouses || node.type !== 'PA') &&
                (selectedFilters.customers || node.type !== 'CL') &&
                (selectedFilters.ports || node.type !== 'PO') &&
                (selectedFilters.sink || node.type !== 'T')
            );
        });

        const filteredGraph = { nodes: [...filteredNodes], edges: [...filteredEdges] };
        setGraph({ ...filteredGraph });
    };

    // Function to update table data based on selected filters
    const updateTableData = () => {
        const filteredEdges = graphData.edges.filter(edge => {
            const fromFilter = (
                (selectedFilters.source || edge.from_type !== 'S') &&
                (selectedFilters.suppliers || edge.from_type !== 'FO') &&
                (selectedFilters.warehouses || edge.from_type !== 'PA') &&
                (selectedFilters.customers || edge.from_type !== 'CL') &&
                (selectedFilters.ports || edge.from_type !== 'PO') &&
                (selectedFilters.sink || edge.from_type !== 'T')
            );

            const toFilter = (
                (selectedFilters.source || edge.to_type !== 'S') &&
                (selectedFilters.suppliers || edge.to_type !== 'FO') &&
                (selectedFilters.warehouses || edge.to_type !== 'PA') &&
                (selectedFilters.customers || edge.to_type !== 'CL') &&
                (selectedFilters.ports || edge.to_type !== 'PO') &&
                (selectedFilters.sink || edge.to_type !== 'T')
            );

            return fromFilter && toFilter;
        });
        setTableData(filteredEdges);
    };

    applyFilters(); // Apply filters to the graph
    updateTableData(); // Update table data
}, [selectedFilters]);


  // Function to handle filter change
  const handleFilterChange = filter => {
    const updatedFilters = { ...selectedFilters, [filter]: !selectedFilters[filter] };
    setSelectedFilters(updatedFilters);
  };

  return (
    <div>
      <header style={{display: 'flex', justifyContent: 'space-between',alignContent: 'center', height: '80px', borderRadius: '4vh', padding: '10px'}}>
        <img src={logo} alt="logo" style={{width: '75px', height: 'auto'}} />
          <h1 style={{marginTop: '10px', textAlign: 'center', flex: '1'}}>Vale Vision</h1>
        <img src={logoInteli} alt="logoInteli" style={{width: '100px', height: 'auto', marginLeft: 'auto', marginRight: '5px'}} />
      </header>

      <div className="container">
        <div className="principal" style={{ position: 'relative' }}>
          <h2 style={{marginTop: '5px', marginLeft: '10px'}}>Grafo resultante</h2>
          {algorithmExecuted && <Graph graph={{...graph}} options={optionsGraph} style={{ width: '100%', height: '90%' }} events={{ click: handleNodeClick }}/>}
          {nodeInfo && (
            <div style={{ position: 'absolute', top: 10, right: 10, background: 'white', padding: '10px', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0,0,0,0.1)' }}>
              <p>Id: {nodeInfo.id}</p>
              <p>Nó: {nodeInfo.label}</p>
            </div>
          )}
        </div>
        <div className="tabela">
          <h2>Tabela</h2>
          {algorithmExecuted && <Tabela edges={tableData} nodes={graphData.nodes}/>}
        </div>
        <div className="dados" style={{ display: 'flex', flexDirection: 'column', justifyContent: 'flex-start', alignItems: 'center' }}>
          <h3 style={{ marginBottom: '10px' }}>Insira os dados</h3>
            <div style={{ marginBottom: '10px' }}>
              <label htmlFor="fileInput1">Nós:</label>
              <input type="file" id="fileInput1" accept="text/csv" onChange={handleFileSelect} />
            </div>
            <div>
              <label htmlFor="fileInput2">Arestas:</label>
              <input type="file" id="fileInput2" accept="text/csv" onChange={handleFileSelect} />
            </div>
        </div>
        <div className="algoritmos" style={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center' }}>
          <h3>Escolha o algoritmo</h3>
          <Popup trigger={<button className="popupBtn">ⓘ</button>} modal nested>
            {close => (
              <div className="popup">
              <div className="popupContent">
                <h4>Ford-Fulkerson</h4>
                  <p>
                  O algoritmo é uma técnica para encontrar o fluxo máximo em uma rede de fluxo, que é um grafo direcionado onde cada aresta tem uma capacidade máxima e um fluxo. O problema do fluxo máximo envolve determinar a quantidade máxima de fluxo que pode ser enviado de uma origem para um destino em uma rede.
                  </p>
                <hr />
                <h4>Edmonds-Karp</h4>
                  <p>
                  O algoritmo é uma variante do algoritmo Ford-Fulkerson para encontrar o fluxo máximo em uma rede de fluxo. Assim como o Ford-Fulkerson, ele lida com grafos direcionados onde cada aresta possui uma capacidade máxima e um fluxo.
                  </p>
              </div>
              <div className="areaClosePopup">
                <button className="closePopup" onClick={() => {console.log("Popup fechado "); close();}}>
                  Fechar
                </button>
              </div>
              </div>
            )}
          </Popup>
        <select value={selectedAlgorithm} onChange={handleAlgorithmChange}>
          <option value="ford-fulkerson">Ford-Fulkerson</option>
          <option value="edmonds-karp">Edmonds-Karp</option>
        </select>
        <button onClick={executarAlgoritmo} className="btnAlg">Executar algoritmo</button>
        </div>
        <div className="filtros">
          <h2>Filtros</h2>
          <input id="f1" type="checkbox" checked={selectedFilters.source} onChange={() => handleFilterChange('source')} />
          <label htmlFor="f1" className="btn">Fonte</label>
          <input id="f2" type="checkbox" checked={selectedFilters.suppliers} onChange={() => handleFilterChange('suppliers')} />
          <label htmlFor="f2" className="btn">Fornecedores</label>
          <input id="f3" type="checkbox" checked={selectedFilters.warehouses} onChange={() => handleFilterChange('warehouses')} />
          <label htmlFor="f3" className="btn">Entrepostos</label>
          <input id="f4" type="checkbox" checked={selectedFilters.customers} onChange={() => handleFilterChange('customers')} />
          <label htmlFor="f4" className="btn">Clientes</label>
          <input id="f5" type="checkbox" checked={selectedFilters.ports} onChange={() => handleFilterChange('ports')} />
          <label htmlFor="f5" className="btn">Portos</label>
          <input id="f6" type="checkbox" checked={selectedFilters.sink} onChange={() => handleFilterChange('sink')} />
          <label htmlFor="f6" className="btn">Sorvedouro</label>
        </div>
        <div className="filler">
        </div>
      </div>
    </div>
  );
}

export default App;
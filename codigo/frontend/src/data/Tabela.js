import React from 'react';

// Arquivo js que pega os dados do grafo em formato de Tabela

function Tabela({ edges, nodes }) {
  // Função auxiliar para encontrar o label do nó pelo id
  const findNodeLabelById = (id) => {
    const node = nodes.find((n) => n.id === id);
    return node ? node.label : '';
  };

  return (
    <div style={{ maxHeight: '400px', overflowY: 'auto', justifyContent: 'center', alignItems: 'center', flexDirection: 'column'}}>
      <table style={{ width: '90%', height: '100%' }}>
        <thead>
          <tr>
            <th>Nó origem</th>
            <th>Nó destinoㅤㅤ</th>
            <th>Fluxo</th>
          </tr>
        </thead>
        <tbody>
          {edges.map((edge, index) => (
            <tr key={index}>
              <td>{findNodeLabelById(edge.from)}</td>
              <td>{findNodeLabelById(edge.to)}</td>
              <td>{edge.label}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Tabela;

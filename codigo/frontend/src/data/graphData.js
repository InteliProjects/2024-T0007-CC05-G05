// Exemplo de JSON que será usado como base para a saída dos algoritmos 

const graphData = {
  nodes: [
    { id: 'Fonte', label: 'F', color: '#C0C0C0', fontColor: '#FFFFFF'},
    { id: 'Fornecedor 1', label: 'F1', color: '#40E0D0', fontColor: '#000000' },
    { id: 'Fornecedor 2', label: 'F2', color: '#40E0D0', fontColor: '#000000' },
    { id: 'Fornecedor 3', label: 'F3', color: '#40E0D0', fontColor: '#000000' },
    { id: 'Usina 1', label: 'U1', color: '#ADD8E6', fontColor: '#000000'},
    { id: 'Usina 2', label: 'U2', color: '#ADD8E6', fontColor: '#000000'},
    { id: 'Usina 3', label: 'U3', color: '#ADD8E6', fontColor: '#000000'},
    { id: 'Usina 4', label: 'U4', color: '#ADD8E6', fontColor: '#000000'},
    { id: 'Entreposto 1', label: 'E1', color: '#32CD32', fontColor: '#FFFFFF'},
    { id: 'Entreposto 2', label: 'E2', color: '#32CD32', fontColor: '#FFFFFF'},
    { id: 'Entreposto 3', label: 'E3', color: '#32CD32', fontColor: '#FFFFFF'},
    { id: 'Cliente 1', label: 'C1', color: '#FF69B4', fontColor: '#000000'},
    { id: 'Cliente 2', label: 'C2', color: '#FF69B4', fontColor: '#000000'},
    { id: 'Cliente 3', label: 'C3', color: '#FF69B4', fontColor: '#000000'},
    { id: 'Porto', label: 'P', color: '#EE82EE', fontColor: '#000000' },
    { id: 'Sorvedouro', label: 'S', color: '#FFFF00', fontColor: '#FFFFFF' }
  ],
  edges: [
    { from: 'Fonte', to: 'Fornecedor 1', label: '350/300'},
    { from: 'Fonte', to: 'Fornecedor 2', label: '350/350'},
    { from: 'Fonte', to: 'Fornecedor 3', label: '350/320'},
    { from: 'Fornecedor 1', to: 'Usina 1', label: '200/170'},
    { from: 'Fornecedor 1', to: 'Usina 2', label: '170/130'},
    { from: 'Fornecedor 2', to: 'Usina 2', label: '110/50'},
    { from: 'Fornecedor 2', to: 'Usina 3', label: '210/200'},
    { from: 'Fornecedor 2', to: 'Usina 4', label: '220/100'},
    { from: 'Fornecedor 3', to: 'Usina 4', label: '240/240'},
    { from: 'Fornecedor 3', to: 'Usina 2', label: '140/80'},
    { from: 'Usina 1', to: 'Entreposto 1', label: '210/170'},
    { from: 'Usina 2', to: 'Entreposto 2', label: '190/100'},
    { from: 'Usina 2', to: 'Entreposto 3', label: '230/160'},
    { from: 'Usina 3', to: 'Entreposto 2', label: '190/190'},
    { from: 'Usina 4', to: 'Entreposto 2', label: '180/140'},
    { from: 'Usina 4', to: 'Entreposto 3', label: '300/200'},
    { from: 'Entreposto 1', to: 'Cliente 1', label: '150/70'},
    { from: 'Entreposto 1', to: 'Porto', label: '100/100'},
    { from: 'Entreposto 2', to: 'Porto', label: '260/260'},
    { from: 'Entreposto 2', to: 'Cliente 2', label: '240/170'},
    { from: 'Entreposto 3', to: 'Cliente 2', label: '130/0'},
    { from: 'Entreposto 3', to: 'Cliente 3', label: '175/120'},
    { from: 'Entreposto 3', to: 'Porto', label: '300/240'},
    { from: 'Cliente 1', to: 'Sorvedouro', label: '70/70'},
    { from: 'Cliente 2', to: 'Sorvedouro', label: '180/170'},
    { from: 'Cliente 3', to: 'Sorvedouro', label: '195/120'},
    { from: 'Porto', to: 'Sorvedouro', label: '700/600'}
  ]
};

export default graphData;
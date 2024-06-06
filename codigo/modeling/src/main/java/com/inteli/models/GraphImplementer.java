package com.inteli.models;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Responsible for constructing and managing the graph representation of the network, including nodes and paths.
 * This class reads data from CSV files to build nodes and paths, sets up the adjacency matrix for graph operations,
 * and provides methods to access and manipulate the graph data.
 */
public class GraphImplementer {
    private  ArrayList<Node> nodes;
    private  ArrayList<Path> paths;
    private List<List<Path>> adjacencyMatrix;
    private  Map<Integer, Integer> identifierMap = new HashMap<>();
    private  Map<Integer, Node> nodeMap = new HashMap<>();

    /**
     * Constructs a GraphImplementer instance by reading node and path data from specified CSV files.
     * It initializes the graph's nodes and paths based on the input files and constructs an adjacency matrix.
     *
     * @param dataDirPath The directory path where node and edge data CSV files are stored.
     */
    public GraphImplementer(String dataDirPath) {
        String nodesPath = dataDirPath + "/nodes.csv";
        List<String[]> nodesArray = createAttributesArray(nodesPath);
        nodes = nodeImplementer(nodesArray);

        String edgesTempCsvPath = createTempEdgesCsv(dataDirPath);
        List<String[]> edgesArray = createAttributesArray(edgesTempCsvPath);

        File edgesTempCsv = new File(edgesTempCsvPath);
        edgesTempCsv.delete();

        paths = edgeImplementer(edgesArray);
        adjacencyMatrix = createAdjacencyMatrix(nodes, paths);
    }

    /**
     * Creates a temporary CSV file to add connections to the source and sink nodes and returns its path.
     * This method is used internally to modify the edge data temporarily for graph construction.
     *
     * @param dataDirPath The directory path where the original edges CSV file is stored.
     * @return The path to the temporary edges CSV file.
     */
    private String createTempEdgesCsv(String dataDirPath) {
        // Adds the source and sink paths to Edges.csv
        String edgesCsvPath = dataDirPath + "/edges.csv";
        String edgesCsvPathTemp = dataDirPath + "/tempEdges.csv";
        File edgesTempCsv = new File(edgesCsvPathTemp);

        try (BufferedReader reader = new BufferedReader(new FileReader(edgesCsvPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(edgesTempCsv))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                writer.write(linha);
                writer.newLine();
            }
            for (Node node : nodes) {
                // Modify the edges.csv file temporarily to create Client/Port - Sink connections
                if (node.getNodeType() == NodeType.CL || node.getNodeType() == NodeType.PO) {
                    writer.write(
                            "DIV,F," + Double.POSITIVE_INFINITY + ", Client - Sink," + node.getIdentifier() + ",1");
                    writer.newLine();
                } else if (node.getNodeType() == NodeType.FO) {
                    writer.write(
                            "DIV,F," + Double.POSITIVE_INFINITY + ", Source - Supplier," + "0," + node.getIdentifier());
                    writer.newLine();
                }
            }
            writer.close();
            return edgesCsvPathTemp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates the adjacency matrix for the graph, where each element is a Path object from the class Path.
     * The adjacency matrix is used to represent the graph in a way that facilitates algorithm implementation.
     *
     * @param nodes The list of nodes in the graph.
     * @param paths The list of paths (edges) in the graph.
     * @return A list of lists representing the adjacency matrix of the graph.
     */
    private List<List<Path>> createAdjacencyMatrix(ArrayList<Node> nodes, ArrayList<Path> paths) {
        List<List<Path>> adjMatrix = new ArrayList<>();
        for (int i = 0; i < nodes.size() + 2; i++) {
            adjMatrix.add(new ArrayList<>());
        }

        // Fill each inner ArrayList with nulls
        for (List<Path> innerList : adjMatrix) {
            for (int j = 0; j < nodes.size() + 2; j++) {
                innerList.add(null);
            }
        }
        for (Path path : paths) {
            int originNode = path.getOriginNode().getIdentifier();
            int destinationNode = path.getDestinationNode().getIdentifier();
            adjMatrix.get(identifierMap.get(originNode)).add(identifierMap.get(destinationNode), path);
        }
        return adjMatrix;
    }

    /**
     * Reads CSV data from a given path and creates an array of String arrays,
     * where each String array represents attributes for constructing Node and Path objects.
     *
     * @param csvPath The path to the CSV file to be read.
     * @return A list of String arrays, each representing a line from the CSV file.
     */
    private List<String[]> createAttributesArray(String csvPath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvPath))) {
            ArrayList<String[]> stringFile = (ArrayList<String[]>) csvReader.readAll();
            stringFile.remove(0);
            return stringFile;
        } catch (IOException e) {
            System.out.println("File not found.");
        }
        return null;
    }

    /**
     * Factory method for creating Node objects with or without storage, based on their NodeType.
     * Nodes designated to have storage (e.g., warehouses) will be created with a Storage object.
     * Other types of nodes will be created without storage.
     *
     * @param maximumStorageCapacity The maximum storage capacity for the node. This is used only if the node type supports storage.
     * @param id The unique identifier for the node.
     * @param description A descriptive text for the node.
     * @param nodeType The type of the node, determining whether it should have storage capabilities.
     * @return A Node object, with or without storage, depending on its NodeType.
     */
    private Node nodeFactory(double maximumStorageCapacity, int id, String description, NodeType nodeType) {
        Storage storage = new Storage(maximumStorageCapacity, 0);
        Set<NodeType> storageNodes = new HashSet<>();
        storageNodes.add(NodeType.PO);
        storageNodes.add(NodeType.PA);
        storageNodes.add(NodeType.PT);
        storageNodes.add(NodeType.UB);
        storageNodes.add(NodeType.BR);
        storageNodes.add(NodeType.UP);

        if (storageNodes.contains(nodeType)) {
            return new Node(id, description, storage, nodeType);
        }
        return new Node(id, description, nodeType);
    }

    /**
     * Implements nodes based on the data from the CSV file.
     * It utilizes the data to create Node instances, including setting up their storage capabilities if applicable.
     *
     * @param csvData A list of String arrays, where each String array contains the data for one node.
     * @return An ArrayList of Node objects created based on the CSV data.
     */
    private ArrayList<Node> nodeImplementer(List<String[]> csvData) {
        ArrayList<Node> nodes = new ArrayList<Node>();

        Node source = new Node(0, "Source", NodeType.S);
        nodes.add(source);
        identifierMap.put(0, 0);
        nodeMap.put(0, source);

        Node sink = new Node(1, "Sink", NodeType.T);
        nodes.add(sink);
        identifierMap.put(1, 1);
        nodeMap.put(1, sink);

        int sequentialOrderId = 2;
        for (String[] line : csvData) {
            int realId = Integer.parseInt(line[0]);
            sequentialOrderId++;
            identifierMap.put(realId, sequentialOrderId);
            NodeType nodeType = NodeType.fromString(line[1]);
            double maximumStorageCapacity = Double.parseDouble(line[2]);
            //
            String description = line[3];
            boolean hasStorage = Boolean.parseBoolean(line[4]);
            Node currentNode = nodeFactory(maximumStorageCapacity, realId, description, nodeType);
            nodes.add(currentNode);
            nodeMap.put(realId, currentNode);
        }
        return nodes;
    }





    /**
     * Implements edges (paths) of the graph based on information inside the edges CSV file.
     * This method creates Path objects for each edge defined in the CSV data.
     *
     * @param csvData A list of String arrays, each representing the data for one path.
     * @return An ArrayList of Path objects created based on the CSV data.
     */
    private ArrayList<Path> edgeImplementer(List<String[]> csvData) {

        ArrayList<Path> paths = new ArrayList<Path>();

        // iterating through the csv file and creating the edges
        for (String[] line : csvData) {
            String subTransportationModeCode = line[0];
            String transportationModeTypeCode = line[1];
            Double capacity = Double.parseDouble(line[2]);
            String description = line[3];
            int originId = Integer.parseInt(line[4]);
            int destinationId = Integer.parseInt(line[5]);

            // objects instantiation
            Node originNode = nodeMap.get(originId);
            Node destinationNode = nodeMap.get(destinationId);
            Path path = new Path(originNode, destinationNode, description, 0.0, capacity, transportationModeTypeCode, subTransportationModeCode);
            paths.add(path);

        }
        return paths;
    }

    /**
     * Gets the list of nodes in the graph.
     *
     * @return An ArrayList of Node objects in the graph.
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Gets the list of paths (edges) in the graph.
     *
     * @return An ArrayList of Path objects in the graph.
     */
    public ArrayList<Path> getPaths() {
        return paths;
    }

    /**
     * Gets the adjacency matrix of the graph.
     *
     * @return A list of lists representing the adjacency matrix, where each element is a Path object.
     */
    public List<List<Path>> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * Gets the dictionary mapping real node identifiers to their corresponding indices in the adjacency matrix.
     *
     * @return A Map where keys are real node identifiers and values are their indices in the adjacency matrix.
     */
    public Map<Integer, Integer> getIdentifierDictionary() {
        return identifierMap;
    }

    /**
     * Prints the adjacency matrix to the standard output. Each row represents a node, and each column represents
     * a potential connection to another node. This method is useful for debugging.
     */
    public void printAdjacencyMatrix() {
        for (List<Path> row : adjacencyMatrix) {
            for (Path path : row) {
                if (path == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print(path.getCapacity());
                }
            }
            System.out.println();
        }
    }
}

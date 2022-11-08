package Oving7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DijkstraMinHeap {
    static class Edge {
        int source;
        int dest;
        int weight;

        public Edge(int source, int dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class HeapNode{
        int vertex;
        int dist;
    }
    static class Graph {
        int vertices;
        LinkedList<Edge>[] adjacencylist;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new LinkedList[vertices];
            for (int i = 0; i <vertices ; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencylist[source].addFirst(edge);
        }

        public void dijkstra_GetMinDistances(int sourceVertex, int[] prev){
            int inf = Integer.MAX_VALUE;
            boolean[] SPT = new boolean[vertices];

            HeapNode[] heapNodes = new HeapNode[vertices];
            for (int i = 0; i < vertices ; i++) {
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].dist = inf;
                prev[i] = -1;
            }
            heapNodes[sourceVertex].dist = 0;

            MinHeap minHeap = new MinHeap(vertices);
            for (int i = 0; i < vertices ; i++) {
                minHeap.insert(heapNodes[i]);
            }

            while(!minHeap.isEmpty()){
                HeapNode extractedNode = minHeap.extractMinimum();

                int extractedVertex = extractedNode.vertex;
                SPT[extractedVertex] = true;

                LinkedList<Edge> listOfAdjacentVertices = adjacencylist[extractedVertex];
                for (Edge edge : listOfAdjacentVertices) {
                    int dest = edge.dest;
                    if (!SPT[dest]) {
                        int newKey = heapNodes[extractedVertex].dist + edge.weight;
                        int currentKey = heapNodes[dest].dist;
                        if (currentKey > newKey) {

                            decreaseKey(minHeap, newKey, dest);
                            heapNodes[dest].dist = newKey;
                            prev[dest] = extractedVertex;
                        }
                    }
                }
            }
            printDijkstra(heapNodes, prev);
        }

        public void decreaseKey(MinHeap minHeap, int newKey, int vertex){
            int index = minHeap.indexes[vertex];

            HeapNode node = minHeap.mH[index];
            node.dist = newKey;
            minHeap.bubbleUp(index);
        }

        public void printDijkstra(HeapNode[] resultSet, int[] previous){
            System.out.println("Weighted graphs using Dijkstra's Algorithm: Ø = none, S = start");
            for (int i = 0; i < vertices ; i++) {
                String x = String.valueOf(resultSet[i].dist);
                if(resultSet[i].dist == Integer.MAX_VALUE || resultSet[i].dist < 0){
                    x = "unreachable";
                }
                String predecessor = String.valueOf(previous[i]);
                if(previous[i] < 0 && resultSet[i].dist == 0) predecessor = "S";
                else if(previous[i] < 0){
                    predecessor = "Ø";
                }
                System.out.println("Node: " + i + " | predecessor " + predecessor +
                        " | distance: " + x);
            }
        }
    }

    public static void showMenu() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                Choose which graph you want to read:
                1. vg1
                2. vg2
                3. vg3
                4. vg4
                5. vg5
                6. vgSkandinavia
                7. Exit""");
        int choice = in.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.println("\n---- vg1 ---- ");
                final long timeStart = System.currentTimeMillis();

                BufferedReader br = new BufferedReader(new FileReader("vg1"));
                printResult(br);

                final long timeEnd = System.currentTimeMillis();
                long totalTime = timeEnd - timeStart;
                System.out.println("Runtime in ms: " + totalTime);

                showMenu();
            }
            case 2 -> {
                System.out.println("\n---- vg2 ---- ");
                final long timeStart = System.currentTimeMillis();

                BufferedReader br = new BufferedReader(new FileReader("vg2"));
                printResult(br);

                final long timeEnd = System.currentTimeMillis();
                long totalTime = timeEnd - timeStart;
                System.out.println("Runtime in ms: " + totalTime);

                showMenu();
            }
            case 3 -> {
                System.out.println("\n---- vg3 ---- ");
                final long timeStart = System.currentTimeMillis();

                BufferedReader br = new BufferedReader(new FileReader("vg3"));
                printResult(br);

                final long timeEnd = System.currentTimeMillis();
                long totalTime = timeEnd - timeStart;
                System.out.println("Runtime in ms: " + totalTime);

                showMenu();
            }
            case 4 -> {
                System.out.println("\n---- vg4 ---- ");
                final long timeStart = System.currentTimeMillis();

                BufferedReader br = new BufferedReader(new FileReader("vg4"));
                printResult(br);

                final long timeEnd = System.currentTimeMillis();
                long totalTime = timeEnd - timeStart;
                System.out.println("Runtime in ms: " + totalTime);

                showMenu();
            }
            case 5 -> {
                System.out.println("\n---- vg5 ---- ");
                final long timeStart = System.currentTimeMillis();

                BufferedReader br = new BufferedReader(new FileReader("vg5"));
                printResult(br);

                final long timeEnd = System.currentTimeMillis();
                long totalTime = timeEnd - timeStart;
                System.out.println("Runtime in ms: " + totalTime);

                showMenu();
            }
            case 6 -> {
                System.out.println("\n---- vgSkandinavia ---- ");
                final long timeStart = System.currentTimeMillis();

                BufferedReader br = new BufferedReader(new FileReader("vgSkandinavia"));
                printResult(br);

                final long timeEnd = System.currentTimeMillis();
                long totalTime = timeEnd - timeStart;
                System.out.println("Runtime in ms: " + totalTime);

                showMenu();
            }
            case 7 -> {
                in.close();
                System.out.println("Exiting...");
                System.exit(0);
            }
            default -> {
                System.out.println("Please make sure to choose a valid choice");
                showMenu();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) showMenu();
    }

    public static void printResult(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer((br.readLine()));
        int vertices = Integer.parseInt(st.nextToken());
        int[] previous = new int[vertices];
        Graph graph = new Graph(vertices);


        int K = Integer.parseInt((st.nextToken()));
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.addEdge(from, to, weight);
        }
        int source_vertex = 1;
        graph.dijkstra_GetMinDistances(source_vertex, previous);
    }


    static class MinHeap {
        int capacity;
        int currentSize;
        HeapNode[] mH;
        int[] indexes;


        public MinHeap(int capacity) {
            this.capacity = capacity;
            mH = new HeapNode[capacity + 1];
            indexes = new int[capacity];
            mH[0] = new HeapNode();
            mH[0].dist = Integer.MIN_VALUE;
            mH[0].vertex = -1;
            currentSize = 0;
        }

        public void insert(HeapNode x) {
            currentSize++;
            int index = currentSize;
            mH[index] = x;
            indexes[x.vertex] = index;
            bubbleUp(index);
        }

        public void bubbleUp(int position) {
            int parentIndex = position / 2;
            int currIndex = position;
            while (currIndex > 0 && mH[parentIndex].dist > mH[currIndex].dist) {
                HeapNode currNode = mH[currIndex];
                HeapNode parentNode = mH[parentIndex];

                indexes[currNode.vertex] = parentIndex;
                indexes[parentNode.vertex] = currIndex;
                swap(currIndex, parentIndex);
                currIndex = parentIndex;
                parentIndex = parentIndex / 2;
            }
        }

        public HeapNode extractMinimum() {
            HeapNode minimum = mH[1];
            HeapNode lastNode = mH[currentSize];

            indexes[lastNode.vertex] = 1;
            mH[1] = lastNode;
            mH[currentSize] = null;
            sinkDown(1);
            currentSize--;
            return minimum;
        }

        public void sinkDown(int k) {
            int smallest = k;
            int leftChildIndex = 2 * k;
            int rightChildIndex = 2 * k + 1;
            if (leftChildIndex < heapSize() && mH[smallest].dist > mH[leftChildIndex].dist) {
                smallest = leftChildIndex;
            }
            if (rightChildIndex < heapSize() && mH[smallest].dist > mH[rightChildIndex].dist) {
                smallest = rightChildIndex;
            }
            if (smallest != k) {

                HeapNode smallestNode = mH[smallest];
                HeapNode kNode = mH[k];


                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        public void swap(int a, int b) {
            HeapNode temporary = mH[a];
            mH[a] = mH[b];
            mH[b] = temporary;
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public int heapSize() {
            return currentSize;
        }
    }
}
package benchmark;

import opgave.DirectedEdge;
import opgave.Node;
import oplossing.MyPriorityQueueFactory;
import oplossing.MyShortestPath;
import oplossing.SkewHeapFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ShortestPathBenchmark {
    MyShortestPath shortestPathSkew = new MyShortestPath();
    MyShortestPath shortestPathLeftist = new MyShortestPath();

    public static void main(String[] args) {
        ShortestPathBenchmark benchmark = new ShortestPathBenchmark();
        try {
            benchmark.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void run() throws IOException {
        shortestPathSkew.setPriorityQueueFactory(new SkewHeapFactory());
        shortestPathLeftist.setPriorityQueueFactory(new MyPriorityQueueFactory());
        int size = 2000;
        int steps = 50;

        BufferedWriter skewHeapTime = new BufferedWriter(new FileWriter("benchmark/SkewHeapPathTime.csv"));
        BufferedWriter leftistHeapTime = new BufferedWriter(new FileWriter("benchmark/LeftistHeapPathTime.csv"));

        for (int i = 1; i <= size; i += size / steps) {
            benchmark(i, skewHeapTime, leftistHeapTime);
        }

        skewHeapTime.close();
        leftistHeapTime.close();

    }

    private void benchmark(int size, BufferedWriter skewHeapTime, BufferedWriter leftistHeapTime) {
        Random random = new Random();
        Node[][] graphNodes = new Node[size][size];
        List<DirectedEdge> edges = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graphNodes[i][j] = new Node(i, j);
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i > 0) {
                    edges.add(new DirectedEdge(graphNodes[i - 1][j], graphNodes[i][j], random.nextDouble()));
                }
                if (j > 0) {
                    edges.add(new DirectedEdge(graphNodes[i][j - 1], graphNodes[i][j], random.nextDouble()));
                }
            }
        }

        List<Node> nodes = Arrays.stream(graphNodes)
                .flatMap(Arrays::stream)
                .toList();

        shortestPathLeftist.setGraph(nodes, edges);
        shortestPathSkew.setGraph(nodes, edges);

        long skewstart = System.currentTimeMillis();
        shortestPathSkew.shortestPath(graphNodes[0][0], graphNodes[size - 1][size - 1]);
        long skewend = System.currentTimeMillis();

        long leftiststart = System.currentTimeMillis();
        shortestPathLeftist.shortestPath(graphNodes[0][0], graphNodes[size - 1][size - 1]);
        long leftistend = System.currentTimeMillis();

        try {
            skewHeapTime.append(String.valueOf(size)).append(";").append(String.valueOf(skewend - skewstart)).append("\n");
            leftistHeapTime.append(String.valueOf(size)).append(";").append(String.valueOf(leftistend - leftiststart)).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("SkewHeap:"+ size + ":" + (skewend - skewstart));
        System.out.println("LeftistHeap:"+ size + ":" + (leftistend - leftiststart));


    }
}

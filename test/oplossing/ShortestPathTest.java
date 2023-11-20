package oplossing;

import opgave.DirectedEdge;
import opgave.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShortestPathTest {
    SkewHeapFactory priorityQueueFactory = new SkewHeapFactory();
    @Test
    public void testShortestPathSimple() {
        MyShortestPath shortestPath = new MyShortestPath();
        shortestPath.setPriorityQueueFactory(priorityQueueFactory);
        List<Node> nodes = new ArrayList<>();
        List<DirectedEdge> edges = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
                Node node = new Node(i,1);
                nodes.add(node);
        }

        edges.add(new DirectedEdge(nodes.get(0), nodes.get(1), 2));
        edges.add(new DirectedEdge(nodes.get(0), nodes.get(2), 1));
        edges.add(new DirectedEdge(nodes.get(1), nodes.get(3), 1));
        edges.add(new DirectedEdge(nodes.get(2), nodes.get(3), 1));
        edges.add(new DirectedEdge(nodes.get(3), nodes.get(4), 1));

        shortestPath.setGraph(nodes, edges);
        //normal implementation priority queue only works with one class instead of 2


        Node from = nodes.get(0);
        Node to = nodes.get(4);
        List<DirectedEdge> path = shortestPath.shortestPath(from, to);

        // Print the shortest path
        if (path != null) {
            System.out.println("Shortest path from " + from + " to " + to + ":");
            for (DirectedEdge edge : path) {
                System.out.println(edge);
            }
        } else {
            System.out.println("No path found from " + from + " to " + to);
        }

        edges.remove(edges.get(0));
        assertTrue(path.containsAll(edges));
    }
}

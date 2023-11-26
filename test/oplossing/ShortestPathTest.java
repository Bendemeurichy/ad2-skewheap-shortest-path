package oplossing;

import opgave.DirectedEdge;
import opgave.Node;
import opgave.PriorityQueueFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathTest {
    PriorityQueueFactory priorityQueueFactory;
    ShortestPathTest (PriorityQueueFactory priorityQueueFactory){
        this.priorityQueueFactory=priorityQueueFactory;
    }
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
        edges.remove(edges.get(1));
        assertTrue(path.containsAll(edges));
    }

    @Test
    public void testShortestPathSimpleGraph() {
        MyShortestPath shortestPath = new MyShortestPath();
        shortestPath.setPriorityQueueFactory(priorityQueueFactory);
        Node node1 = new Node(1, 1);
        Node node2 = new Node(2, 2);
        Node node3 = new Node(3, 3);

        DirectedEdge edge1 = new DirectedEdge(node1, node2, 1);
        DirectedEdge edge2 = new DirectedEdge(node2, node3, 2);

        shortestPath.setGraph(Arrays.asList(node1, node2, node3), Arrays.asList(edge1, edge2));

        // Call shortestPath
        List<DirectedEdge> path = shortestPath.shortestPath(node1, node3);

        // Assert that the path is correct
        assertEquals(2, path.size());
        assertTrue(path.contains(edge1));
        assertTrue(path.contains(edge2));
    }

    @Test
    public void testShortestPathNoPath() {
        MyShortestPath myShortestPath = new MyShortestPath();
        myShortestPath.setPriorityQueueFactory(priorityQueueFactory);
        Node node1 = new Node(1, 1);
        Node node2 = new Node(2, 2);
        Node node3 = new Node(3, 3);

        DirectedEdge edge1 = new DirectedEdge(node1, node2, 1);

        myShortestPath.setGraph(Arrays.asList(node1, node2, node3), Arrays.asList(edge1));

        List<DirectedEdge> path = myShortestPath.shortestPath(node1, node3);

        assertNull(path);
    }

    @Test
    public void testShortestPathSameStartAndEnd() {
        MyShortestPath myShortestPath = new MyShortestPath();
        myShortestPath.setPriorityQueueFactory(priorityQueueFactory);
        Node node1 = new Node(1, 1);

        myShortestPath.setGraph(Arrays.asList(node1), Collections.emptyList());

        // Call shortestPath
        List<DirectedEdge> path = myShortestPath.shortestPath(node1, node1);

        // Assert that the path is empty
        assertNull(path);
    }

    @Test
    public void testShortestPathWithLargeGraph() {
        MyShortestPath shortestPath = new MyShortestPath();
        shortestPath.setPriorityQueueFactory(priorityQueueFactory);
        // Create nodes
        Node A = new Node(0, 0);
        Node B = new Node(1, 1);
        Node C = new Node(2, 2);
        Node D = new Node(3, 3);
        Node E = new Node(4, 4);
        Node F = new Node(5, 5);
        Node G = new Node(6, 6);
        Node H = new Node(7, 7);
        Node I = new Node(8, 8);

        // Create edges
        DirectedEdge edge1 = new DirectedEdge(A, B, 2.0);
        DirectedEdge edge2 = new DirectedEdge(B, C, 1.0);
        DirectedEdge edge3 = new DirectedEdge(A, C, 4.0);
        DirectedEdge edge4 = new DirectedEdge(B, D, 3.0);
        DirectedEdge edge5 = new DirectedEdge(D, E, 2.0);
        DirectedEdge edge6 = new DirectedEdge(C, E, 5.0);
        DirectedEdge edge7 = new DirectedEdge(E, F, 1.0);
        DirectedEdge edge8 = new DirectedEdge(F, G, 3.0);
        DirectedEdge edge9 = new DirectedEdge(G, H, 2.0);
        DirectedEdge edge10 = new DirectedEdge(H, I, 4.0);
        DirectedEdge edge11 = new DirectedEdge(I, E, 1.0);

        shortestPath.setGraph(Arrays.asList(A, B, C, D, E, F, G, H, I),
                Arrays.asList(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11));


        List<DirectedEdge> foundPath = shortestPath.shortestPath(A, I);
        List<DirectedEdge> expectedPath = Arrays.asList(edge1, edge4, edge5, edge7, edge8, edge9, edge10);
        assertEquals(expectedPath, foundPath);



        // Set up the graph with different weights
        DirectedEdge edge1Modified = new DirectedEdge(A, B, 4.0);
        DirectedEdge edge4Modified = new DirectedEdge(B, D, 1.0);
        DirectedEdge edge5Modified = new DirectedEdge(D, E, 3.0);
        DirectedEdge edge7Modified = new DirectedEdge(E, F, 2.0);

        List<DirectedEdge> existingPath = Arrays.asList(edge1Modified, edge4Modified, edge5Modified, edge7Modified, edge8, edge9, edge10);
        shortestPath.setGraph(Arrays.asList(A, B, C, D, E, F, G, H, I),
                Arrays.asList(edge1Modified, edge2, edge3, edge4Modified, edge5Modified, edge6, edge7Modified, edge8, edge9, edge10, edge11));

        List<DirectedEdge> newFoundPath = shortestPath.shortestPath(A, I);
        System.out.println(newFoundPath);
        assertEquals(existingPath, newFoundPath);
    }


}

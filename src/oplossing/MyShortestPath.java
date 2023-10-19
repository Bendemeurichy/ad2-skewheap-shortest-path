package oplossing;

import opgave.*;
import opgave.PriorityQueue;

import java.util.*;

public class MyShortestPath implements RoutePlanner {
    private PriorityQueueFactory queueFactory;
    private Map<Node, List<DirectedEdge>> neighbours = new HashMap<>();


    @Override
    public void setGraph(List<Node> nodes, List<DirectedEdge> edges) {
        for (Node node : nodes) {
            neighbours.put(node, new ArrayList<>());
        }
        for (DirectedEdge edge : edges) {
            neighbours.get(edge.from()).add(edge);
        }
    }

    @Override
    public void setPriorityQueueFactory(PriorityQueueFactory queueFactory) {
        this.queueFactory=queueFactory;
    }

    @Override
    public List<DirectedEdge> shortestPath(Node from, Node to) {
         PriorityQueue<Double,Node> queue= queueFactory.create();
         Map<Node,DirectedEdge> predecessor= new HashMap<>();
         Map<Node,Double> distance = new HashMap<>();
         Map<Node,QueueItem<Double,Node>> queueItemMap = new HashMap<>();
         Set<Node> processed = new HashSet<>();

         distance.put(from,0.0);
         queueItemMap.put(from,queue.add(0.0,from));

        Node current = from;
         while(current!=null && !current.equals(to)){

             if(!processed.contains(current)){
                 processed.add(current);
                 for(DirectedEdge edge : neighbours.get(current)){
                     Node neighbour = edge.to();
                     double newDistance = distance.get(current)+edge.weight();
                     if(newDistance<distance.getOrDefault(neighbour,Double.POSITIVE_INFINITY)){
                         if (distance.containsKey(neighbour)){
                             queueItemMap.get(neighbour).decreaseKey(newDistance);
                         }
                         else{
                             queueItemMap.put(neighbour,queue.add(newDistance,neighbour));
                         }
                         distance.put(neighbour,newDistance);
                         predecessor.put(neighbour,edge);

                     }
                 }
             }
             if(queue.isEmpty()){
                 current=null;
             } else {
                 current=queue.poll().getValue();
             }
         }

         if (predecessor.getOrDefault(to,null)==null){
            return null;
            }
         else{
                List<DirectedEdge> shortestRoute = new LinkedList<>();
                DirectedEdge prevEdge = predecessor.get(to);
                while(prevEdge!=null){
                    shortestRoute.add(0,prevEdge);
                    prevEdge=predecessor.get(prevEdge.from());
                }

                return shortestRoute;
         }
    }
}

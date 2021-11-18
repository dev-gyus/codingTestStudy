package remind.graph.advanced;

import java.util.*;

class DijkstraEdge implements Comparable<DijkstraEdge>{
    public Integer distance;
    public String node;

    public DijkstraEdge(Integer distance, String node) {
        this.distance = distance;
        this.node = node;
    }

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.node + ")";
    }

    @Override
    public int compareTo(DijkstraEdge dijkstraEdge){
        return this.distance - dijkstraEdge.distance;
    }
}

public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<DijkstraEdge>> graph, String startNode){
        HashMap<String, Integer> distance = new HashMap<>();
        // 초기값 세팅
        for (String node : graph.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
        }
        distance.put(startNode, 0);
        Queue<DijkstraEdge> needVisitList = new PriorityQueue<>(graph.get(startNode));
        while(needVisitList.size() > 0){
            DijkstraEdge edge = needVisitList.poll();
            Integer edgeDistance = edge.distance;
            String destinationNode = edge.node;
            if(distance.get(destinationNode) > edgeDistance) {
                distance.put(destinationNode, edgeDistance);
                ArrayList<DijkstraEdge> adjacentEdges = graph.get(destinationNode);
                for (DijkstraEdge adjacentNode : adjacentEdges) {
                    Integer totalDistance = edgeDistance + adjacentNode.distance;
                    if (distance.get(adjacentNode.node) > totalDistance){
                        needVisitList.add(new DijkstraEdge(totalDistance, adjacentNode.node));
                    }
                }
            }
        }
        return distance;

    }

    public static void main(String[] args){
        HashMap<String, ArrayList<DijkstraEdge>> graph = new HashMap<String, ArrayList<DijkstraEdge>>();
        graph.put("A", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(8, "B"), new DijkstraEdge(1, "C"), new DijkstraEdge(2, "D"))));
        graph.put("B", new ArrayList<DijkstraEdge>());
        graph.put("C", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(5, "B"), new DijkstraEdge(2, "D"))));
        graph.put("D", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(3, "E"), new DijkstraEdge(5, "F"))));
        graph.put("E", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(1, "F"))));
        graph.put("F", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(5, "A"))));

        DijkstraPath dObject = new DijkstraPath();
        System.out.println(dObject.dijkstraFunc(graph, "A"));
    }
}

package basic.remind.graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class DijkstraEdge implements Comparable<DijkstraEdge> {
    public Integer distance;
    public String node;

    public DijkstraEdge(Integer distance, String node) {
        this.distance = distance;
        this.node = node;
    }

    @Override
    public int compareTo(DijkstraEdge edge){
        return this.distance - edge.distance;
    }
}

public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<DijkstraEdge>> graph, String startNode){
        HashMap<String, Integer> distance = new HashMap<>();
        // 초기값 세팅
        for (String node : graph.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
        }
        // 시작 노드는 반드시 거리가 0이어야함 (어짜피 안들림)
        distance.put(startNode, 0);
        PriorityQueue<DijkstraEdge> queue = new PriorityQueue<>();
        queue.add(new DijkstraEdge(0, startNode));

        while(queue.size() > 0){
            DijkstraEdge edge = queue.poll();
            Integer edgeDistance = edge.distance;
            String destinatinoNode = edge.node;
            ArrayList<DijkstraEdge> adjacentEdges = graph.get(destinatinoNode);
            for(DijkstraEdge adjacentEdge : adjacentEdges){
                Integer adjacentDistance = adjacentEdge.distance;
                String adjacentNode = adjacentEdge.node;
                Integer totalDistance = edgeDistance + adjacentDistance;
                if(distance.get(adjacentNode) > totalDistance){
                    distance.put(adjacentNode, totalDistance);
                    queue.add(new DijkstraEdge(totalDistance, adjacentNode));
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

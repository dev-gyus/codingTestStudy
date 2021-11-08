package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 다익스트라 알고리즘에 사용되는 그래프의 각 엣지의 정보를 갖는 클래스
 */
class Edge implements Comparable<Edge>{
    public int distance;
    public String vertex;

    public Edge(int distance, String vertex){
        this.distance = distance;
        this.vertex = vertex;
    }
    @Override
    public String toString(){
        return "vertex: " + this.vertex + ", distance: " + this.distance;
    }

    @Override
    public int compareTo(Edge edge){
        return this.distance - edge.distance;
    }
}

/**
 * 다익스트라 알고리즘 구현
 */
public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<Edge>> graph, String start){
        HashMap<String, Integer> distances = new HashMap<>();
        // 초기세팅 (start Node는 거리 0, 나머지는 무한대)
        for(String key : graph.keySet()){
            distances.put(key, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(distances.get(start), start));

        while(priorityQueue.size() > 0){
            Edge currEdge = priorityQueue.poll();
            int currDistance = currEdge.distance;
            String currVertex = currEdge.vertex;

            if(distances.get(currVertex) < currDistance) continue;

            for(int index = 0; index < graph.get(currVertex).size(); index++){
                Edge adjacentEdge = graph.get(currVertex).get(index);
                String adjacentVertex = adjacentEdge.vertex;
                int adjacentDistance = adjacentEdge.distance;
                int weight = currDistance + adjacentDistance;
                if(distances.get(adjacentVertex) > weight){
                    priorityQueue.add(new Edge(weight, adjacentVertex));
                    distances.put(adjacentVertex, weight);
                }
            }
        }
        return distances;
    }

    public static void main(String[] args){
        HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
        graph.put("A", new ArrayList<Edge>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D"))));
        graph.put("B", new ArrayList<Edge>());
        graph.put("C", new ArrayList<Edge>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
        graph.put("D", new ArrayList<Edge>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
        graph.put("E", new ArrayList<Edge>(Arrays.asList(new Edge(1, "F"))));
        graph.put("F", new ArrayList<Edge>(Arrays.asList(new Edge(5, "A"))));

        DijkstraPath dObject = new DijkstraPath();
        System.out.println(dObject.dijkstraFunc(graph, "A"));
    }
}
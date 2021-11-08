package graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 다익스트라 알고리즘에 사용되는 그래프의 각 엣지의 정보를 갖는 클래스
 */
class DijkstraEdge implements Comparable<DijkstraEdge>{
    public int distance;
    public String vertex;

    public DijkstraEdge(int distance, String vertex){
        this.distance = distance;
        this.vertex = vertex;
    }
    @Override
    public String toString(){
        return "vertex: " + this.vertex + ", distance: " + this.distance;
    }

    @Override
    public int compareTo(DijkstraEdge dijkstraEdge){
        return this.distance - dijkstraEdge.distance;
    }
}

/**
 * 다익스트라 알고리즘 구현
 */
public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<DijkstraEdge>> graph, String start){
        HashMap<String, Integer> distances = new HashMap<>();
        // 초기세팅 (start Node는 거리 0, 나머지는 무한대)
        for(String key : graph.keySet()){
            distances.put(key, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<DijkstraEdge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new DijkstraEdge(distances.get(start), start));

        while(priorityQueue.size() > 0){
            DijkstraEdge currDijkstraEdge = priorityQueue.poll();
            int currDistance = currDijkstraEdge.distance;
            String currVertex = currDijkstraEdge.vertex;

            if(distances.get(currVertex) < currDistance) continue;

            for(int index = 0; index < graph.get(currVertex).size(); index++){
                DijkstraEdge adjacentDijkstraEdge = graph.get(currVertex).get(index);
                String adjacentVertex = adjacentDijkstraEdge.vertex;
                int adjacentDistance = adjacentDijkstraEdge.distance;
                int weight = currDistance + adjacentDistance;
                if(distances.get(adjacentVertex) > weight){
                    priorityQueue.add(new DijkstraEdge(weight, adjacentVertex));
                    distances.put(adjacentVertex, weight);
                }
            }
        }
        return distances;
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
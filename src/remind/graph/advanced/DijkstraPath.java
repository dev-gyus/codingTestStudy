package remind.graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class DijkstraEdge implements Comparable<DijkstraEdge>{
    Integer distance;
    String node;

    public DijkstraEdge(Integer distance, String node) {
        this.distance = distance;
        this.node = node;
    }

    @Override
    public int compareTo(DijkstraEdge dijkstraEdge){
        return this.distance - dijkstraEdge.distance;
    }

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.node + ")";
    }
}
public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<DijkstraEdge>> graph, String startNode){
        HashMap<String, Integer> distances = new HashMap<>();
        // 스타트 노드를 제외한 나머지 모든 노드까지 가는 거리는 모두 무한으로 설정
        for (String key : graph.keySet()) {
            distances.put(key, Integer.MAX_VALUE);
        }
        // 스타트 노드의 거리는 0 으로 세팅
        distances.put(startNode, 0);
        // 특정 노드에 연결된 엣지의 거리를 비교해서 방문하기 위해 queue 생성
        PriorityQueue<DijkstraEdge> queue = new PriorityQueue<>();
        // 시작 노드와 연결된 엣지들 모두 큐에 넣기
        queue.add(new DijkstraEdge(0, startNode));
        while(queue.size() > 0){
            DijkstraEdge currentEdge = queue.poll();
            String currentNode = currentEdge.node;
            Integer currentEdgeDistance = currentEdge.distance;
            // 현재 노드와 연결된 엣지목록을 가져옴
            ArrayList<DijkstraEdge> adjacentEdgeList = graph.get(currentNode);
            for (DijkstraEdge adjacentEdge : adjacentEdgeList) {
                Integer adjacentEdgeDistance = adjacentEdge.distance;
                String adjacentNode = adjacentEdge.node;
                Integer totalDistance = currentEdgeDistance + adjacentEdgeDistance;
                if (distances.get(adjacentNode) > totalDistance) {
                    distances.put(adjacentNode, totalDistance);
                    queue.add(new DijkstraEdge(totalDistance, adjacentNode));
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

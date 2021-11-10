package remind.graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class DijkstraEdge implements Comparable<DijkstraEdge>{
    public Integer distance;
    public String node1;

    public DijkstraEdge(Integer distance, String node1) {
        this.distance = distance;
        this.node1 = node1;
    }

    @Override
    public int compareTo(DijkstraEdge dijkstraEdge){
        return this.distance - dijkstraEdge.distance;
    }

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.node1 + ")";
    }

}
public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<DijkstraEdge>> graph, String startNode){
        HashMap<String, Integer> distances = new HashMap<>();
        // 초기화
        for(String key : graph.keySet()){
            distances.put(key, Integer.MAX_VALUE);
        }
        distances.put(startNode, 0);

        // 특정 노드와 연결된 노드들 중 방문해야할 노드 정보를 갖는 ArrayList를 선언 (다익스트라는 시작 노드에서 그래프 전체의 노드들로 가는 각각의 최단거리를 구하는 알고리즘)
        ArrayList<DijkstraEdge> needVisitList = new ArrayList<>();

        // 최초 스타트 엣지 추가
        needVisitList.add(new DijkstraEdge(distances.get(startNode), startNode));

        while(needVisitList.size() > 0){
            DijkstraEdge currentEdge = needVisitList.remove(0);
            String currentNode = currentEdge.node1;
            Integer currentDistance = currentEdge.distance;
            ArrayList<DijkstraEdge> adjacentEdges = graph.get(currentNode);


            for (DijkstraEdge adjacentEdge : adjacentEdges) {
                String adjacentNode = adjacentEdge.node1;
                Integer adjacentDistance = adjacentEdge.distance;
                Integer weight = currentDistance + adjacentDistance;
                // 현재 노드에서 인접한 노드를 방문할때 필요한 총 비용(거리)이 기존에 인접한 노드를 방문할때 갖고있던 최소값보다 더 작으면 거리를 갱신하고, 방문필요 노드 목록에 인접한노드의 인접한노드를 추가함
                // 추가하는이유 = 다익스트라 알고리즘은 기본적으로 탐욕알고리즘을 적용하므로 최소한의 비용을 필요로하는 경우만을 모으다보면 최소한의 비용으로 원하는 결과를 얻을수 있다는 마인드를 전제로 하기 때문
                if(distances.get(adjacentNode) > weight) {
                    distances.put(adjacentNode, weight);
                    needVisitList.add(new DijkstraEdge(weight, adjacentNode));
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

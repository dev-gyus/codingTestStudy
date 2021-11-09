package remind.graph.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;

class PrimEdge implements Comparable<PrimEdge> {
    public Integer distance;
    public String node1;
    public String node2;

    public PrimEdge(Integer distance, String node1, String node2) {
        this.distance = distance;
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public int compareTo(PrimEdge primEdge){
        return this.distance - primEdge.distance;
    }

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.node1 + ", " + this.node2 + ")";
    }
}
public class PrimPath {
    public ArrayList<PrimEdge> primFunc(String startNode, ArrayList<PrimEdge> edgeList){
        ArrayList<PrimEdge> mst = new ArrayList<>(), adjacentNodes = new ArrayList<>();
        HashMap<String, ArrayList<PrimEdge>> adjacentEdges = new HashMap<>();
        ArrayList<String> connectedNodes = new ArrayList<>();

        for (PrimEdge primEdge : edgeList) {
            if(!adjacentEdges.containsKey(primEdge.node1)){
                adjacentEdges.put(primEdge.node1, new ArrayList<>());
            }
            if(!adjacentEdges.containsKey(primEdge.node2)){
                adjacentEdges.put(primEdge.node2, new ArrayList<>());
            }
        }

        for (PrimEdge primEdge : edgeList) {
            // node1에 대한 인접 노드를 엣지 리스트에 추가
            adjacentEdges.get(primEdge.node1).add(new PrimEdge(primEdge.distance, primEdge.node1, primEdge.node2));
            // node2에 대한 인접 노드를 엣지 리스트에 추가
            adjacentEdges.get(primEdge.node2).add(new PrimEdge(primEdge.distance, primEdge.node2, primEdge.node1));
        }

        // 연결 노드에 시작 노드 추가
        connectedNodes.add(startNode);
        // 최소 비용을 가진 엣지를 방문하기위한 큐 선언
        PriorityQueue<PrimEdge> queue = new PriorityQueue<>();
        // 큐에 최초 노드에 연결된 엣지들의 정보 추가
        queue.addAll(adjacentEdges.getOrDefault(startNode, new ArrayList<>()));
        // 큐 사이즈가 0개 이상일때만 루프 돌도록 선언
        while(queue.size() > 0){
            // 최소 비용을 가진 엣지 꺼내옴
            PrimEdge edge = queue.poll();
            // 기존에 연결된 노드들에 해당 엣지에 연결된 노드중 node2(도착점)에 연결된경우 (기준이 node1부터 시작해서 node2로 연결된걸 가정함)
            if(!connectedNodes.contains(edge.node2)){
                // 연결된 노드 리스트에 해당 노드의 노드2 값 추가 노드1(출발) -> 노드2(도착) 이라고 가정함
                connectedNodes.add(edge.node2);
                // 최소 신장 트리에 해당 엣지 추가
                mst.add(new PrimEdge(edge.distance, edge.node1, edge.node2));
                // 해당 노드와 연결된 엣지들 추출
                adjacentNodes = adjacentEdges.getOrDefault(edge.node2, new ArrayList<>());
                // 인접한 노드들중 그 노드들과 연결된 엣지의 도착점(node2)이 연결된 노드 목록에 없는 경우 모두 queue에 추가함
                for(PrimEdge adjacentEdge : adjacentNodes){
                    if(!connectedNodes.contains(adjacentEdge.node2)){
                        queue.add(adjacentEdge);
                    }
                }
            }
        }
        return mst;
    }
    public static void main(String[] args){
        ArrayList<PrimEdge> myedges = new ArrayList<PrimEdge>();
        myedges.add(new PrimEdge(7, "A", "B"));
        myedges.add(new PrimEdge(5, "A", "D"));
        myedges.add(new PrimEdge(8, "B", "C"));
        myedges.add(new PrimEdge(9, "B", "D"));
        myedges.add(new PrimEdge(7, "D", "E"));
        myedges.add(new PrimEdge(5, "C", "E"));
        myedges.add(new PrimEdge(7, "B", "E"));
        myedges.add(new PrimEdge(6, "D", "F"));
        myedges.add(new PrimEdge(8, "E", "F"));
        myedges.add(new PrimEdge(9, "E", "G"));
        myedges.add(new PrimEdge(11, "F", "G"));

        PrimPath primPath = new PrimPath();
        System.out.println(primPath.primFunc("A", myedges));
    }
}

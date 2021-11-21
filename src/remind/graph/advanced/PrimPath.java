package remind.graph.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;

class PrimEdge implements Comparable<PrimEdge>{
    public Integer distance;
    public String node1;
    public String node2;

    public PrimEdge(Integer distance, String node1, String node2) {
        this.distance = distance;
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public int compareTo(PrimEdge edge){
        return this.distance - edge.distance;
    }

    public String toString(){
        return "(" + this.distance + ", " + this.node1 + ", " + this.node2 + ")";
    }
}
public class PrimPath {
    public ArrayList<PrimEdge> primFunc(String startNode, ArrayList<PrimEdge> edges){
        ArrayList<PrimEdge> mst = new ArrayList<>();
        // 특정 노드에 연결된 엣지가 뭐가있는지 알기위한 객체 선언
        HashMap<String, ArrayList<PrimEdge>> connectedEdges = new HashMap<>();
        // key, value 초기화
        for (PrimEdge edge : edges) {
            if(!connectedEdges.containsKey(edge.node1)){
                connectedEdges.put(edge.node1, new ArrayList<>());
            }
            if(!connectedEdges.containsKey(edge.node2)){
                connectedEdges.put(edge.node2, new ArrayList<>());
            }
        }

        // value에 엣지 기본값 추가
        for(PrimEdge edge : edges){
            connectedEdges.get(edge.node1).add(new PrimEdge(edge.distance, edge.node1, edge.node2));
            connectedEdges.get(edge.node2).add(new PrimEdge(edge.distance, edge.node2, edge.node1));
        }
        // 연결된 노드들을 관리하는 객체 추가
        ArrayList<String> connectedNodes = new ArrayList<>();
        // 시작 노드 연결
        connectedNodes.add(startNode);
        // 특정 노드의 연결된 엣지들 중 distance값이 가장 작은 것부터 꺼내기 위한 queue 선언
        PriorityQueue<PrimEdge> queue = new PriorityQueue<>();
        // 시작점에 연결된 엣지들 추가
        queue.addAll(connectedEdges.get(startNode));

        while(queue.size() > 0){
            PrimEdge adjacentEdge = queue.poll();
            // 특정 엣지의 도착점이 연결된 노드객체에 없다면 연결 노드에 추가하고, mst에 엣지 추가하고, 그 노드들과 연결된 엣지를 큐에 넣음
            if(!connectedNodes.contains(adjacentEdge.node2)){
                connectedNodes.add(adjacentEdge.node2);
                mst.add(adjacentEdge);
                queue.addAll(connectedEdges.get(adjacentEdge.node2));
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

        PrimPath pObject = new PrimPath();
        System.out.println(pObject.primFunc("A", myedges));
    }
}

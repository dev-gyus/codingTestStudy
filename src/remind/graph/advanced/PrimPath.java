package remind.graph.advanced;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.node1 + ", " + this.node2 + ")";
    }
}
public class PrimPath {
    public ArrayList<PrimEdge> primFunc(String startNode, ArrayList<PrimEdge> edges){
        ArrayList<PrimEdge> mst = new ArrayList<>();
        HashMap<String, ArrayList<PrimEdge>> connectedEdges = new HashMap<>();
        ArrayList<String> connectedNodes = new ArrayList<>();

        for (PrimEdge edge : edges) {
            String node1 = edge.node1;
            String node2 = edge.node2;
            if(!connectedEdges.containsKey(node1)){
                connectedEdges.put(node1, new ArrayList<>());
            }
            if(!connectedEdges.containsKey(node2)){
                connectedEdges.put(node2, new ArrayList<>());
            }
        }
        // 각 노드별로 연결된 엣지 추가
        for (PrimEdge edge : edges) {
            connectedEdges.get(edge.node1).add(new PrimEdge(edge.distance, edge.node1, edge.node2));
            connectedEdges.get(edge.node2).add(new PrimEdge(edge.distance, edge.node2, edge.node1));
        }
        // 최소 거리를 가진 엣지를 선택해야 하므로 queue 선언
        PriorityQueue<PrimEdge> queue = new PriorityQueue<>();
        // 큐에 시작노드에 연결된 엣지 추가
        queue.addAll(connectedEdges.get(startNode));
        // 연결된 노드 목록에 시작 노드 추가
        connectedNodes.add(startNode);
        while(queue.size() > 0){
            PrimEdge currentEdge = queue.poll();
            if(!connectedNodes.contains(currentEdge.node2)){
                connectedNodes.add(currentEdge.node2);
                mst.add(currentEdge);
                queue.addAll(connectedEdges.get(currentEdge.node2));
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

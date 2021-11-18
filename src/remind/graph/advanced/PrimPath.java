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

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.node1 + ", " + this.node2 + ")";
    }
}
public class PrimPath {
    public ArrayList<PrimEdge> primFunc(String startNode, ArrayList<PrimEdge> edges){
        ArrayList<PrimEdge> mst = new ArrayList<PrimEdge>();
        HashMap<String, ArrayList<PrimEdge>> connectedEdges = new HashMap<>();
        ArrayList<String> connectedNodes = new ArrayList<>();

        // 초기화
        for (PrimEdge edge : edges) {
            if(!connectedEdges.containsKey(edge.node1)){
                connectedEdges.put(edge.node1, new ArrayList<>());
            }
            if(!connectedEdges.containsKey(edge.node2)){
                connectedEdges.put(edge.node2, new ArrayList<>());
            }
        }

        for (PrimEdge edge : edges) {
            connectedEdges.get(edge.node1).add(new PrimEdge(edge.distance, edge.node1, edge.node2));
            connectedEdges.get(edge.node2).add(new PrimEdge(edge.distance, edge.node2, edge.node1));
        }

        PriorityQueue<PrimEdge> queue = new PriorityQueue<>();
        queue.addAll(connectedEdges.getOrDefault(startNode, new ArrayList<>()));
        connectedNodes.add(startNode);

        while(queue.size() > 0){
            PrimEdge poppedEdge = queue.poll();
            if(!connectedNodes.contains(poppedEdge.node2)) {
                mst.add(poppedEdge);
                connectedNodes.add(poppedEdge.node2);
                ArrayList<PrimEdge> adjacentEdges = connectedEdges.getOrDefault(poppedEdge.node2, new ArrayList<>());
                for (PrimEdge adjacentEdge : adjacentEdges) {
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

        PrimPath pObject = new PrimPath();
        System.out.println(pObject.primFunc("A", myedges));
    }
}

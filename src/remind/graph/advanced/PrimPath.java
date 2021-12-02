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
    public int compareTo(PrimEdge primEdge){
        return this.distance - primEdge.distance;
    }

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.node1 + ", " + this.node2 + ")";
    }
}
public class PrimPath {
    public ArrayList<PrimEdge> primFunc(String startNode, ArrayList<PrimEdge> edges){
        ArrayList<PrimEdge> mst = new ArrayList<>();
        HashMap<String, ArrayList<PrimEdge>> adjacentEdges = new HashMap<>();
        ArrayList<String> connectedNode = new ArrayList<>();
        for (PrimEdge edge : edges) {
            if(!adjacentEdges.containsKey(edge.node1)){
                adjacentEdges.put(edge.node1, new ArrayList<>());
            }
            if(!adjacentEdges.containsKey(edge.node2)){
                adjacentEdges.put(edge.node2, new ArrayList<>());
            }
        }

        for(PrimEdge edge : edges){
            adjacentEdges.get(edge.node1).add(new PrimEdge(edge.distance, edge.node1, edge.node2));
            adjacentEdges.get(edge.node2).add(new PrimEdge(edge.distance, edge.node2, edge.node1));
        }

        PriorityQueue<PrimEdge> queue = new PriorityQueue<>();
        queue.addAll(adjacentEdges.getOrDefault(startNode, new ArrayList<>()));
        connectedNode.add(startNode);
        while(queue.size() > 0) {
            PrimEdge poppedEdge = queue.poll();
            if (!connectedNode.contains(poppedEdge.node2)) {
                connectedNode.add(poppedEdge.node2);
                mst.add(poppedEdge);
                for (PrimEdge adjacentEdge : adjacentEdges.get(poppedEdge.node2)) {

                    if (!connectedNode.contains(adjacentEdge.node2)) {

                        queue.add(adjacentEdge);
                    }
                }
            }
        }
//
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

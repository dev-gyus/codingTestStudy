package remind.graph.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

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
        ArrayList<PrimEdge> currentEdgeList, candidateEdgeList, adjacentEdgeNodes;
        PrimEdge currentEdge, adjacentEdgeNode;
        ArrayList<String> connectedNodes = new ArrayList<>();
        ArrayList<PrimEdge> mst = new ArrayList<>();
        HashMap<String, ArrayList<PrimEdge>> adjacentEdges = new HashMap<>();

        // 초기화
        for (PrimEdge value : edges) {
            currentEdge = value;
            if (!adjacentEdges.containsKey(currentEdge.node1)) {
                adjacentEdges.put(currentEdge.node1, new ArrayList<PrimEdge>());
            }
            if (!adjacentEdges.containsKey(currentEdge.node2)) {
                adjacentEdges.put(currentEdge.node2, new ArrayList<PrimEdge>());
            }
        }
        for (PrimEdge edge : edges) {
            currentEdge = edge;
            // node1에 대해 인접한 노드 리스트 추가
            currentEdgeList = adjacentEdges.get(currentEdge.node1);
            currentEdgeList.add(new PrimEdge(currentEdge.distance, currentEdge.node1, currentEdge.node2));
            // node2에 대해 인접한 노드 리스트 추가
            currentEdgeList = adjacentEdges.get(currentEdge.node2);
            currentEdgeList.add(new PrimEdge(currentEdge.distance, currentEdge.node2, currentEdge.node1));
        }

        connectedNodes.add(startNode);
        // 혹시나 인접한 엣지목록에서 startNode에 대한 값이 없는경우 대비
        candidateEdgeList = adjacentEdges.getOrDefault(startNode, new ArrayList<PrimEdge>());
        Queue<PrimEdge> priorityQueue = new PriorityQueue<PrimEdge>();
        for (PrimEdge primEdge : candidateEdgeList) {
            priorityQueue.add(primEdge);
        }

        while(priorityQueue.size() > 0){
            PrimEdge poppedEdge = priorityQueue.poll();
            if(!connectedNodes.contains(poppedEdge.node2)){
                // 해당 Edge를 mst에 추가
                connectedNodes.add(poppedEdge.node2);
                mst.add(new PrimEdge(poppedEdge.distance, poppedEdge.node1, poppedEdge.node2));

                adjacentEdgeNodes = adjacentEdges.getOrDefault(poppedEdge.node2, new ArrayList<PrimEdge>());
                for (PrimEdge edgeNode : adjacentEdgeNodes) {
                    adjacentEdgeNode = edgeNode;
                    if (!connectedNodes.contains(adjacentEdgeNode.node2)) {
                        priorityQueue.add(adjacentEdgeNode);
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

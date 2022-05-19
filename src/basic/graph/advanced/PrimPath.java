package basic.graph.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;


class PrimEdge implements Comparable<PrimEdge> {
    public int weight;
    public String node1;
    public String node2;

    public PrimEdge(int weight, String node1, String node2) {
        this.weight = weight;
        this.node1 = node1;
        this.node2 = node2;
    }

    public String toString() {
        return "(" + this.weight + ", " + this.node1 + ", " + this.node2 + ")";
    }

    @Override
    public int compareTo(PrimEdge primEdge) {
        return this.weight - primEdge.weight;
    }
}

public class PrimPath {
    public ArrayList<PrimEdge> primFunc(String startNode, ArrayList<PrimEdge> primEdges){
        ArrayList<PrimEdge> currentPrimEdgeList, candidatePrimEdgeList, adjacentPrimEdgeNodes;
        PrimEdge currentPrimEdge, adjacentPrimEdgeNode;
        ArrayList<String> connectedNodes = new ArrayList<>();
        ArrayList<PrimEdge> mst = new ArrayList<>();
        HashMap<String, ArrayList<PrimEdge>> adjacentEdges = new HashMap<>();

        // 초기화
        for(int index = 0; index < primEdges.size(); index++){
            currentPrimEdge = primEdges.get(index);
            if(!adjacentEdges.containsKey(currentPrimEdge.node1)){
                adjacentEdges.put(currentPrimEdge.node1, new ArrayList<PrimEdge>());
            }
            if(!adjacentEdges.containsKey(currentPrimEdge.node2)){
                adjacentEdges.put(currentPrimEdge.node2, new ArrayList<PrimEdge>());
            }
        }
        for(int index = 0; index < primEdges.size(); index++){
            currentPrimEdge = primEdges.get(index);
            // node1에 대해 인접한 노드 리스트 추가
            currentPrimEdgeList =adjacentEdges.get(currentPrimEdge.node1);
            currentPrimEdgeList.add(new PrimEdge(currentPrimEdge.weight, currentPrimEdge.node1, currentPrimEdge.node2));
            // node2에 대해 인접한 노드 리스트 추가
            currentPrimEdgeList =adjacentEdges.get(currentPrimEdge.node2);
            currentPrimEdgeList.add(new PrimEdge(currentPrimEdge.weight, currentPrimEdge.node2, currentPrimEdge.node1));
        }

        connectedNodes.add(startNode);
        // 혹시나 인접한 엣지목록에서 startNode에 대한 값이 없는경우 대비
        candidatePrimEdgeList = adjacentEdges.getOrDefault(startNode, new ArrayList<PrimEdge>());
        Queue<PrimEdge> priorityQueue = new PriorityQueue<PrimEdge>();
        for(int index = 0; index < candidatePrimEdgeList.size(); index++){
            priorityQueue.add(candidatePrimEdgeList.get(index));
        }

        while(priorityQueue.size() > 0){
            PrimEdge poppedPrimEdge = priorityQueue.poll();
            if(!connectedNodes.contains(poppedPrimEdge.node2)){
                // 해당 Edge를 mst에 추가
                connectedNodes.add(poppedPrimEdge.node2);
                mst.add(new PrimEdge(poppedPrimEdge.weight, poppedPrimEdge.node1, poppedPrimEdge.node2));

                adjacentPrimEdgeNodes = adjacentEdges.getOrDefault(poppedPrimEdge.node2, new ArrayList<PrimEdge>());
                for(int index = 0; index < adjacentPrimEdgeNodes.size(); index++){
                    adjacentPrimEdgeNode = adjacentPrimEdgeNodes.get(index);
                    if(!connectedNodes.contains(adjacentPrimEdgeNode.node2)){
                        priorityQueue.add(adjacentPrimEdgeNode);
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

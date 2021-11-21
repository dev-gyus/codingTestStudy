package remind.graph.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;

// Edge만으로는 어떤 버텍스에서 어떤 버텍스로 이동하는지를 모르기 때문에 Path클래스 선언
class Path {
    public String node1;
    public String node2;
    public int weight;

    public Path(String node1, String node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public String toString(){
        return "(" + this.node1 + ", " + this.node2 + ", " + this.weight + ")";
    }
}
class Edge implements Comparable<Edge>{
    public String node;
    public int weight;

    public Edge(String node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public String toString(){
        return "(" + this.weight + ", " + this.node + ")";
    }

    @Override
    public int compareTo(Edge edge){
        return this.weight - edge.weight;
    }
}

public class ImprovePrimPath {
    public ArrayList<Path> improvedPrimFunc(HashMap<String, HashMap<String, Integer>> graph, String startNode){
        ArrayList<Path> mst = new ArrayList<>();
        PriorityQueue<Edge> keys = new PriorityQueue<>();
        // queue에 최소힙으로 이루어진 엣지의 distance를 업데이트하기위해 hashMap을 이용해 관리하도록함
        HashMap<String, Edge> keysObjects = new HashMap<>();
        // 현재 mst가 어느 버텍스에서 어느 버텍스로 연결된건지 모르기때문에 이를 해시맵으로 관리하도록 함
        HashMap<String, String> mstPath = new HashMap<>();
        HashMap<String, Integer> linkedEdges = new HashMap<>();

        for(String key : graph.keySet()){
            Edge edgeObject = null;
            if(key.equals(startNode)){
                edgeObject = new Edge(key, 0);
                mstPath.put(key, key);
            }
            else{
                edgeObject = new Edge(key, Integer.MAX_VALUE);
                mstPath.put(key, null);
            }
            keys.add(edgeObject);
            keysObjects.put(key, edgeObject);
        }
        Integer totalWeight = 0;
        // 전체 노드의 수만큼 반복처리가 될것임
        while(keys.size() > 0){
            Edge poppedEdge = keys.poll();
            // 큐에서 poll 하면 keysObjects에서도 remove를 해줘야됨
            keysObjects.remove(poppedEdge.node);
            // 위에서 poll한 엣지는 mst임
            mst.add(new Path(mstPath.get(poppedEdge.node), poppedEdge.node, poppedEdge.weight));
            totalWeight += poppedEdge.weight;

            linkedEdges = graph.get(poppedEdge.node);
            for(String adjacent : linkedEdges.keySet()){
                if(keysObjects.containsKey(adjacent)){
                    Edge linkedEdge = keysObjects.get(adjacent);
                    if(linkedEdges.get(adjacent) < linkedEdge.weight){
                        linkedEdge.weight = linkedEdges.get(adjacent);
                        mstPath.put(adjacent, poppedEdge.node);

                        keys.remove(linkedEdge);
                        keys.add(linkedEdge);
                    }
                }
            }
        }

        System.out.println(totalWeight);
        return mst;
    }

    public static void main(String[] args){
        HashMap<String, HashMap<String, Integer>> mygraph = new HashMap<String, HashMap<String, Integer>>();

        HashMap<String, Integer> edges;
        edges = new HashMap<String, Integer>();
        edges.put("B", 7);
        edges.put("D", 5);
        mygraph.put("A", edges);

        edges = new HashMap<String, Integer>();
        edges.put("A", 7);
        edges.put("D", 9);
        edges.put("C", 8);
        edges.put("E", 7);
        mygraph.put("B", edges);

        edges = new HashMap<String, Integer>();
        edges.put("B", 8);
        edges.put("E", 5);
        mygraph.put("C", edges);

        edges = new HashMap<String, Integer>();
        edges.put("A", 5);
        edges.put("B", 9);
        edges.put("E", 7);
        edges.put("F", 6);
        mygraph.put("D", edges);

        edges = new HashMap<String, Integer>();
        edges.put("B", 7);
        edges.put("C", 5);
        edges.put("D", 7);
        edges.put("F", 8);
        edges.put("G", 9);
        mygraph.put("E", edges);

        edges = new HashMap<String, Integer>();
        edges.put("D", 6);
        edges.put("E", 8);
        edges.put("G", 11);
        mygraph.put("F", edges);

        edges = new HashMap<String, Integer>();
        edges.put("E", 9);
        edges.put("F", 11);
        mygraph.put("G", edges);

        ImprovePrimPath pObject = new ImprovePrimPath();
        System.out.println(pObject.improvedPrimFunc(mygraph, "A"));
    }
}

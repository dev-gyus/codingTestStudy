package remind.graph.advanced;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

class Path {
    public Integer distance;
    public String node1;
    public String node2;

    public Path(Integer distance, String node1, String node2) {
        this.distance = distance;
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.node1 + ", " + this.node2 + ")";
    }
}

class Edge implements Comparable<Edge>{
    public Integer distance;
    public String node;

    public Edge(Integer distance, String node) {
        this.distance = distance;
        this.node = node;
    }
    @Override
    public int compareTo(Edge edge){
        return this.distance - edge.distance;
    }

}

public class ImprovedPrimPath {

    public ArrayList<Path> improvedPrimFunc(HashMap<String, HashMap<String, Integer>> graph, String startNode){
        ArrayList<Path> mst = new ArrayList<>();
        HashMap<String, String> mstPath = new HashMap<>();
        Queue<Edge> keys = new PriorityQueue<>();
        HashMap<String, Edge> keysObject = new HashMap<>();
        HashMap<String, Integer> linkedEdges = new HashMap<>();


        for (String key : graph.keySet()) {
            Edge edge;
            if(Objects.equals(key, startNode)){
                edge = new Edge(0, key);
                mstPath.put(key, key);
            }else{
                edge = new Edge(Integer.MAX_VALUE, key);
                mstPath.put(key, null);
            }
            keys.add(edge);
            keysObject.put(key, edge);
        }

        Integer totalWeight = 0;
        while(keys.size() > 0){
            Edge poppedEdge = keys.poll();
            keysObject.remove(poppedEdge.node);

            mst.add(new Path(poppedEdge.distance, mstPath.get(poppedEdge.node), poppedEdge.node));
            totalWeight += poppedEdge.distance;

            linkedEdges = graph.get(poppedEdge.node);

            for(String adjacent : linkedEdges.keySet()){
                if(keysObject.containsKey(adjacent)){
                    Edge linkedEdge = keysObject.get(adjacent);

                    if(linkedEdges.get(adjacent) < linkedEdge.distance){
                        linkedEdge.distance = linkedEdges.get(adjacent);
                        mstPath.put(adjacent, poppedEdge.node);

                        keys.remove(linkedEdge);
                        keys.add(linkedEdge);
                    }
                }
            }
        }
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

        ImprovedPrimPath pObject = new ImprovedPrimPath();
        System.out.println(pObject.improvedPrimFunc(mygraph, "A"));

    }
}

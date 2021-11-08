package graph.advanced;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

class Path{
    public String node1;
    public String node2;
    public int weight;

    public Path(String node1, String node2, int weight){
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public String toString(){
        return "(" + this.node1 + ", " + this.node2 + ", " + this.weight + ")";
    }
}

class AdvancedPrimEdge implements Comparable<AdvancedPrimEdge>{
    public String node;
    public int weight;

    public AdvancedPrimEdge(String node, int weight){
        this.node = node;
        this.weight = weight;
    }

    public String toString(){
        return "(" + this.node + ", " + this.weight + ")";
    }

    @Override
    public int compareTo(AdvancedPrimEdge advancedPrimEdge){
        return this.weight - advancedPrimEdge.weight;
    }
}


public class AdvancedPrimPath {
    public ArrayList<Path> improvedPrimFunc(HashMap<String, HashMap<String, Integer>> graph, String startNode){
        ArrayList<Path> mst = new ArrayList<>();
        Queue<AdvancedPrimEdge> keys = new PriorityQueue<>();
        HashMap<String, AdvancedPrimEdge> keysObject = new HashMap<>();
        HashMap<String, String> mstPath = new HashMap<>();
        Integer totalWeight = 0;
        AdvancedPrimEdge edgeObject, poppedEdge, linkedEdge;
        HashMap<String, Integer> linkedEdges = new HashMap<>();

        // 초기화
        for(String key : graph.keySet()){
            if(key == startNode){
                edgeObject = new AdvancedPrimEdge(key, 0);
                mstPath.put(key, key);
            }else{
                edgeObject = new AdvancedPrimEdge(key, Integer.MAX_VALUE);
                mstPath.put(key, null);
            }
            keys.add(edgeObject);
            keysObject.put(key, edgeObject);
        }

        while(keys.size() > 0){
            poppedEdge = keys.poll();
            keysObject.remove(poppedEdge.node);

            mst.add(new Path(mstPath.get(poppedEdge.node), poppedEdge.node, poppedEdge.weight));
            totalWeight += poppedEdge.weight;

            linkedEdges = graph.get(poppedEdge.node);
            for(String adjacent : linkedEdges.keySet()){
                if(keysObject.containsKey(adjacent)){
                    linkedEdge = keysObject.get(adjacent);

                    if(linkedEdges.get(adjacent) < linkedEdge.weight){
                        linkedEdge.weight = linkedEdges.get(adjacent);
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

        AdvancedPrimPath pObject = new AdvancedPrimPath();
        System.out.println(pObject.improvedPrimFunc(mygraph, "A"));
    }
}

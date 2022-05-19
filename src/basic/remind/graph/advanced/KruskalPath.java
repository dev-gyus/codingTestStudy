package basic.remind.graph.advanced;

import java.util.*;

class KruskalEdge implements Comparable<KruskalEdge>{
    public Integer distance;
    public String node1;
    public String node2;

    public KruskalEdge(Integer distance, String node, String node2) {
        this.distance = distance;
        this.node1 = node;
        this.node2 = node2;
    }

    @Override
    public int compareTo(KruskalEdge kruskalEdge){
        return this.distance - kruskalEdge.distance;
    }

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.node1 + ", " + this.node2 + ")";
    }
}
public class KruskalPath {
    public HashMap<String, String> parent = new HashMap<>();
    public HashMap<String, Integer> rank = new HashMap<>();

    public String findParent(String node){
        if(!parent.get(node).equals(node)){
            parent.put(node, findParent(parent.get(node)));
        }
        return parent.get(node);
    }

    public void union(String node1, String node2){
        String node1Parent = parent.get(node1);
        String node2Parent = parent.get(node2);

        if(!Objects.equals(node1Parent, node2Parent)) {
            if (rank.get(node1Parent) > rank.get(node2Parent)) {
                parent.put(node2Parent, node1Parent);
            } else {
                parent.put(node1Parent, node2Parent);
                if (Objects.equals(rank.get(node1Parent), rank.get(node2Parent))) {
                    rank.put(node2Parent, rank.get(node2Parent) + 1);
                }
            }
        }
    }

    public ArrayList<KruskalEdge> kruskalFunc(ArrayList<String> vertices, ArrayList<KruskalEdge> kruskalEdges){
        ArrayList<KruskalEdge> mst = new ArrayList<>();

        for (String vertex : vertices) {
            parent.put(vertex, vertex);
            rank.put(vertex, 0);
        }
        // distance 오름차순으로 edges 정렬
        Collections.sort(kruskalEdges);

        for (KruskalEdge kruskalEdge : kruskalEdges) {
            if(!Objects.equals(findParent(kruskalEdge.node1), findParent(kruskalEdge.node2))){
                mst.add(kruskalEdge);
                union(kruskalEdge.node1, kruskalEdge.node2);
            }
        }
        return mst;
    }

    public static void main(String[] args){
        ArrayList<String> vertices = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
        ArrayList<KruskalEdge> kruskalEdges = new ArrayList<KruskalEdge>();
        kruskalEdges.add(new KruskalEdge(7, "A", "B"));
        kruskalEdges.add(new KruskalEdge(5, "A", "D"));
        kruskalEdges.add(new KruskalEdge(7, "B", "A"));
        kruskalEdges.add(new KruskalEdge(8, "B", "C"));
        kruskalEdges.add(new KruskalEdge(9, "B", "D"));
        kruskalEdges.add(new KruskalEdge(7, "B", "E"));
        kruskalEdges.add(new KruskalEdge(8, "C", "B"));
        kruskalEdges.add(new KruskalEdge(5, "C", "E"));
        kruskalEdges.add(new KruskalEdge(5, "D", "A"));
        kruskalEdges.add(new KruskalEdge(9, "D", "B"));
        kruskalEdges.add(new KruskalEdge(7, "D", "E"));
        kruskalEdges.add(new KruskalEdge(6, "D", "F"));
        kruskalEdges.add(new KruskalEdge(7, "E", "B"));
        kruskalEdges.add(new KruskalEdge(5, "E", "C"));
        kruskalEdges.add(new KruskalEdge(7, "E", "D"));
        kruskalEdges.add(new KruskalEdge(8, "E", "F"));
        kruskalEdges.add(new KruskalEdge(9, "E", "G"));
        kruskalEdges.add(new KruskalEdge(6, "F", "D"));
        kruskalEdges.add(new KruskalEdge(8, "F", "E"));
        kruskalEdges.add(new KruskalEdge(11, "F", "G"));
        kruskalEdges.add(new KruskalEdge(9, "G", "E"));
        kruskalEdges.add(new KruskalEdge(11, "G", "F"));

        KruskalPath kObject = new KruskalPath();
        System.out.println(kObject.kruskalFunc(vertices, kruskalEdges));
    }
}

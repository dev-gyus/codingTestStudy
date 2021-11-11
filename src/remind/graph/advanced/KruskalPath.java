package remind.graph.advanced;

import java.util.*;

class KruskalEdge implements Comparable<KruskalEdge> {
    public Integer distance;
    public String node1;
    public String node2;

    public KruskalEdge(Integer distance, String node1, String node2) {
        this.distance = distance;
        this.node1 = node1;
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

    public String findParent(String tarNode){
        if(!Objects.equals(parent.get(tarNode), tarNode)){
            parent.put(tarNode, findParent(parent.get(tarNode)));
        }
        return parent.get(tarNode);
    }

    public void union(String node1, String node2){
        String node1Parent = findParent(node1);
        String node2Parent = findParent(node2);
        // node1과 node2의 루트 노드가 서로 같지 않다면 rank 비교
        if(!Objects.equals(node1Parent, node2Parent)){
            if(rank.get(node1Parent) > rank.get(node2Parent)){
                parent.put(node2, node1);
            }else{
                parent.put(node1, node2);
                if(Objects.equals(rank.get(node1Parent), rank.get(node2Parent))){
                    rank.put(node2Parent, rank.get(node2Parent) + 1);
                }
            }
        }
    }

    public ArrayList<KruskalEdge> kruskalFunc(ArrayList<String> vertices, ArrayList<KruskalEdge> kruskalEdges){
        ArrayList<KruskalEdge> mst = new ArrayList<>();
        // 초기화
        for(String vertice : vertices){
            parent.put(vertice, vertice);
            rank.put(vertice, 0);
        }
        // 엣지의 distance 오름차순으로 정렬(distance 낮은 순서대로 정렬)
        Collections.sort(kruskalEdges);

        for(KruskalEdge kruskalEdge : kruskalEdges){
            if(!Objects.equals(findParent(kruskalEdge.node1), findParent(kruskalEdge.node2))) {
                union(kruskalEdge.node1, kruskalEdge.node2);
                mst.add(kruskalEdge);
            }
        }
        return mst;
    }

    public static void main(String[] args){
        ArrayList<String> vertices = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
        ArrayList<KruskalEdge> edges = new ArrayList<KruskalEdge>();
        edges.add(new KruskalEdge(7, "A", "B"));
        edges.add(new KruskalEdge(5, "A", "D"));
        edges.add(new KruskalEdge(7, "B", "A"));
        edges.add(new KruskalEdge(8, "B", "C"));
        edges.add(new KruskalEdge(9, "B", "D"));
        edges.add(new KruskalEdge(7, "B", "E"));
        edges.add(new KruskalEdge(8, "C", "B"));
        edges.add(new KruskalEdge(5, "C", "E"));
        edges.add(new KruskalEdge(5, "D", "A"));
        edges.add(new KruskalEdge(9, "D", "B"));
        edges.add(new KruskalEdge(7, "D", "E"));
        edges.add(new KruskalEdge(6, "D", "F"));
        edges.add(new KruskalEdge(7, "E", "B"));
        edges.add(new KruskalEdge(5, "E", "C"));
        edges.add(new KruskalEdge(7, "E", "D"));
        edges.add(new KruskalEdge(8, "E", "F"));
        edges.add(new KruskalEdge(9, "E", "G"));
        edges.add(new KruskalEdge(6, "F", "D"));
        edges.add(new KruskalEdge(8, "F", "E"));
        edges.add(new KruskalEdge(11, "F", "G"));
        edges.add(new KruskalEdge(9, "G", "E"));
        edges.add(new KruskalEdge(11, "G", "F"));

        KruskalPath kObject = new KruskalPath();
        System.out.println(kObject.kruskalFunc(vertices, edges));
    }
}

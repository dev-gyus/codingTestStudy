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


    public String find(String node){
        if(!Objects.equals(parent.get(node), node)){
            parent.put(node, find(parent.get(node)));
        }
        return parent.get(node);
    }

    public void union(String nodeV, String nodeU){
        if(rank.get(nodeV) > rank.get(nodeU)){
            parent.put(nodeU, nodeV);
        }else{
            parent.put(nodeV, nodeU);
            if(Objects.equals(rank.get(nodeV), rank.get(nodeU))){
                rank.put(nodeU, rank.get(nodeU) + 1);
            }
        }
    }

    public void makeSet(String node){
        parent.put(node, node);
        rank.put(node, 0);
    }

    public ArrayList<KruskalEdge> kruskalFunc(ArrayList<KruskalEdge> edgeList, ArrayList<String> vertices){
        ArrayList<KruskalEdge> mst = new ArrayList<>();
        // parent, rank 초기화
        for (String vertex : vertices) {
            makeSet(vertex);
        }
        // edgeList 정렬 (크루스칼 = 엣지의 distance(비용) 순서대로 정렬해서 비용이 낮은 엣지끼리 선택해서 경로 찾는 알고리즘)
        Collections.sort(edgeList);
        // 크루스칼 알고리즘
        for(KruskalEdge kruskalEdge : edgeList){
            // 해당 엣지의 양쪽 버텍스의 루트노드가 각각 다를경우 union해서 최소 신장 루트에 넣음
            if(!Objects.equals(find(kruskalEdge.node1), find(kruskalEdge.node2))){
                union(kruskalEdge.node1, kruskalEdge.node2);
                mst.add(kruskalEdge);
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

        KruskalPath kruskalPath = new KruskalPath();
        System.out.println(kruskalPath.kruskalFunc(kruskalEdges, vertices));
    }
}

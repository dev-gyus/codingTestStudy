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

    public String toString(){
        return "(" + this.distance + ", " + this.node1 + ", " + this.node2 + ")";
    }
}
public class KruskalPath {
    public HashMap<String, String> parent = new HashMap<>();
    public HashMap<String, Integer> rank = new HashMap<>();

    public String findParent(String node){
        if(!Objects.equals(parent.get(node), node)){
            parent.put(node, findParent(parent.get(node)));
        }
        return parent.get(node);
    }

    public void union(String node1, String node2){
        String node1Parent = findParent(node1);
        String node2Parent = findParent(node2);
        // node1, node2의 루트 노드가 서로 다르면 사이클이 없다고 판단, union 실행
        if(!Objects.equals(node1Parent, node2Parent)){
            // node1의 루트 노드 랭크가 node2 루트 노드 랭크보다 높다면, node2 루트 노드의 부모노드를 node1으로 지정
            if(rank.get(node1Parent) > rank.get(node2Parent)){
                parent.put(node2Parent, node1Parent);
            }
            // 아니면 node1의 루트 노드 부모노드를 node2 루트 노드로 지정
            else{
                parent.put(node1Parent, node2Parent);
                // 만약 두 루트노드의 랭크가 같다면, node2 루트 노드의 랭크를 하나 올림
                if(Objects.equals(rank.get(node1Parent), rank.get(node2Parent))){
                    rank.put(node2Parent, rank.get(node2Parent) + 1);
                }
            }
        }
    }

    public ArrayList<KruskalEdge> kruskalFunc(ArrayList<String> vertices, ArrayList<KruskalEdge> edges){
        ArrayList<KruskalEdge> mst = new ArrayList<>();
        // parent, rank 초기값 세팅
        for(String vertex : vertices){
            parent.put(vertex, vertex);
            rank.put(vertex, 0);
        }
        // distance 낮은 순서대로 엣지를 정렬
        Collections.sort(edges);
        while(edges.size() > 0){
            KruskalEdge currentEdge = edges.remove(0);
            String node1 = currentEdge.node1;
            String node2 = currentEdge.node2;
            // node1, node2의 루트노드가 같지 않다면 하나로 연결하고 mst 추가
            if(!Objects.equals(findParent(node1), findParent(node2))){
                mst.add(currentEdge);
                union(node1, node2);
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

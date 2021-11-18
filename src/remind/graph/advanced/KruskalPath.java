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

    public String findParent(String node1){
        // 특정 트리의 루트노드를 제외한 모든 자식노드들을 루트노드 바로 밑으로 붙여서 루트노드가 바로 부모노드가 되도록 세팅
        if(!Objects.equals(parent.get(node1), node1)){
            parent.put(node1, findParent(parent.get(node1)));
        }
        return parent.get(node1);
    }

    // 두 노드의 루트 노드가 서로 다를경우 하나로 합침 <- 루트가 같지 않으면 사이클이 생기지 않는다는걸 전제로함
    public void union(String node1, String node2){
        String node1Parent = findParent(node1);
        String node2Parent = findParent(node2);

        // 두 노드의 루트노드가 서로 다를경우
        if(!Objects.equals(node1Parent, node2Parent)){
            // node1의 부모노드의 랭크가 더 높으면 node1의 부모노드(루트노드)에 node2의 부모노드(루트노드)를 연결함
            if(rank.get(node1Parent) > rank.get(node2Parent)){
                parent.put(node2Parent, node1Parent);
            }
            // 아니라면 node2의 부모노드에 node1의 부모노드(루트노드)를 연결함, 만약 이때 두 부모노드의 랭크가 같다면 node2의 랭크를 하나 올림
            else{
                parent.put(node1Parent, node2Parent);
                if(Objects.equals(rank.get(node1Parent), rank.get(node2Parent))){
                    rank.put(node2Parent, rank.get(node2Parent) + 1);
                }
            }
        }
    }

    public ArrayList<KruskalEdge> kruskalFunc(ArrayList<String> vertices, ArrayList<KruskalEdge> edges){
        ArrayList<KruskalEdge> mst = new ArrayList<>();
        for (String vertex : vertices) {
            parent.put(vertex, vertex);
            rank.put(vertex, 0);
        }

        // 엣지의 distance가 낮은 순서대로 정렬
        Collections.sort(edges);

        for (KruskalEdge edge : edges) {
            if(!Objects.equals(findParent(edge.node1), findParent(edge.node2))) {
                mst.add(edge);
                union(edge.node1, edge.node2);
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

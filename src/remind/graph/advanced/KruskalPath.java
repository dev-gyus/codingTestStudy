package remind.graph.advanced;

import java.util.*;

class KruskalEdge implements Comparable<KruskalEdge>{
    public Integer distance;
    public String startNode;
    public String endNode;

    public KruskalEdge(Integer distance, String startNode, String endNode) {
        this.distance = distance;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @Override
    public int compareTo(KruskalEdge kruskalEdge){
        return this.distance - kruskalEdge.distance;
    }

    @Override
    public String toString(){
        return "(" + this.distance + ", " + this.startNode + ", " + this.endNode + ")";
    }
}
public class KruskalPath {
    public HashMap<String, String> parents = new HashMap<>();
    public HashMap<String, Integer> ranks = new HashMap<>();

    public String find(String node){
        if(!Objects.equals(parents.get(node), node)){
            parents.put(node, this.find(parents.get(node)));
        }
        return parents.get(node);
    }

    public void union(String node1, String node2){
            if (ranks.get(node1) > ranks.get(node2)) {
                parents.put(node2, node1);
            } else {
                parents.put(node1, node2);
                if (Objects.equals(ranks.get(node1), ranks.get(node2))) {
                    ranks.put(node2, ranks.get(node2) + 1);
                }
            }
    }

    public ArrayList<KruskalEdge> kruskalFunc(ArrayList<String> vertices, ArrayList<KruskalEdge> kruskalEdges){
        ArrayList<KruskalEdge> mst = new ArrayList<>();
        // 초기화
        vertices.forEach(node -> {
            parents.put(node, node);
            ranks.put(node, 0);
        });
        // 거리가 짧은 엣지 순으로 소팅
        Collections.sort(kruskalEdges);

        for (KruskalEdge kruskalEdge : kruskalEdges) {
            String node1Parent = find(kruskalEdge.startNode);
            String node2Parent = find(kruskalEdge.endNode);
            // node1과 node2의 부모노드가 같다면 사이클이 생기므로 같지 않은 경우에만 하나로 합침
            if (!Objects.equals(node1Parent, node2Parent)) {
                union(node1Parent, node2Parent);
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

        KruskalPath kObject = new KruskalPath();
        System.out.println(kObject.kruskalFunc(vertices, kruskalEdges));
    }
}

package graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class KruskalEdge implements Comparable<KruskalEdge> {
    public int weight;
    public String nodeV;
    public String nodeU;

    public KruskalEdge(int weight, String nodeV, String nodeU){
        this.weight = weight;
        this.nodeV = nodeV;
        this.nodeU = nodeU;
    }

    @Override
    public String toString(){
        return "(" + this.weight + "," + this.nodeV + "," + this.nodeU + ")";
    }

    @Override
    public int compareTo(KruskalEdge kruskalEdge){
        return this.weight - kruskalEdge.weight;
    }
}

public class KruskalPath {
    HashMap<String, String> parent = new HashMap<>();
    HashMap<String, Integer> rank = new HashMap<>();


    public String find(String node){
        // path compression 기법
        if(this.parent.get(node) != node){
            this.parent.put(node, this.find(this.parent.get(node)));
        }
        return this.parent.get(node);
    }

    public void union(String nodeV, String nodeU){
        String root1 = this.find(nodeV);
        String root2 = this.find(nodeU);

        // union-by-rank 기법 사용
        if(this.rank.get(root1) > this.rank.get(root2)){
            this.parent.put(root2, root1);
        }else{
            this.parent.put(root1, root2);
            if(this.rank.get(root1) == this.rank.get(root2)){
                this.rank.put(root2, this.rank.get(root2) + 1);
            }
        }
    }

    public void makeSet(String node){
        this.parent.put(node, node);
        this.rank.put(node, 0);
    }

    public ArrayList<KruskalEdge> kruskalFunc(ArrayList<String> vertices, ArrayList<KruskalEdge> dijkstraEdges){
        ArrayList<KruskalEdge> mst = new ArrayList<>();
        KruskalEdge currentDijkstraEdge;

        // 초기화
        for(int index = 0; index < vertices.size(); index++){
            this.makeSet(vertices.get(index));
        }

        // 간선 weight 기반 sorting
        Collections.sort(dijkstraEdges);

        for(int index = 0; index < dijkstraEdges.size(); index++){
            currentDijkstraEdge = dijkstraEdges.get(index);
            if(this.find(currentDijkstraEdge.nodeV) != this.find(currentDijkstraEdge.nodeU)){
                this.union(currentDijkstraEdge.nodeV, currentDijkstraEdge.nodeU);
                mst.add(currentDijkstraEdge);
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

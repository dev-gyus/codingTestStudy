package remind.graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class DijkstraEdge implements Comparable<DijkstraEdge>{
    public Integer distance;
    public String vertex;

    public DijkstraEdge(Integer distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }

    @Override
    public int compareTo(DijkstraEdge dijkstraEdge){
        return this.distance - dijkstraEdge.distance;
    }

    @Override
    public String toString(){
        return "(" + this.vertex + ", " + this.distance + ")";
    }
}
public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<DijkstraEdge>> graph, String startNode){
        HashMap<String, Integer> visited = new HashMap<>();
        PriorityQueue<DijkstraEdge> queue = new PriorityQueue<>();

        for(String key : graph.keySet()){
            if(key.equals(startNode)) {
                visited.put(key, 0);
            }else{
                visited.put(key, Integer.MAX_VALUE);
            }
        }

        queue.add(new DijkstraEdge(visited.get(startNode), startNode));

        while(queue.size() > 0){
            DijkstraEdge dijkstraEdge = queue.poll();
            Integer currDistance = dijkstraEdge.distance;
            String currVertex = dijkstraEdge.vertex;
            if(visited.get(currVertex) < currDistance) continue;
            ArrayList<DijkstraEdge> nodeList = graph.get(currVertex);

            for (DijkstraEdge adjacentNode : nodeList) {
                String adjacentVertex = adjacentNode.vertex;
                Integer adjacentDistance = adjacentNode.distance;
                Integer weight = adjacentDistance + currDistance;

                if (weight < visited.get(adjacentVertex)) {
                    queue.add(new DijkstraEdge(weight, adjacentVertex));
                    visited.put(adjacentVertex, weight);
                }
            }

        }
        return visited;
    }

    public static void main(String[] args){
        HashMap<String, ArrayList<DijkstraEdge>> graph = new HashMap<String, ArrayList<DijkstraEdge>>();
        graph.put("A", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(8, "B"), new DijkstraEdge(1, "C"), new DijkstraEdge(2, "D"))));
        graph.put("B", new ArrayList<DijkstraEdge>());
        graph.put("C", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(5, "B"), new DijkstraEdge(2, "D"))));
        graph.put("D", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(3, "E"), new DijkstraEdge(5, "F"))));
        graph.put("E", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(1, "F"))));
        graph.put("F", new ArrayList<DijkstraEdge>(Arrays.asList(new DijkstraEdge(5, "A"))));
        DijkstraPath dObject = new DijkstraPath();
        System.out.println(dObject.dijkstraFunc(graph, "A"));
    }
}

package remind.graph.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge>{
    public Integer distance;
    public String vertex;

    public Edge(Integer distance, String vertex) {
        this.distance = distance;
        this.vertex = vertex;
    }

    @Override
    public int compareTo(Edge edge){
        return this.distance - edge.distance;
    }

    @Override
    public String toString(){
        return "(" + this.vertex + ", " + this.distance + ")";
    }
}
public class DijkstraPath {
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<Edge>> graph, String startNode){
        HashMap<String, Integer> visited = new HashMap<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        for(String key : graph.keySet()){
            if(key.equals(startNode)) {
                visited.put(key, 0);
            }else{
                visited.put(key, Integer.MAX_VALUE);
            }
        }

        queue.add(new Edge(visited.get(startNode), startNode));

        while(queue.size() > 0){
            Edge edge = queue.poll();
            Integer currDistance = edge.distance;
            String currVertex = edge.vertex;
            if(visited.get(currVertex) < currDistance) continue;
            ArrayList<Edge> nodeList = graph.get(currVertex);

            for (Edge adjacentNode : nodeList) {
                String adjacentVertex = adjacentNode.vertex;
                Integer adjacentDistance = adjacentNode.distance;
                Integer weight = adjacentDistance + currDistance;

                if (weight < visited.get(adjacentVertex)) {
                    queue.add(new Edge(weight, adjacentVertex));
                    visited.put(adjacentVertex, weight);
                }
            }

        }
        return visited;
    }

    public static void main(String[] args){
        HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
        graph.put("A", new ArrayList<Edge>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D"))));
        graph.put("B", new ArrayList<Edge>());
        graph.put("C", new ArrayList<Edge>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
        graph.put("D", new ArrayList<Edge>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
        graph.put("E", new ArrayList<Edge>(Arrays.asList(new Edge(1, "F"))));
        graph.put("F", new ArrayList<Edge>(Arrays.asList(new Edge(5, "A"))));
        DijkstraPath dObject = new DijkstraPath();
        System.out.println(dObject.dijkstraFunc(graph, "A"));
    }
}

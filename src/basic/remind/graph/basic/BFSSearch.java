package basic.remind.graph.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BFSSearch {

    public ArrayList<String> bfsFunc(HashMap<String, ArrayList<String>> graph, String startNode){
        ArrayList<String> visited = new ArrayList<>();
        visited.add(startNode);

        ArrayList<String> needVisit = new ArrayList<>(graph.get(startNode));

        while(needVisit.size() > 0){
            String currNeedVisit = needVisit.remove(0);
            if(!visited.contains(currNeedVisit)){
                needVisit.addAll(graph.get(currNeedVisit));
                visited.add(currNeedVisit);
            }
        }

        return visited;
    }

    public static void main(String[] args){
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();

        graph.put("A", new ArrayList<String>(Arrays.asList("B", "C")));
        graph.put("B", new ArrayList<String>(Arrays.asList("A", "D")));
        graph.put("C", new ArrayList<String>(Arrays.asList("A", "G", "H", "I")));
        graph.put("D", new ArrayList<String>(Arrays.asList("B", "E", "F")));
        graph.put("E", new ArrayList<String>(List.of("D")));
        graph.put("F", new ArrayList<String>(List.of("D")));
        graph.put("G", new ArrayList<String>(List.of("C")));
        graph.put("H", new ArrayList<String>(List.of("C")));
        graph.put("I", new ArrayList<String>(Arrays.asList("C", "J")));
        graph.put("J", new ArrayList<String>(List.of("I")));

        BFSSearch bObject = new BFSSearch();
        System.out.println(bObject.bfsFunc(graph, "A"));
    }
}

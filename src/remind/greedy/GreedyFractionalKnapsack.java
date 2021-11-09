package remind.greedy;

import java.util.ArrayList;
import java.util.Collections;

// 배낭문제
class KnapsackObject implements Comparable<KnapsackObject>{
    public Integer weight;
    public Integer value;

    public KnapsackObject(Integer weight, Integer value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(KnapsackObject knapsackObject){
        return (int)(((double) knapsackObject.value/ knapsackObject.weight) * 10) - (int)((double) this.value/this.weight);
    }

    @Override
    public String toString(){
        return "weight: " + this.weight + ", value: " + this.value;
    }
}
public class GreedyFractionalKnapsack {
    public void knapsackFunc(Integer[][] objectList, Double tarWeight){
        ArrayList<KnapsackObject> objectArrayList = new ArrayList<>();
        if(objectList.length <= 0) System.out.println("0");
        if(objectList.length == 1) {
            KnapsackObject knapsackObject = new KnapsackObject(objectList[0][0], objectList[0][1]);
            System.out.println((tarWeight/(double)knapsackObject.weight) * knapsackObject.value);
        }
        double value = 0.0;

        // objectList 세팅
        for(Integer[] tar : objectList){
            objectArrayList.add(new KnapsackObject(tar[0], tar[1]));
        }
        // 무가비로 내림차순(가격/무게)
        Collections.sort(objectArrayList);
        for (KnapsackObject knapsackObject : objectArrayList) {
            if(tarWeight < knapsackObject.weight){
                value += tarWeight * ((double)knapsackObject.value/(double)knapsackObject.weight);
                break;
            }else{
                value +=  knapsackObject.value;
                tarWeight -= knapsackObject.weight;
            }
        }
        System.out.println(value);
    }

    public static void main(String[] args){
        GreedyFractionalKnapsack gObject = new GreedyFractionalKnapsack();
        Integer[][] objectList = { {10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5} };
        gObject.knapsackFunc(objectList, 64.5);
    }
}

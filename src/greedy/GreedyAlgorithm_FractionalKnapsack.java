package greedy;

import java.util.Arrays;

public class GreedyAlgorithm_FractionalKnapsack {

    public void knapsackFunc(Integer[][] objectList, double capacity) {
        double totalValue = 0.0;
        double fraction = 0.0;
        Arrays.sort(objectList,
                (o1, o2) -> (int)((((double)o1[1]/(double)o1[0]) - ((double)o2[1]/(double)o2[0]))*10));
        for(int index=objectList.length-1; index>=0; index--){
            if((capacity - (double)objectList[index][0]) > 0){
                capacity -= (double)objectList[index][0];
                totalValue += (double)objectList[index][1];
                System.out.println("무게:" + objectList[index][0] + ", 가치:" + objectList[index][1]);
            }else{
                fraction = capacity / (double) objectList[index][0];
                totalValue += (double)objectList[index][1]*fraction;
                System.out.println("무게:" + objectList[index][0] + ", 가치:" + objectList[index][1] + ", 비율:" + fraction);
                break;
            }
        }
        System.out.println("담을 수 있는 가치: " + totalValue);
    }


    public static void main(String[] args){
        GreedyAlgorithm_FractionalKnapsack gObject = new GreedyAlgorithm_FractionalKnapsack();
        Integer[][] objectList = { {10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5} };
        gObject.knapsackFunc(objectList, 64.5);
    }
}

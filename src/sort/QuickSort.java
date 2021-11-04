package sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 퀵 정렬을 수행하는 클래스
 */
public class QuickSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) {
        if (dataList.size() <= 1) return dataList;
        Integer pivot = dataList.get(0);
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        for (int a = 1; a < dataList.size(); a++) {
            if (dataList.get(a) < pivot) {
                leftList.add(dataList.get(a));
            } else {
                rightList.add(dataList.get(a));
            }
        }
        ArrayList<Integer> mergedList = new ArrayList<>();
        mergedList.addAll(this.sort(leftList));
        mergedList.addAll(Arrays.asList(pivot));
        mergedList.addAll(this.sort(rightList));

        return mergedList;
    }

    public static void main(String[] args){
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }

        QuickSort qSort = new QuickSort();
        System.out.println(qSort.sort(testData));
    }
}

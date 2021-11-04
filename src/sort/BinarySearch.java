package sort;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 이진 탐색을 수행하는 클래스
 */
public class BinarySearch {
    public boolean searchFunc(ArrayList<Integer> dataList, Integer searchItem){
        if(dataList == null || dataList.size() <= 0) return false;
        if(dataList.size() == 1) return dataList.get(0) == searchItem;

        int mid = dataList.size()/2;
        if(searchItem == dataList.get(mid)){
            return true;
        }else{
            if(dataList.get(mid) < searchItem){
                return this.searchFunc(new ArrayList<>(dataList.subList(mid, dataList.size())), searchItem);
            }else{
                return this.searchFunc(new ArrayList<>(dataList.subList(0, mid)), searchItem);
            }
        }
    }

    public static void main(String[] args){
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }

        Collections.sort(testData);
        System.out.println(testData);

        BinarySearch bSearch = new BinarySearch();
        System.out.println(bSearch.searchFunc(testData, 18));
    }
}

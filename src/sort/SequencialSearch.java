package sort;

import java.util.ArrayList;

/**
 * 순차 탐색을 수행하는 클래스
 */
public class SequencialSearch {
    public int searchFunc(ArrayList<Integer> dataList, Integer searchItem){
        for(Integer data : dataList){
            if(data != null && data == searchItem) return data;
        }
        return -1;
    }

    public static void main(String[] args){
        ArrayList<Integer> testData = new ArrayList<>();

        for(int a = 0; a<10; a++){
            testData.add((int) (Math.random() * 100));
        }

        System.out.println(testData);
        SequencialSearch sSearch = new SequencialSearch();
        System.out.println(sSearch.searchFunc(testData, 99));
    }
}
package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 병합 정렬을 수행하기 전 주어진 Array를 2분할 하는 클래스 입니다.
 */
public class MergeSort {
//    public void SplitFunc(List<Integer> tarList){
//        // Size <= 1 일때는 별 다른 작업을 안해도 되므로 인자 바로 출력
//        if(tarList.size() <= 1) System.out.println(tarList);
//        // 주어진 인자 사이즈의 절반 값을 구함
//        int medium = tarList.size()/2;
//        // 위에서 구한 절반 값을 이용해 Array를 2개로 분할함
//        List<Integer> leftArrays = tarList.subList(0, medium);
//        List<Integer> rightArrays = tarList.subList(medium, tarList.size());
//        // 분할한 리스트 toString 출력
//        System.out.println(leftArrays + "\n" + rightArrays);
//    }

    public ArrayList<Integer> mergeFunc(ArrayList<Integer> leftList, ArrayList<Integer> rightList){
        ArrayList<Integer> result = new ArrayList<>();
        int leftPoint = 0;
        int rightPoint = 0;

        // case1: left/right 둘 다 있을 때
        while(leftList.size() > leftPoint && rightList.size() > rightPoint){
            if(leftList.get(leftPoint) > rightList.get(rightPoint)){
                result.add(rightList.get(rightPoint));
                rightPoint++;
            }else{
                result.add(leftList.get(leftPoint));
                leftPoint++;
            }
        }

        // case2: right 데이터가 없을 때
        while(leftList.size() > leftPoint){
            result.add(leftList.get(leftPoint));
            leftPoint++;
        }

        // case3: left 데이터가 없을 때
        while(rightList.size() > rightPoint){
            result.add((rightList.get(rightPoint)));
            rightPoint++;
        }

        return result;
    }

    public ArrayList<Integer> mergeSplitFunc(ArrayList<Integer> dataList) {
        if (dataList.size() <= 1) {
            return dataList;
        }
        int medium = dataList.size() / 2;

        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();

        leftArr = this.mergeSplitFunc(new ArrayList<Integer>(dataList.subList(0, medium)));
        rightArr = this.mergeSplitFunc(new ArrayList<Integer>(dataList.subList(medium, dataList.size())));

        return this.mergeFunc(leftArr, rightArr);
    }


    public static void main(String[] args){
        ArrayList<Integer> testData = new ArrayList<>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }
        MergeSort splitArray = new MergeSort();
        System.out.println(splitArray.mergeSplitFunc(testData));
    }
}

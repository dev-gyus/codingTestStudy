package sort.mergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 병합 정렬을 수행하기 전 주어진 Array를 2분할 하는 클래스 입니다.
 */
public class SplitArray {
    public void SplitFunc(List<Integer> tarList){
        // Size <= 1 일때는 별 다른 작업을 안해도 되므로 인자 바로 출력
        if(tarList.size() <= 1) System.out.println(tarList);
        // 주어진 인자 사이즈의 절반 값을 구함
        int medium = tarList.size()/2;
        // 위에서 구한 절반 값을 이용해 Array를 2개로 분할함
        List<Integer> leftArrays = tarList.subList(0, medium);
        List<Integer> rightArrays = tarList.subList(medium, tarList.size());
        // 분할한 리스트 toString 출력
        System.out.println(leftArrays + "\n" + rightArrays);

    }
    public static void main(String[] args){
        SplitArray splitArray = new SplitArray();
        splitArray.SplitFunc(Arrays.asList(2,3,1,5,6,9,8,7));
    }
}

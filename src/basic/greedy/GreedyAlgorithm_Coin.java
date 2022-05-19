package basic.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 탐욕 알고리즘 수행 클래스
 * 베이스 마인드: 가장 단위가 큰 동전부터 최대공약수를 구한 뒤
 * 가장 단위가 작은 동전까지 큰 동전에서 계산하고 남은 나머지의 최대공약수를 구하면 가장 적은 동전 개수가 나올것이다.
 */
// 지불해야 하는 값이 4720원 일 때 1원 50원 100원, 500원 동전으로 동전의 수가 가장 적게 지불하시오.
public class GreedyAlgorithm_Coin {
    public void coinFunc(Integer price, ArrayList<Integer> coinList) {
        if(coinList.size() <= 0 || price <= 0) {
            System.out.println("0");
        }else {
            Collections.sort(coinList);
            int remain = price;
            int[] coinAmount = new int[coinList.size()];
            for (int a = coinList.size() - 1; a >= 0; a--) {
                coinAmount[a] = remain / coinList.get(a);
                remain = remain % coinList.get(a);
                System.out.println(coinList.get(a) + " 원: " + coinAmount[a] + " 개");
            }
        }
    }

    public static void main(String[] args){
        GreedyAlgorithm_Coin gObject = new GreedyAlgorithm_Coin();
        ArrayList<Integer> coinList = new ArrayList<Integer>(Arrays.asList(500, 100, 50, 1));
        gObject.coinFunc(4720, coinList);
    }
}

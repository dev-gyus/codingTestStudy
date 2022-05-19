package basic.remind.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// 총 지불해야하는 값이 4720원, 지불할 동전의 종류는 1원, 50원, 100원, 500원 일때 동전의 수를 가장 적게 지불하는 방법
public class GreedyCoin {
    public HashMap<Integer, Integer> coinFunc(Integer price, ArrayList<Integer> coins){
        HashMap<Integer, Integer>  payment = new HashMap<>();
        // 동전 액수로 내림차순 정렬
        coins.sort((o1, o2) -> o2 - o1);
        // 남은 액수 선언
        Integer remain = price;

        for (Integer coin : coins) {
            int mok = remain / coin;
            remain = remain - (coin * mok);
            payment.put(coin, mok);
        }
        return payment;
    }

    public static void main(String[] args){
        GreedyCoin gObject = new GreedyCoin();
        ArrayList<Integer> coinList = new ArrayList<Integer>(Arrays.asList(500, 100, 50, 1));
        System.out.println(gObject.coinFunc(5000, coinList));
    }
}

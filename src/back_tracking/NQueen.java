package back_tracking;

import java.util.ArrayList;
import java.util.Objects;

// 대표적인 백트래킹문제
// promising, pruning(가지치기) 기법을 모두 사용한다
public class NQueen {
    public boolean isAvailable(ArrayList<Integer> candidate, Integer currentCol){
        Integer currentRow = candidate.size();
        // promising 방법. 모든 후보군을 탐색해서 답이 될 수 있는지 여부를 판단
        for(int index = 0; index < currentRow; index++){
            if(Objects.equals(candidate.get(index), currentCol)
                    || (Math.abs(candidate.get(index) - currentCol)) == currentRow - index){
                return false;
            }
        }
        return true;
    }

    public void dfsFunc(Integer n, Integer currentRow, ArrayList<Integer> currentCandidate){
        if(Objects.equals(n, currentRow)){
            System.out.println(currentCandidate);
            return;
        }
        // pruning 방법. 특정 경로를 기준으로 탐색해서 답이 되지않으면 그 기준을 후보군에서 제거해서 다시 탐색하지 않도록 쳐냄
        for(int index = 0; index < n; index ++){
            if(this.isAvailable(currentCandidate, index)){
                currentCandidate.add(index);
                this.dfsFunc(n, currentRow + 1, currentCandidate);
                // queen에 해당하는 위치가 딱히 없을경우 루트노드로 다시 돌아가서 판단하기위해 후보군을 remove함
                currentCandidate.remove(currentCandidate.size() - 1);
            }
        }
    }
    public static void main(String[] args){
        NQueen nQueen = new NQueen();
        nQueen.dfsFunc(4,0,new ArrayList<>());
    }
}

package others;

import java.util.Arrays;

public class Solution {
    public static int findRunnerUp(int [] scores){
        int maior = Integer.MIN_VALUE;
        int segundoMaior = Integer.MIN_VALUE;
        int size = scores.length;
        if(size == 0)
            return -1;
        // Arrays.sort(scores);
        for(int i = 0; i < size ; i++){
            if(scores[i] > maior) {
                segundoMaior = maior;
                maior = scores[i];
            }

        }
        return segundoMaior;
    }
    public static void main(String [] args){
        int [] scores = new int[]{3,2,6,6,5,8,10};
        System.out.println(findRunnerUp(scores));
    }
}

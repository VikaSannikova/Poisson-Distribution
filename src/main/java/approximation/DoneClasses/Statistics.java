package approximation.DoneClasses;

import java.util.ArrayList;

public class Statistics {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Integer arr1[] = new Integer[5];
        for(int i = 0; i < 5; i++){
            arr.add(i);
            arr1[i] = i;
        }
        System.out.println(arr.get(1));
        //arr.get(1)++;
        arr1[1]++;
    }
}

package approximation.DoneClasses;

import java.util.ArrayList;

public class Statistics {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            arr.add(i);
        }
        System.out.println(arr);
        ArrayList<Integer> a = new ArrayList<>(arr);
        System.out.println(a);
        arr.add(3);
        a.add(0);
        System.out.println(arr);
        System.out.println(a);
    }
}

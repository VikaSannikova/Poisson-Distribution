//package example;
//
//import java.util.*;
//
//public class Class {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[] arr = new int[n];
//        for(int i = 0; i < n; i++){
//            arr[i] = in.nextInt();
//        }
//        int[] res = new int[n];
//        TreeSet<Integer> tmp = new TreeSet<>();
//        tmp.add(0);
//        for(int i = 0; i < n; i++){
//            res[i] = 0;
//            tmp.add(arr[i]);
//            ArrayList<Integer> t = new ArrayList<>(tmp.headSet(arr[i]));
//            for(int j = 0; j <t.size(); j++){
//                res[i] += t.get(j);
//            }
//
//        }
//        for(int k : res){
//            System.out.print(k + " ");
//        }
//    }
//}

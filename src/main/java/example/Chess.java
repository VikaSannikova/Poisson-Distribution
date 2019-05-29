//package example;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class Chess {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        ArrayList<Coord> horses = new ArrayList<>();
//        for(int i = 0; i< m ;i++){
//            int x = in.nextInt();
//            int y = in.nextInt();
//            horses.add(new Coord(x-1,y-1));
//        }
//        ArrayList<Coord> close = new ArrayList<>(horses);
//        for(int i = 0; i < horses.size(); i++ ){
//            Coord c = new Coord(horses.get(i).x+2, horses.get(i).y+1);
//            if ((horses.get(i).x+2>=0)&&(horses.get(i).y+1>=0)&& (horses.get(i).x+2<n)&&(horses.get(i).y+1<n)&& (!(close.contains(c)))){
//                close.add(c);
//            }
//            c = new Coord(horses.get(i).x+1, horses.get(i).y+2);
//            if((horses.get(i).x+1>=0)&&(horses.get(i).y+2>=0)&&
//               (horses.get(i).x+1<n)&&(horses.get(i).y+2<n)&&
//               (!(close.contains(c)))){
//                close.add(c);
//            }
//            c = new Coord(horses.get(i).x+2, horses.get(i).y-1);
//            if((horses.get(i).x+2>=0)&&(horses.get(i).y-1>=0)&&
//               (horses.get(i).x+2<n)&&(horses.get(i).y-1<n)&&
//               (!(close.contains(c)))){
//                close.add(c);
//            }
//            c = new Coord(horses.get(i).x+1, horses.get(i).y-2);
//            if((horses.get(i).x+1>=0)&&(horses.get(i).y-2>=0)&&
//               (horses.get(i).x+1<n)&&(horses.get(i).y-2<n)&&
//               (!(close.contains(c)))){
//                close.add(c);
//            }
//            c = new Coord(horses.get(i).x-1, horses.get(i).y-2);
//            if((horses.get(i).x-1>=0)&&(horses.get(i).y-2>=0)&&
//               (horses.get(i).x-1<n)&&(horses.get(i).y-2<n)&&
//               (!(close.contains(c)))){
//                close.add(c);
//            }
//            c = new Coord(horses.get(i).x-2, horses.get(i).y-1);
//            if((horses.get(i).x-2>=0)&&(horses.get(i).y-1>=0)&&
//               (horses.get(i).x-2<n)&&(horses.get(i).y-1<n)&&
//               (!(close.contains(c)))){
//                close.add(c);
//            }
//            c = new Coord(horses.get(i).x-2, horses.get(i).y+1);
//            if((horses.get(i).x-2>=0)&&(horses.get(i).y+1>=0)&&
//               (horses.get(i).x-2<n)&&(horses.get(i).y+1<n)&&
//               (!(close.contains(c)))){
//                close.add(c);
//            }
//            c = new Coord(horses.get(i).x-1, horses.get(i).y+2);
//            if((horses.get(i).x-1>=0)&&(horses.get(i).y+2>=0)&&
//               (horses.get(i).x-1<n)&&(horses.get(i).y+2<n)&&
//               (!(close.contains(c)))) {
//                close.add(c);
//            }
//        }
//        System.out.println(n*n - close.size());
//
//    }
//
//}
//
//class Coord{
//    int x;
//    int y;
//
//    public Coord(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Coord coord = (Coord) o;
//
//        if (x != coord.x) return false;
//        return y == coord.y;
//    }
//}
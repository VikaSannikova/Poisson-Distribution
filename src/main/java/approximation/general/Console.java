package approximation.general;

import java.util.Scanner;

public class Console {
    int numOfPoints;
    double T;
    Intensity intensity;

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    public double getT() {
        return T;
    }

    public void setT(double t) {
        T = t;
    }

    public Intensity getIntensity() {
        return intensity;
    }

    public void setIntensity(Intensity intensity) {
        this.intensity = intensity;
    }


    public Console() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество точек разрыва: ");
        numOfPoints = in.nextInt();
        System.out.println("Введите время работы сигнала: ");
        T = in.nextDouble();
        this.intensity = new Intensity(numOfPoints,T);
    }

    public void output(){
        System.out.println("Массив времен: ");
        for (Double elem: intensity.getDeltatimes()) {
            System.out.print(elem + " ");
        }
        System.out.println();
        System.out.println("Промежутки времени");
        for(int i = 0;i<intensity.getIntervals().length;i++){
            System.out.print("["+intensity.getIntervals()[i].getLeft()+"; "+intensity.getIntervals()[i].getRight()+") ");
        }
        System.out.println();
        System.out.println("Интенсивности: ");
        for (Double res: intensity.getIntensities()){
            System.out.print(res+" ");
        }
        System.out.println();
        System.out.println("Среднее значение интенсивностей: ");
        System.out.println(intensity.getAverage());
        System.out.println();
    }
}

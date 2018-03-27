package PoisDist;

import cern.jet.random.Exponential;
import cern.jet.random.Poisson;
import cern.jet.random.Uniform;
import cern.jet.random.engine.DRand;
import cern.jet.random.engine.RandomEngine;

import java.util.ArrayList;

import static java.lang.Math.exp;

public class DP_Main {

    public static int poisson(double lambda) {
        int n = 0;
        double v = exp(-1.0/lambda),
                y =1.0;
        while(y>=v){
            y=Math.random();
            n++;
        }
        return n;
    }

/*    public static int poissonExp(double rate) { //wtf

        RandomEngine engine = new DRand();
        Exponential exponential = new Exponential(1.0, engine);
        int k = -1;
        double s = 0;
        do {
            s += exponential.nextDouble();
            ++k;
        } while (s < rate);
        return k;
    }*/

/*    double expRateInv;
    void setup(double rate){
        expRateInv = exp(-rate);
}

    double PoissonKnuth(double) {
        RandomEngine engine = new DRand();
        Uniform uniform = new Uniform(engine);
        double k = 0, prod = Uniform(0, 1);
        while (prod > expRateInv) {
            prod *= Uniform(0, 1);
            ++k;
        }
        return k;
    }*/

/*    RandomEngine engine = new DRand();
    double lambda = 3;
    Poisson poisson = new Poisson(50.0, engine);
    int poissonObs = poisson.nextInt();*/

    public static void main(String[] args) {

        RandomEngine engine = new DRand();
        Poisson poisson = new Poisson(100.0, engine);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i=0;i<100;++i) {
            int poissonObs = poisson.nextInt();
            arr.add(poissonObs);
            //System.out.print(poissonObs+" ");
        }
        //System.out.println();

        System.out.println("DefPois: " + arr.toString());

        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        for(int i=0;i<100;++i) {
            int res = poisson(1.0);
            arr1.add(res);
            //System.out.print(res+" ");

        }
        //System.out.println();
        System.out.println("Pois:    "+ arr1.toString());

        /*for(int i=0;i<100;++i) {
            int poissonObs = poissonExp(1.0);
            System.out.print(poissonObs + " ");
        }
        System.out.println();*/
    }
}


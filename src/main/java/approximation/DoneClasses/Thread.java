package approximation.DoneClasses;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class Thread {
    int queue;
    ArrayList<Integer> queues = new ArrayList<>();
    double T;
    double lambda;
    Formula formula;
    //Expression expression;
    int numOfPoints;

    public Thread(int queue, double t, double lambda, Formula formula, int numOfPoints) {
        this.queue = queue;
        ArrayList<Integer> queues = new ArrayList<>();
        T = t;
        this.lambda = lambda;
        this.formula = formula;
        this.numOfPoints = numOfPoints;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public void setT(double t) {
        T = t;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    public int getQueue() {
        return queue;
    }

    public double getT() {
        return T;
    }

    public double getLambda() {
        return lambda;
    }

    public Formula getFormula() {
        return formula;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void createQueue(){
        Intensity intensity = new Intensity(getNumOfPoints(), getT(), getFormula());
        ArrayList<Integer> queues = new ArrayList<>();
        queues.add(getQueue());
        System.out.println( queues.get(0));
        for(int i = 1; i < intensity.getIntervals().size(); i++){ //здесь можно выводить 1 число а не массив очередей
            PoissonDistriburion pd = new PoissonDistriburion(getLambda(),intensity.getIntervals().get(i-1).getLength());
            queues.add(Math.max(0,queues.get(i-1)+ pd.returnNum(pd.u, pd.intervals) - (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1))));
            System.out.println(queues.get(i-1)+"+"+ pd.returnNum(pd.u, pd.intervals) +"-"+ (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1)));
        }
        this.queue = queues.get(queues.size()-1);
        //return queues.get(queues.size()-1);
    }
    public void createQueueWithoutService(){
        PoissonDistriburion pd = new PoissonDistriburion(getLambda(), getT());
        System.out.print(getQueue()+"+"+pd.returnNum(pd.u, pd.intervals)+"=");
        setQueue(getQueue()+pd.returnNum(pd.u, pd.intervals));
        System.out.println(getQueue());
    }



    public static void main(String[] args) {
        Formula formula = new Formula("x^2");
        Thread thread = new Thread(2, 10, 1, formula, 9);
//        Intensity intensity = new Intensity(thread.getNumOfPoints(), thread.getT(), thread.getFormula());
//        ArrayList<Integer> queues = new ArrayList<>();
//        queues.add(thread.getQueue());
//            for(int i = 1; i < intensity.getIntervals().size(); i++){
//                PoissonDistriburion pd = new PoissonDistriburion(thread.getLambda(),intensity.getIntervals().get(i-1).getLength());
//                queues.add(Math.max(0,queues.get(i-1)+ pd.returnNum(pd.u, pd.intervals) - (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1))));
//                System.out.println(queues.get(i-1)+"+"+ pd.returnNum(pd.u, pd.intervals) +"-"+ (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1)));
//        }
        thread.createQueue();
        //System.out.println("QUEUES: " + thread.getQueues());
        System.out.println("FINAL: "+ thread.queue);


    }

}

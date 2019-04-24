package approximation.DoneClasses;

import java.util.ArrayList;

public class Thread {
    int id;
    int queue;
    //ArrayList<Integer> queues = new ArrayList<>();
    double lambda;
    double greenTime;

    Formula formula;
    double yellowTime;
    //Expression expression;
    int numOfPoints;
    double avgIntens;

    public Thread(int id, int queue, double lambda, double greenTime, Formula formula, double yellowTime, int numOfPoints) {
        this.id = id;
        this.queue = queue;
        this.lambda = lambda;
        this.greenTime = greenTime;
        this.formula = formula;
        this.yellowTime = yellowTime;
        this.numOfPoints = numOfPoints;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(double yellowTime) {
        this.yellowTime = yellowTime;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public void setGreenTime(double greenTime) {
        this.greenTime = greenTime;
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

    public double getGreenTime() {
        return greenTime;
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

    public double getAvgIntens() {
        return avgIntens;
    }

    public void setAvgIntens(double avgIntens) {
        this.avgIntens = avgIntens;
    }

    public void createQueue(){
        Intensity intensity = new Intensity(getNumOfPoints(), getGreenTime(), getFormula());
        avgIntens = intensity.getAverage();
        ArrayList<Integer> queues = new ArrayList<>();
        queues.add(getQueue());
        System.out.println( queues.get(0));
        for(int i = 1; i < intensity.getIntervals().size()+1; i++){ //здесь можно выводить 1 число а не массив очередей
            PoissonDistriburion pd = new PoissonDistriburion(getLambda(),intensity.getIntervals().get(i-1).getLength());
            queues.add(Math.max(0,queues.get(i-1)+ pd.returnNum(pd.u, pd.intervals) - (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1))));
            System.out.println(queues.get(i-1)+"+"+ pd.returnNum(pd.u, pd.intervals) +"-"+ (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1)));
        }
        this.queue = queues.get(queues.size()-1);
        //return queues.get(queues.size()-1);
    }
    public void createQueueWithoutService(){
        PoissonDistriburion pd = new PoissonDistriburion(getLambda(), getGreenTime());
        System.out.print(getQueue()+"+"+pd.returnNum(pd.u, pd.intervals)+"=");
        setQueue(getQueue()+pd.returnNum(pd.u, pd.intervals));
        System.out.println(getQueue());
    }



    public static void main(String[] args) {
        Formula formula = new Formula("x^2");
        Thread thread = new Thread(1,2, 1, 10, formula, 10, 9);
//        Intensity intensity = new Intensity(thread.getNumOfPoints(), thread.getGreenTime(), thread.getFormula());
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
        System.out.println("AVG INTENSITY: "+ thread.avgIntens);
        Thread thread1 = new Thread(2,2,1,10,new Formula("x"),10,9);
        thread1.createQueue();System.out.println("FINAL: "+ thread1.queue);
        System.out.println("AVG INTENSITY: "+ thread1.avgIntens);


    }

}

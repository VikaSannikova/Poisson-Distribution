package approximation.DoneClasses;

import java.util.ArrayList;
import java.util.Arrays;

public class Thread implements Cloneable {
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
    int realDoneApps;
    int maxDoneApps;
    int[] realDoneAppsStats;

    public Thread(int id, int queue, double lambda, double greenTime, Formula formula, double yellowTime, int numOfPoints) {
        this.id = id;
        this.queue = queue;
        this.lambda = lambda;
        this.greenTime = greenTime;
        this.formula = formula;
        this.yellowTime = yellowTime;
        this.numOfPoints = numOfPoints;
        realDoneAppsStats = new int [numOfPoints+1];
        for(int i = 0; i < numOfPoints+1;i++){
            realDoneAppsStats[i]=0;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Thread clone = (Thread)super.clone();
        clone.realDoneAppsStats = (int[])realDoneAppsStats.clone();
        return clone;
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

    public int getRealDoneApps() {
        return realDoneApps;
    }

    public void setRealDoneApps(int realDoneApps) {
        this.realDoneApps = realDoneApps;
    }

    public void setZeroQueue(){
        setQueue(0);
    }

    public ArrayList<Integer> getMaxDoneAppsDeltas(){
        ArrayList<Integer> maxDoneAppsDeltas = new ArrayList<>(); //за каждый дальта t хранит максимально вохможное число обслуженных заявок
        Intensity intensity = new Intensity(getNumOfPoints(), getGreenTime(), getFormula());
        for(int i = 1; i < intensity.getIntervals().size()+1; i++){
            maxDoneAppsDeltas.add((int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1)));
        }
        return maxDoneAppsDeltas;
    }


    public Integer getMaxDoneApps(){
        ArrayList<Integer> maxDoneApps = new ArrayList<>(); //ха каждый дальта t хранит максимально вохможное число обслуженных заявок
        Intensity intensity = new Intensity(getNumOfPoints(), getGreenTime(), getFormula());
        for(int i = 1; i < intensity.getIntervals().size()+1; i++){
            maxDoneApps.add((int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1)));
        }
        this.maxDoneApps = 0;
        for(int elem : maxDoneApps){
            this.maxDoneApps+=elem;
        }
        //System.out.println("Могло быть обслужено " + this.maxDoneApps +" заявок");
        return this.maxDoneApps;
    }

    public void setMaxDoneApps(int maxDoneApps) {
        this.maxDoneApps = maxDoneApps;
    }

    public int[] getRealDoneAppsStats() {
        return realDoneAppsStats;
    }

    public void setRealDoneAppsStats(int[] realDoneAppsStats) {
        this.realDoneAppsStats = realDoneAppsStats;
    }

    public void createQueue(){
        Intensity intensity = new Intensity(getNumOfPoints(), getGreenTime(), getFormula());
        avgIntens = intensity.getAverage();
        ArrayList<Integer> queues = new ArrayList<>();
        ArrayList<Integer> realDoneApps = new ArrayList<>(); //будет хранить за каждый дельта t реально обслуженные заявки

        ArrayList<Integer> maxDoneApps = new ArrayList<>(); //ха каждый дальта t хранит максимально вохможное число обслуженных заявок
        queues.add(getQueue());
        System.out.println( queues.get(0));
        for(int i = 1; i < intensity.getIntervals().size()+1; i++){ //здесь можно выводить 1 число а не массив очередей
            PoissonDistriburion pd = new PoissonDistriburion(getLambda(),intensity.getIntervals().get(i-1).getLength());
            queues.add(Math.max(0,queues.get(i-1)+ pd.returnNum(pd.u, pd.intervals) - (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1))));
            realDoneApps.add(Math.min(queues.get(i-1)+ pd.returnNum(pd.u, pd.intervals),(int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1))));
            realDoneAppsStats[i-1] = 0;
            realDoneAppsStats[i-1] = realDoneApps.get(i-1); //+=

            maxDoneApps.add((int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1)));
            System.out.println(queues.get(i-1)+"+"+ pd.returnNum(pd.u, pd.intervals) +"-"+ (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1)));
        }
        this.queue = queues.get(queues.size()-1);
        this.realDoneApps = 0;
        for(int elem : realDoneApps){
            this.realDoneApps+=elem;
        }
        this.maxDoneApps = 0;
        for(int elem : maxDoneApps){

            this.maxDoneApps+=elem;
        }
        System.out.println(realDoneApps);
        System.out.println("Обслужилось " + this.realDoneApps + " заявок");
        System.out.println("Могло быть обслужено " + this.maxDoneApps +" заявок");
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
        thread.createQueue();
        thread.createQueue();
        //System.out.println("QUEUES: " + thread.getQueues());
        System.out.println("FINAL: "+ thread.queue);
        System.out.println("AVG INTENSITY: "+ thread.avgIntens);
//        Thread thread1 = new Thread(2,2,1,10,new Formula("x"),10,9);
//        thread1.createQueue();System.out.println("FINAL: "+ thread1.queue);
//        System.out.println("AVG INTENSITY: "+ thread1.avgIntens);
        System.out.println("Реально обслуженные заявки по дельтам: " + Arrays.toString(thread.realDoneAppsStats));
        System.out.println("В сумме " + thread.realDoneApps);


    }

}

package approximation.DoneClasses;

import java.util.ArrayList;
import java.util.Arrays;

public class Loop {
    int numOfThreads;
    ArrayList<Integer> queues = new ArrayList<>();
    ArrayList<Double> greenTimes = new ArrayList<>();
    ArrayList<Double> lambdas = new ArrayList<>();
    ArrayList<String> formulas = new ArrayList<>();
    ArrayList<Double> redTimes = new ArrayList<>();

    ArrayList<Thread> threads = new ArrayList<>();

    public void setNumOfThreads(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    public void setQueues(ArrayList<Integer> queues) {
        this.queues = queues;
    }

    public void setGreenTimes(ArrayList<Double> greenTimes) {
        this.greenTimes = greenTimes;
    }

    public void setLambdas(ArrayList<Double> lambdas) {
        this.lambdas = lambdas;
    }

    public void setFormulas(ArrayList<String> formulas) {
        this.formulas = formulas;
    }

    public void setRedTimes(ArrayList<Double> redTimes) {
        this.redTimes = redTimes;
    }

    public Loop(int numOfThreads) {
        Integer queue[] = {1,2,3};
        this.setQueues(new ArrayList<Integer>(Arrays.asList(queue)));
        Double times[] = {10.0,10.0,10.0};
        this.setGreenTimes(new ArrayList<Double>(Arrays.asList(times)));
        Double lambdas[] = {1.0,1.0,1.0};
        this.setLambdas(new ArrayList<Double>(Arrays.asList(lambdas)));
        String strings[] = {"x^2", "x^2", "x^2"};
        this.setFormulas(new ArrayList<String>(Arrays.asList(strings)));

        threads.add(new Thread(queues.get(0), greenTimes.get(0), this.lambdas.get(0), new Formula(formulas.get(0)),9));
        threads.get(0).createQueue();
        System.out.println("Queue Thread 1 after 1GL: " + threads.get(0).getQueue());
        //считаем сколько придет по 2 потоку за время работы 1
        threads.add(new Thread(queues.get(1), greenTimes.get(0), this.lambdas.get(1), new Formula(formulas.get(1)), 9));
        threads.get(1).createQueueWithoutService();
        //считаем сколько придет по 3 потоку за время работы 1
        threads.add(new Thread(queues.get(2), greenTimes.get(0), this.lambdas.get(2), new Formula(formulas.get(2)), 9));
        threads.get(2).createQueueWithoutService();
    }

    public static void main(String[] args) {
        Loop loop = new Loop(3);
    }
}

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
    double yellowTime;

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
        this.yellowTime = 3.0;
        Integer queue[] = {1,2,3};
        this.setQueues(new ArrayList<Integer>(Arrays.asList(queue)));
        Double times[] = {10.0,10.0,10.0};
        this.setGreenTimes(new ArrayList<Double>(Arrays.asList(times)));
        Double lambdas[] = {0.01,1.0,1.0}; //интенсивность для 1 потока очеь мала
        this.setLambdas(new ArrayList<Double>(Arrays.asList(lambdas)));
        String strings[] = {"x^2", "x^2", "x^2"};
        this.setFormulas(new ArrayList<String>(Arrays.asList(strings)));
        Double timesRed[] = {5.0,5.0,5.0};
        this.setRedTimes(new ArrayList<Double>(Arrays.asList(timesRed)));

        threads.add(new Thread(queues.get(0), greenTimes.get(0), this.lambdas.get(0), new Formula(formulas.get(0)),9));
        threads.get(0).createQueue();
        System.out.println("Queue Thread 1 after 1GL: " + threads.get(0).getQueue());
        //считаем сколько придет по 2 потоку за время работы 1
        threads.add(new Thread(queues.get(1), greenTimes.get(0), this.lambdas.get(1), new Formula(formulas.get(1)), 9));
        threads.get(1).createQueueWithoutService();
        //считаем сколько придет по 3 потоку за время работы 1
        threads.add(new Thread(queues.get(2), greenTimes.get(0), this.lambdas.get(2), new Formula(formulas.get(2)), 9));
        threads.get(2).createQueueWithoutService();

        System.out.println("Queues: "+threads.get(0).getQueue() +" " + threads.get(1).getQueue() +" "+threads.get(2).getQueue());

        //работа за 1 КС
        threads.get(0).setT(redTimes.get(0));
        threads.get(0).createQueueWithoutService();
        threads.get(1).setT(redTimes.get(0));
        threads.get(1).createQueueWithoutService();
        threads.get(2).setT(redTimes.get(0));
        threads.get(2).createQueueWithoutService();
        System.out.println("Queues: "+threads.get(0).getQueue() +" " + threads.get(1).getQueue() +" "+threads.get(2).getQueue());

        //работа за 2 ЗС
        System.out.println("Queues after 2GL");
        threads.get(0).setT(greenTimes.get(1));
        threads.get(0).createQueueWithoutService();
        threads.get(1).setT(greenTimes.get(1));
        threads.get(1).createQueue();
        System.out.println("Queue Thread 2 after 2GL: " + threads.get(1).getQueue());
        threads.get(2).setT(greenTimes.get(1));
        threads.get(2).createQueueWithoutService();
        System.out.println("Queues: "+threads.get(0).getQueue() +" " + threads.get(1).getQueue() +" "+threads.get(2).getQueue());

        //работа за 2 КС
        threads.get(0).setT(redTimes.get(1));
        threads.get(0).createQueueWithoutService();
        threads.get(1).setT(redTimes.get(1));
        threads.get(1).createQueueWithoutService();
        threads.get(2).setT(redTimes.get(1));
        threads.get(2).createQueueWithoutService();
        System.out.println("Queues: "+threads.get(0).getQueue() +" " + threads.get(1).getQueue() +" "+threads.get(2).getQueue());

        threads.get(0).setT(redTimes.get(1));
        threads.get(0).createQueueWithoutService();
        threads.get(1).setT(redTimes.get(1));
        threads.get(1).createQueueWithoutService();

        threads.get(2).setT(greenTimes.get(2));
        threads.get(2).createQueue();

        //нужен цикл while пока нет заявок по 1 потоку нужно
        if(threads.get(0).getQueue()==0){
            threads.get(2).setT(yellowTime);
            //переходим к работе 1 потока
        }



    }

    public static void main(String[] args) {
        Loop loop = new Loop(3);
    }
}

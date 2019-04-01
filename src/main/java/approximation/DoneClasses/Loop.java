package approximation.DoneClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
        setNumOfThreads(numOfThreads);
        this.yellowTime = 10.0;
        Integer queue[] = {1,2,3,4,5};
        this.setQueues(new ArrayList<Integer>(Arrays.asList(queue)));
        Double times[] = {10.0,10.0,10.0,10.0,10.0};
        this.setGreenTimes(new ArrayList<Double>(Arrays.asList(times)));
        Double lambdas[] = {0.01,1.0,1.0,1.0,10.0}; //интенсивность для 1 потока очень мала
        this.setLambdas(new ArrayList<Double>(Arrays.asList(lambdas)));
        String strings[] = {"x^2", "x^2", "x^2", "x^2", "x^2"};
        this.setFormulas(new ArrayList<String>(Arrays.asList(strings)));
        Double timesRed[] = {5.0,5.0,5.0,5.0,5.0};
        this.setRedTimes(new ArrayList<Double>(Arrays.asList(timesRed)));

        for(int i = 0; i<numOfThreads; i++){
            this.threads.add(new Thread(this.queues.get(i), greenTimes.get(0), this.lambdas.get(i), new Formula(formulas.get(i)), 9));
        }
    }

    public void start( int numOfIterations){
        Integer arr[] = {1,2,3,4,5};
        for(int p = 0; p < numOfIterations; p++) {
            int k = 0;
            //работа до последнего зеленого света
            for (int i = 0; i < numOfThreads - 1; i++) {
                for (int j = 0; j < numOfThreads; j++) {
                    if (j == k) {
                        threads.get(j).createQueue();
                        System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + "после зеленого света" + i);
                    } else {
                        threads.get(j).createQueueWithoutService();
                        System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + "после зеленого света" + i);
                    }
                    this.threads.get(j).setT(redTimes.get(i)); //?
                    this.threads.get(j).createQueueWithoutService();
                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + " после красного света" + i);
                    this.threads.get(j).setT(greenTimes.get(i + 1));//?
                }
                k++;
                System.out.println("______________________");
            }
            //работа за последний зеленый свет
            for (int i = 0; i < numOfThreads - 1; i++) {
                threads.get(i).createQueueWithoutService();
                System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после ЗС света" + (numOfThreads - 1));
                threads.get(i).setT(yellowTime);
            }

            threads.get(numOfThreads - 1).createQueue();
            System.out.println("Очередь " + (numOfThreads - 1) + " потока = " + threads.get(numOfThreads - 1).getQueue() + " после ЗС света" + (numOfThreads - 1));
            threads.get(numOfThreads - 1).setT(yellowTime);
            //работа за желтый свет
            int s = 0;
            while (threads.get(0).getQueue() == 0) {
                for (int i = 0; i < numOfThreads - 1; i++) {
                    this.threads.get(i).createQueueWithoutService();
                    System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после желтого света" + s);
                }
                this.threads.get(this.numOfThreads - 1).createQueue();
                System.out.println("Очередь " + (numOfThreads - 1) + " потока = " + threads.get(numOfThreads - 1).getQueue() + " после желтого света" + s);
                s++;
            }
            //работа за последний красный свет
            for (int i = 0; i < numOfThreads; i++) {
                this.threads.get(i).setT(redTimes.get(numOfThreads - 1));
                this.threads.get(i).createQueueWithoutService();
                System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после красного света" + (numOfThreads - 1));
                this.threads.get(i).setT(greenTimes.get(0));
            }
            System.out.print("Очереди на новое начало цикла: ");
            for (int i = 0; i < numOfThreads; i++) {
                System.out.print(threads.get(i).getQueue() + ", ");
            }
            System.out.println();
            for(int i = 0; i < numOfThreads;i++){
                arr[i]+=threads.get(i).getQueue();
                System.out.println("ДЛЯ СРЕДНЕЙ ОЧЕРЕДИ:" + arr[i]);
            }
        }
        System.out.println("ХВОСТ___________________________________________________");
        for(int i = 0;i <numOfThreads;i++){
            threads.get(i).setQueue(0);
        }
        int k = 0;
        //работа до последнего зеленого света
        for (int i = 0; i < numOfThreads - 1; i++) {
            for (int j = 0; j < numOfThreads; j++) {
                if (j > k) { //>
                    threads.get(j).createQueueWithoutService();
                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + "после зеленого света" + i);
                    this.threads.get(j).setT(redTimes.get(i)); //?
                    this.threads.get(j).createQueueWithoutService();
                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + " после красного света" + i);
                    this.threads.get(j).setT(greenTimes.get(i + 1));//?
                }
            }
            k++;
            System.out.println("______________________");
        }
        for(int i = 0; i < numOfThreads;i++){
            arr[i]+=threads.get(i).getQueue();
            System.out.println("ДЛЯ СРЕДНЕЙ ОЧЕРЕДИ:" + arr[i]);
            System.out.println("СРЕДНЯЯ ОЧЕРЕДЬ ЗА "+ numOfIterations + " ИТЕРИЦИЙ: " + ((double)arr[i]/numOfIterations));
        }

    }

    public void check(){ //спросить на счет проверки, не меняются ли условия
        System.out.println();
        double sumTime = 0;
        for( double elem : greenTimes){ //суммируем ЗС
            sumTime+=elem;
        }
        for(double elem: redTimes){ //суммируем ЖС, нет дополнительного ЖС
            sumTime+=elem;
        }
        for(int i = 0; i < numOfThreads; i++){
            if(threads.get(i).getLambda()*sumTime-threads.get(i).getAvgIntens()*greenTimes.get(i)<0){ // умножаем на ЗС от потока
                System.out.println(threads.get(i).getLambda()+"*"+sumTime+"-"+threads.get(i).getAvgIntens()+"*"+greenTimes.get(i));
                System.out.println("Thread "+ i +" is valid");
            }
        }
    }

    public static void main(String[] args) {
        Loop loop = new Loop(3);
        loop.start(2);
        System.out.println();
        loop.check();

    }
}

package approximation.DoneClasses;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Loop {
    int numOfThreads;
    ArrayList<Integer> queues = new ArrayList<>();
    ArrayList<Double> greenTimes = new ArrayList<>();
    ArrayList<Double> lambdas = new ArrayList<>();
    ArrayList<String> formulas = new ArrayList<>();
    ArrayList<Double> redTimes = new ArrayList<>();
    double yellowTime;
    ArrayList<Thread> threads = new ArrayList<>();
    Double param;

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

    public Double getParam() {
        return param;
    }

    public void setParam(Double param) {
        this.param = param;
    }


    public Loop(int numOfThreads, ArrayList<Integer> queue) {
        setNumOfThreads(numOfThreads);
        this.yellowTime = 10.0;
        //Integer queue[] = {1,2,3,4,5};
        this.setQueues(queue);
        Double times[] = {10.0,10.0,10.0,10.0,10.0};
        this.setGreenTimes(new ArrayList<Double>(Arrays.asList(times)));
        Double lambdas[] = {0.01,1.0,1.0,1.0,10.0}; //интенсивность для 1 потока очень мала
        this.setLambdas(new ArrayList<Double>(Arrays.asList(lambdas)));
        String strings[] = {"x^2", "x^2", "x^2", "x^2", "x^2"};
        this.setFormulas(new ArrayList<String>(Arrays.asList(strings)));
        Double timesRed[] = {5.0,5.0,5.0,5.0,5.0};
        this.setRedTimes(new ArrayList<Double>(Arrays.asList(timesRed)));

        for(int i = 0; i<numOfThreads; i++){
            this.threads.add(new Thread(1,this.queues.get(i), this.lambdas.get(i),greenTimes.get(0), new Formula(formulas.get(i)), redTimes.get(i),9));
        }
        this.param = 0.0;
    }

    public void start( int numOfIterations){
        Integer arr[] = new Integer[queues.size()];
        arr = queues.toArray(arr);
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
                    this.threads.get(j).setGreenTime(redTimes.get(i)); //?
                    this.threads.get(j).createQueueWithoutService();
                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + " после красного света" + i);
                    this.threads.get(j).setGreenTime(greenTimes.get(i + 1));//?
                }
                k++;
                System.out.println("______________________");
            }
            //работа за последний зеленый свет
            for (int i = 0; i < numOfThreads - 1; i++) {
                threads.get(i).createQueueWithoutService();
                System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после ЗС света" + (numOfThreads - 1));
                threads.get(i).setGreenTime(yellowTime);
            }

            threads.get(numOfThreads - 1).createQueue();
            System.out.println("Очередь " + (numOfThreads - 1) + " потока = " + threads.get(numOfThreads - 1).getQueue() + " после ЗС света" + (numOfThreads - 1));
            threads.get(numOfThreads - 1).setGreenTime(yellowTime);
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
                this.threads.get(i).setGreenTime(redTimes.get(numOfThreads - 1));
                this.threads.get(i).createQueueWithoutService();
                System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после красного света" + (numOfThreads - 1));
                this.threads.get(i).setGreenTime(greenTimes.get(0));
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
                    this.threads.get(j).setGreenTime(redTimes.get(i)); //?
                    this.threads.get(j).createQueueWithoutService();
                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + " после красного света" + i);
                    this.threads.get(j).setGreenTime(greenTimes.get(i + 1));//?
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
        double sumLambda = 0.0;
        for(int i = 0; i< numOfThreads; i++){
            param+=threads.get(i).getLambda()*arr[i]/numOfIterations;
            sumLambda+=threads.get(i).getLambda();
        }
        param/=sumLambda;
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
        ArrayList<Integer> zeroQueue = new ArrayList<Integer>();
        zeroQueue.add(0);
        zeroQueue.add(0);
        zeroQueue.add(0);
        ArrayList<Integer> infQueue = new ArrayList<Integer>();
        infQueue.add(1);
        infQueue.add(75);
        infQueue.add(75);
        Loop loop = new Loop(3, zeroQueue);
        loop.start(100);
        System.out.println("ggjbkb");
//        loop.check();
//        System.out.println();
//        System.out.println("НОВЫЙ ЦИКЛ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        Loop loop1 = new Loop(3, infQueue);
//        loop1.start(100);
//        loop1.check();
//        System.out.println();
//
//        Double delta = 0.1;
//        System.out.println("Левая часть: " + Math.abs(loop.getParam()-loop1.getParam()));
//        System.out.println("Правая часть: " + loop1.getParam()*delta);
    }
}

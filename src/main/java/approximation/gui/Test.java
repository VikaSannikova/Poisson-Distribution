//package approximation.gui;
//
//import approximation.DoneClasses.Formula;
//import approximation.DoneClasses.Loop;
//import approximation.DoneClasses.Thread;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Test {
//
//    ArrayList<Thread> threads = new ArrayList<>();
//    Double yellowTime;
//    Double param;
//
//
//
//    public Test(ArrayList<Thread> threads,  Double yellowTime) {
//       this.threads = threads;
//       this.yellowTime = yellowTime;
//       this.param = 0.0;
//    }
//
//    public void start( int numOfIterations){
//        Integer arr[] = new Integer[threads.size()];
//        ArrayList<Double> greenTimes = new ArrayList<>();
//        ArrayList<Double> yellowTimes = new ArrayList<>();
//        for(int i = 0; i < threads.size(); i++){
//            arr[i] = threads.get(i).getQueue();
//            greenTimes.add(threads.get(i).getGreenTime());
//            yellowTimes.add(threads.get(i).getYellowTime());
//        }
//        int numOfThreads = threads.size();
//        for(int p = 0; p < numOfIterations; p++) {
//            int k = 0;
//            //работа до последнего зеленого света
//            for (int i = 0; i < numOfThreads - 1; i++) {
//                for (int j = 0; j < numOfThreads; j++) {
//                    if (j == k) {
//                        threads.get(j).createQueue();
//                        System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + "после зеленого света" + i);
//                    } else {
//                        threads.get(j).createQueueWithoutService();
//                        System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + "после зеленого света" + i);
//                    }
//                    this.threads.get(j).setGreenTime(yellowTimes.get(i)); //?
//                    this.threads.get(j).createQueueWithoutService();
//                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + " после красного света" + i);
//                    this.threads.get(j).setGreenTime(greenTimes.get(i+1));//? берет значение уже измененное!!!!!!!
//                }
//                k++;
//                System.out.println("______________________");
//            }
//            //работа за последний зеленый свет
//            for (int i = 0; i < numOfThreads - 1; i++) {
//                threads.get(i).createQueueWithoutService();
//                System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после ЗС света" + (numOfThreads - 1));
//                threads.get(i).setGreenTime(yellowTime);
//            }
//
//            threads.get(numOfThreads - 1).createQueue();
//            System.out.println("Очередь " + (numOfThreads - 1) + " потока = " + threads.get(numOfThreads - 1).getQueue() + " после ЗС света" + (numOfThreads - 1));
//            threads.get(numOfThreads - 1).setGreenTime(yellowTime);
//            //работа за желтый свет
//            int s = 0;
//            while (threads.get(0).getQueue() == 0) {
//                for (int i = 0; i < numOfThreads - 1; i++) {
//                    this.threads.get(i).createQueueWithoutService();
//                    System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после желтого света" + s);
//                }
//                this.threads.get(numOfThreads - 1).createQueue();
//                System.out.println("Очередь " + (numOfThreads - 1) + " потока = " + threads.get(numOfThreads - 1).getQueue() + " после желтого света" + s);
//                s++;
//            }
//            //работа за последний красный свет
//            for (int i = 0; i < numOfThreads; i++) {
//                this.threads.get(i).setGreenTime(yellowTimes.get(numOfThreads-1));
//                this.threads.get(i).createQueueWithoutService();
//                System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после красного света" + (numOfThreads - 1));
//                this.threads.get(i).setGreenTime(greenTimes.get(0));
//            }
//            System.out.print("Очереди на новое начало цикла: ");
//            for (int i = 0; i < numOfThreads; i++) {
//                System.out.print(threads.get(i).getQueue() + ", ");
//            }
//            System.out.println();
//            for(int i = 0; i < numOfThreads;i++){
//                arr[i]+=threads.get(i).getQueue();
//                System.out.println("ДЛЯ СРЕДНЕЙ ОЧЕРЕДИ:" + arr[i]);
//            }
//        }
//        System.out.println("ХВОСТ___________________________________________________");
//        for(int i = 0;i <numOfThreads;i++){
//            threads.get(i).setQueue(0);
//        }
//        int k = 0;
//        //работа до последнего зеленого света
//        for (int i = 0; i < numOfThreads - 1; i++) {
//            for (int j = 0; j < numOfThreads; j++) {
//                if (j > k) { //>
//                    threads.get(j).createQueueWithoutService();
//                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + "после зеленого света" + i);
//                    this.threads.get(j).setGreenTime(yellowTimes.get(i)); //?
//                    this.threads.get(j).createQueueWithoutService();
//                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + " после красного света" + i);
//                    this.threads.get(j).setGreenTime(greenTimes.get(i+1));//?
//                }
//            }
//            k++;
//            System.out.println("______________________");
//        }
//        for(int i = 0; i < numOfThreads;i++){
//            arr[i]+=threads.get(i).getQueue();
//            System.out.println("ДЛЯ СРЕДНЕЙ ОЧЕРЕДИ:" + arr[i]);
//            System.out.println("СРЕДНЯЯ ОЧЕРЕДЬ ЗА "+ numOfIterations + " ИТЕРИЦИЙ: " + ((double)arr[i]/numOfIterations));
//        }
//        double sumLambda = 0.0;
//        for(int i = 0; i< numOfThreads; i++){
//            param+=threads.get(i).getLambda()*arr[i]/numOfIterations;
//            sumLambda+=threads.get(i).getLambda();
//        }
//        param/=sumLambda;
//    }
//
//    public void check(){ //спросить на счет проверки, не меняются ли условия
//        System.out.println();
//        double sumTime = 0;
//        for(Thread thr : threads){
//            sumTime+=(thr.getGreenTime()+thr.getYellowTime());
//        }
//        for(int i = 0; i < threads.size() ; i++){
//            if(threads.get(i).getLambda()*sumTime-threads.get(i).getAvgIntens()*threads.get(i).getGreenTime()<0){ // умножаем на ЗС от потока
//                System.out.println(threads.get(i).getLambda()+"*"+sumTime+"-"+threads.get(i).getAvgIntens()+"*"+threads.get(i).getGreenTime());
//                System.out.println("Thread "+ i +" is valid");
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        List<Thread> list = new ArrayList<>();
//        list.add(new Thread(1, 0, 0.01,10.0 ,new Formula("x^2"), 5, 9));
//        list.add(new Thread(2, 0, 1.0,10.0 ,new Formula("x^2"), 5, 9));
//        list.add(new Thread(3, 0, 1.0,10.0 ,new Formula("x^2"), 5, 9));
//        Test loop = new Test((ArrayList<Thread>) list,  10.0);
//        loop.start(1000);
//        loop.check();
//
//    }
//}

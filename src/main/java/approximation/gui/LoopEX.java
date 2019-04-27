//package approximation.gui;
//
//import approximation.DoneClasses.Formula;
//import approximation.DoneClasses.Loop;
//import approximation.DoneClasses.Thread;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class LoopEX {
//
//    List<Thread> threads = new ArrayList<>();
//    Double yellowTimePETLYA;
//    Double param;
//
//    public Double getParam() {
//        return param;
//    }
//
//    public void setParam(Double param) {
//        this.param = param;
//    }
//
//
//    public LoopEX(List<Thread> threads, Double yellowTime) {
//        this.threads = threads;
//        this.yellowTimePETLYA = yellowTime;
//        this.param = 0.0;
//    }
//
//    public Double getTime(){
//        double time = 0.0;
//        for(Thread thr : threads){
//            time += thr.getGreenTime()+thr.getYellowTime();
//        }
//        return time;
//    }
//
//    public void start( int numOfIterations){
//        Integer arr[] = new Integer[threads.size()];
//        for(int i = 0; i < threads.size(); i++){
//            arr[i] = threads.get(i).getQueue();
//        }
//        double time = getTime();
//        //arr = queues.toArray(arr);
//        for(int p = 0; p < numOfIterations; p++) {
//            int k = 0;
//            //работа до последнего зеленого света
//            for (int i = 0; i < threads.size() - 1; i++) {
//                for (int j = 0; j < threads.size(); j++) {
//                    if (j == k) {
//                        threads.get(j).createQueue();
//                        System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + "после зеленого света" + i);
//                    } else {
//                        threads.get(j).createQueueWithoutService();
//                        System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + "после зеленого света" + i);
//                    }
//                    this.threads.get(j).setGreenTime(threads.get(i).getYellowTime()); //?
//                    this.threads.get(j).createQueueWithoutService();
//                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + " после красного света" + i);
//                    this.threads.get(j).setGreenTime(threads.get(i+1).getGreenTime());//?
//                }
//                k++;
//                System.out.println("______________________");
//            }
//            //работа за последний зеленый свет
//            for (int i = 0; i < threads.size() - 1; i++) {
//                threads.get(i).createQueueWithoutService();
//                System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после ЗС света" + (threads.size() - 1));
//                threads.get(i).setGreenTime(yellowTimePETLYA);
//            }
//
//            threads.get(threads.size() - 1).createQueue();
//            System.out.println("Очередь " + (threads.size() - 1) + " потока = " + threads.get(threads.size() - 1).getQueue() + " после ЗС света" + (threads.size() - 1));
//            threads.get(threads.size() - 1).setGreenTime(yellowTimePETLYA);
//            //работа за желтый свет
//            int s = 0;
//            while (threads.get(0).getQueue() == 0) {
//                for (int i = 0; i < threads.size() - 1; i++) {
//                    this.threads.get(i).createQueueWithoutService();
//                    System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после желтого света" + s);
//                }
//                this.threads.get(this.threads.size() - 1).createQueue();
//                System.out.println("Очередь " + (threads.size() - 1) + " потока = " + threads.get(threads.size() - 1).getQueue() + " после желтого света" + s);
//                s++;
//            }
//            //работа за последний красный свет
//            for (int i = 0; i < threads.size(); i++) {
//                this.threads.get(i).setGreenTime(threads.get(threads.size()-1).getYellowTime());
//                this.threads.get(i).createQueueWithoutService();
//                System.out.println("Очередь " + i + " потока = " + threads.get(i).getQueue() + " после красного света" + (threads.size() - 1));
//                this.threads.get(i).setGreenTime(threads.get(0).getGreenTime());
//            }
//            System.out.print("Очереди на новое начало цикла: ");
//            for (int i = 0; i < threads.size(); i++) {
//                System.out.print(threads.get(i).getQueue() + ", ");
//            }
//            System.out.println();
//            for(int i = 0; i < threads.size();i++){
//                arr[i]+=threads.get(i).getQueue();
//                System.out.println("ДЛЯ СРЕДНЕЙ ОЧЕРЕДИ:" + arr[i]);
//            }
//        }
//        System.out.println("ХВОСТ___________________________________________________");
//        for(int i = 0;i <threads.size();i++){
//            threads.get(i).setQueue(0);
//        }
//        int k = 0;
//        //работа до последнего зеленого света
//        for (int i = 0; i < threads.size() - 1; i++) {
//            for (int j = 0; j < threads.size(); j++) {
//                if (j > k) { //>
//                    threads.get(j).createQueueWithoutService();
//                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + "после зеленого света" + i);
//                    this.threads.get(j).setGreenTime(threads.get(i).getYellowTime()); //?
//                    this.threads.get(j).createQueueWithoutService();
//                    System.out.println("Очередь " + j + " потока = " + threads.get(j).getQueue() + " после красного света" + i);
//                    this.threads.get(j).setGreenTime(threads.get(i+1).getGreenTime());//?
//                }
//            }
//            k++;
//            System.out.println("______________________");
//        }
//        for(int i = 0; i < threads.size() ;i++){
//            arr[i]+=threads.get(i).getQueue();
//            System.out.println("ДЛЯ СРЕДНЕЙ ОЧЕРЕДИ:" + arr[i]);
//            System.out.println("СРЕДНЯЯ ОЧЕРЕДЬ ЗА "+ numOfIterations + " ИТЕРИЦИЙ: " + ((double)arr[i]/numOfIterations));
//        }
//        double sumLambda = 0.0;
//        for(int i = 0; i< threads.size(); i++){
//            param+=threads.get(i).getLambda()*arr[i]/numOfIterations;
//            sumLambda+=threads.get(i).getLambda();
//        }
//        param/=sumLambda;
//    }
//
//    public void check(){ //спросить на счет проверки, не меняются ли условия
//        System.out.println();
//        double sumTime = 0;
//        for( Thread thr : threads){
//            sumTime += thr.getGreenTime() + thr.getYellowTime();
//        }
////        for( double elem : greenTimes){ //суммируем ЗС
////            sumTime+=elem;
////        }
////        for(double elem: redTimes){ //суммируем ЖС, нет дополнительного ЖС
////            sumTime+=elem;
////        }
//        for(int i = 0; i < threads.size(); i++){
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
//        LoopEX loop = new LoopEX(list,  10.0);
//        loop.start(100);
//        loop.check();
//    }
//}

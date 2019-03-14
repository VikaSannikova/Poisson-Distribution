//package approximation.gui;
//
//import approximation.general.Console;
//import approximation.DoneClasses.Intensity;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
//import org.jfree.chart.renderer.xy.XYSplineRenderer;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.*;
//
//public class GUI {
//    public static void main(String[] args) {
//
//        Console console = new Console();
//        console.output();
//
//        //Intensity intensity = new Intensity(console.getNumOfPoints(),console.getT());
//
//        final XYSeriesCollection dataset = new XYSeriesCollection();
//        final XYSeriesCollection datasetSpline = new XYSeriesCollection();
//
//        JFrame frame = new JFrame("Кусочно постоянная аппроксимация");
//        JPanel dataPanel = new JPanel(new GridLayout(6,2,0,5));
//        JLabel inputFunctionLabel = new JLabel("Функция:");
//        JTextField inputFunction = new JTextField();
//        JLabel timeLabel = new JLabel("T:");
//        final JTextField time = new JTextField();
//        final JLabel numOfPointsLabel = new JLabel("Кол-во точек разрыва: ");
//        final JTextField numOfPoints = new JTextField();
//        JButton goPlot = new JButton("Построить графики");
//        JButton goPlot2 = new JButton("Новые графики");
//        JButton goPlot3 = new JButton("Реальные графики");
//        JButton goPlot4 = new JButton("Новые реальные графики");
//        JButton clear = new JButton("Очистить");
//
//        dataPanel.add(inputFunctionLabel);
//        dataPanel.add(inputFunction);
//        dataPanel.add(timeLabel);
//        dataPanel.add(time);
//        dataPanel.add(numOfPointsLabel);
//        dataPanel.add(numOfPoints);
//        dataPanel.add(goPlot);
//        dataPanel.add(goPlot2);
//        dataPanel.add(goPlot3);
//        dataPanel.add(goPlot4);
//        dataPanel.add(clear);
//
//
//        final Intensity[] intensities = new Intensity[4];
//
//        goPlot.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                intensities[0] = new Intensity(Integer.parseInt(numOfPoints.getText()),Double.parseDouble(time.getText()));
//                XYSeries base = new XYSeries("Функция");
//                XYSeries base1 = new XYSeries("Аппроксимация");
//                XYSeries base2 = new XYSeries("Средняя");
//                for(int i = 0; i < intensities[0].getDeltatimes().length; i++){
//                    for(double j = 0; j <= intensities[0].getDeltatimes()[i]; j+=0.1){
//                        base1.add(intensities[0].getIntervals()[i].getLeft()+j, intensities[0].getIntensities()[i]);
//                    }
//                }
//                for(double i = 0; i < Double.parseDouble(time.getText()); i+=0.1){ //??
//                    base.add(i,Math.pow(i,2));
//                    base2.add(i, intensities[0].getAverage());
//                }
//                dataset.addSeries(base);
//                dataset.addSeries(base1);
//                dataset.addSeries(base2);
//            }
//        });
//
//        goPlot2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                intensities[1] = intensities[0];
//                double avg = intensities[0].getAverage();
//                double sum = avg*console.getT();
//                Random rand = new Random();
//                Double[] numbers = new Double[console.getNumOfPoints()+2];
//                double randsum = 0;
//                System.out.println("длина "+numbers.length);
//                for(int i = 0;i < numbers.length; i++){
//                    numbers[i] = (double) rand.nextInt(82);
//                    randsum+=numbers[i];
//                    System.out.print(" "+numbers[i]);
//                }
//                Arrays.sort(numbers, Collections.reverseOrder());
//                double var = 0;
//                for (int i = 0; i < numbers.length;i++) {
//                    numbers[i] = numbers[i]/randsum*sum;
//                    var+=numbers[i];
//                    System.out.print(" "+numbers[i]);
//                }
//                System.out.println();
//                System.out.println("sum "+sum + " randsum "+randsum+ " var "+var);
//                XYSeries b1 = new XYSeries("Func");
//                for(int i = 0; i <numbers.length;i++){
//                    b1.add(i,numbers[i]);
//                }
//
//
//                intensities[1].setIntervals(intensities[0].getIntervals());
//                double[] ints = new double[intensities[0].getLength()];
//                for(int i = 0; i< intensities[0].getLength(); i++){
//                    ints[i]=numbers[i];
//                }
//
//                intensities[1].setIntensities(ints);
//                intensities[1].setAverage(sum/intensities[1].getIntensities().length);
//
//
//                XYSeries b2 = new XYSeries("app");
//                XYSeries b3 = new XYSeries("avg");
//                for(int i = 0; i < intensities[1].getDeltatimes().length;i++){
//                    for(double j = 0; j < intensities[1].getDeltatimes()[i]; j+=0.1){
//                        b2.add(intensities[1].getIntervals()[i].getLeft()+j,intensities[1].getIntensities()[i]);
//                    }
//                }
//                for(double i = 0; i < Double.parseDouble(time.getText());i+=0.1){ //??
//                    b3.add(i, intensities[1].getAverage());
//                }
//                datasetSpline.addSeries(b1);
//                dataset.addSeries(b2);
//                dataset.addSeries(b3);
//
//
//
//
//                System.out.println("Длина массива: "+ numbers.length);
//                System.out.println("Интенсивности: ");
//                double summary = 0;
//                for (int i = 0;i<numbers.length;i++){
//                    System.out.print(numbers[i]+"! ");
//                    summary+=numbers[i];
//                }
//                System.out.println();
//                System.out.println("сумма "+summary+" Новое среднее значение "+ avg);
//                System.out.println("______________________________________________________________");
//            }
//        });
//
//        goPlot3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                intensities[2] = new Intensity(Integer.parseInt(numOfPoints.getText()),Double.parseDouble(time.getText()));
//                double avg = 6;
//                double sum = avg*console.getT();
//                Random rand = new Random();
//                Double[] numbers = new Double[Integer.parseInt(numOfPoints.getText())+2];
//                double randsum = 0;
//                System.out.println("длина "+numbers.length);
//                for(int i = 0;i < numbers.length; i++){
//                    numbers[i] = (double) rand.nextInt(82);
//                    randsum+=numbers[i];
//                    System.out.print(" "+numbers[i]);
//                }
//                Arrays.sort(numbers);
//                double var = 0;
//                for (int i = 0; i < numbers.length;i++) {
//                    numbers[i] = numbers[i]/randsum*sum;
//                    var+=numbers[i];
//                    System.out.print(" "+numbers[i]);
//                }
//                System.out.println();
//                System.out.println("sum "+sum + " randsum "+randsum+ " var "+var);
//                XYSeries b4 = new XYSeries("Func1");
//                for(int i = 0; i <numbers.length;i++){
//                    b4.add(i,numbers[i]);
//                }
//
//
//                //intensities[2].setIntervals(intensities[0].getIntervals());
//                double[] ints = new double[intensities[2].getLength()];
//                for(int i = 0; i< intensities[2].getLength(); i++){
//                    ints[i]=numbers[i];
//                }
//
//                intensities[2].setIntensities(ints);
//                intensities[2].setAverage(sum/intensities[2].getIntensities().length);
//
//
//                XYSeries b5 = new XYSeries("app1");
//                XYSeries b6 = new XYSeries("avg1");
//                for(int i = 0; i < intensities[2].getDeltatimes().length;i++){
//                    for(double j = 0; j < intensities[2].getDeltatimes()[i]; j+=0.1){
//                        b5.add(intensities[2].getIntervals()[i].getLeft()+j,intensities[2].getIntensities()[i]);
//                    }
//                }
//                for(double i = 0; i < Double.parseDouble(time.getText());i+=0.1){ //??
//                    b6.add(i, intensities[2].getAverage());
//                }
//                datasetSpline.addSeries(b4);
//                dataset.addSeries(b5);
//                dataset.addSeries(b6);
//
//
//
//
//
//                System.out.println("Длина массива: "+ numbers.length);
//                System.out.println("Интенсивности: ");
//                double summary = 0;
//                for (int i = 0;i<numbers.length;i++){
//                    System.out.print(numbers[i]+"! ");
//                    summary+=numbers[i];
//                }
//                System.out.println();
//                System.out.println("сумма "+summary+" Новое среднее значение "+ avg);
//                System.out.println("_________________________________________________");
//            }
//        });
//
//        goPlot4.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                intensities[3] = new Intensity(Integer.parseInt(numOfPoints.getText()),Double.parseDouble(time.getText()));
//                double avg = intensities[2].getAverage();
//                double sum = avg*console.getT();
//                Random rand = new Random();
//                Double[] numbers = new Double[Integer.parseInt(numOfPoints.getText())+2];
//                double randsum = 0;
//                System.out.println("длина "+numbers.length);
//                for(int i = 0;i < numbers.length; i++){
//                    numbers[i] = (double) rand.nextInt(82);
//                    randsum+=numbers[i];
//                    System.out.print(" "+numbers[i]);
//                }
//                Arrays.sort(numbers, Collections.reverseOrder());
//                double var = 0;
//                for (int i = 0; i < numbers.length;i++) {
//                    numbers[i] = numbers[i]/randsum*sum;
//                    var+=numbers[i];
//                    System.out.print(" "+numbers[i]);
//                }
//                System.out.println();
//                System.out.println("sum "+sum + " randsum "+randsum+ " var "+var);
//                XYSeries b7 = new XYSeries("Func2");
//                for(int i = 0; i <numbers.length;i++){
//                    b7.add(i,numbers[i]);
//                }
//
//
//                //intensities[2].setIntervals(intensities[0].getIntervals());
//                double[] ints = new double[intensities[3].getLength()];
//                for(int i = 0; i< intensities[3].getLength(); i++){
//                    ints[i]=numbers[i];
//                }
//
//                intensities[3].setIntensities(ints);
//                intensities[3].setAverage(sum/intensities[3].getIntensities().length);
//
//
//                XYSeries b8 = new XYSeries("app2");
//                XYSeries b9 = new XYSeries("avg2");
//                for(int i = 0; i < intensities[3].getDeltatimes().length;i++){
//                    for(double j = 0; j < intensities[3].getDeltatimes()[i]; j+=0.1){
//                        b8.add(intensities[3].getIntervals()[i].getLeft()+j,intensities[3].getIntensities()[i]);
//                    }
//                }
//                for(double i = 0; i < Double.parseDouble(time.getText());i+=0.1){ //??
//                    b9.add(i, intensities[3].getAverage());
//                }
//                datasetSpline.addSeries(b7);
//                dataset.addSeries(b8);
//                dataset.addSeries(b9);
//
//
//
//
//
//                System.out.println("Длина массива: "+ numbers.length);
//                System.out.println("Интенсивности: ");
//                double summary = 0;
//                for (int i = 0;i<numbers.length;i++){
//                    System.out.print(numbers[i]+"! ");
//                    summary+=numbers[i];
//                }
//                System.out.println();
//                System.out.println("сумма "+summary+" Новое среднее значение "+ avg);
//            }
//        });
//
//        clear.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dataset.removeAllSeries();
//                datasetSpline.removeAllSeries();
//            }
//        });
//
//
//        JFreeChart chart = ChartFactory.createXYLineChart(
//                "",
//                "x",
//                "y",
//                dataset,
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                true);
//        chart.setBackgroundPaint(Color.WHITE);
//        final XYPlot plot = chart.getXYPlot();
//        plot.setDataset(1,datasetSpline);
//        plot.setBackgroundPaint(new Color(232, 232, 232));
//
//        plot.setDomainGridlinePaint(Color.gray);
//        plot.setRangeGridlinePaint (Color.gray);
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
////        renderer.setSeriesPaint(0,Color.red);
////        renderer.setSeriesPaint(1,Color.blue);
////        renderer.setSeriesPaint  (2, Color.orange);
////        renderer.setSeriesPaint  (3, Color.green);
//        renderer.setSeriesPaint  (4, Color.MAGENTA);
//
//        //renderer.setSeriesPaint  (5, Color.green);
//        //renderer.setSeriesPaint  (6, Color.MAGENTA);
//
//        XYSplineRenderer renderer1 = new XYSplineRenderer();
//        renderer1.setSeriesPaint(0,Color.black);
//        renderer.setSeriesPaint(0,Color.red);
//        renderer.setSeriesPaint(1,Color.pink);
//
//
//        renderer1.setSeriesPaint(1,Color.orange);
//        renderer.setSeriesPaint  (2, Color.green);
//        renderer.setSeriesPaint  (3, Color.blue);
//
//        plot.setRenderer(0,renderer);
//        plot.setRenderer(1,renderer1);
//
//        frame.add(dataPanel, BorderLayout.WEST);
//        frame.getContentPane().add(new ChartPanel(chart));
//        frame.setSize(800,500);
//        frame.show();
//
//
//    }
//}
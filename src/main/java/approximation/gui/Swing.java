package approximation.gui;

import approximation.DoneClasses.Formula;
import approximation.DoneClasses.Loop;
import approximation.DoneClasses.Thread;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.CategoryStepRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Swing extends JFrame{
    public Swing(){

        super("Имитационная модель");
        setSize(1000,550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        SetOfThreads tableModel = new SetOfThreads();
        JTable table = new JTable(tableModel);
        JScrollPane jScrollPane = new JScrollPane(table);

        JPanel dataPanel = new JPanel(new GridLayout(1,2,0,5));
        JPanel loopPanel = new JPanel(new GridLayout(3,2,0,5));
        loopPanel.add(new JLabel("Время петли"));
        JTextField loopTimeTF = new JTextField();
        loopPanel.add(loopTimeTF);
        loopPanel.add(new JLabel("Число итераций"));
        JTextField iterNumTF = new JTextField();
        loopPanel.add(iterNumTF);
        loopPanel.add(new JLabel("Время обслуживания одной заявки"));
        JTextField timeTF = new JTextField();
        loopPanel.add(timeTF);
        dataPanel.add(loopPanel);
        JPanel resPanel = new JPanel(new GridLayout(4,1,0,5));
            JLabel text = new JLabel("Выходные данные");
            resPanel.add(text);
            JLabel stat = new JLabel();
            resPanel.add(stat);
            JLabel petlya = new JLabel();
            resPanel.add(petlya);
            JLabel ochered = new JLabel();
            resPanel.add(ochered);
        dataPanel.add(resPanel);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.7; //??
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(jScrollPane,gbc);
        gbc.weighty = 0.3; //??
        gbc.gridy = 1;
        panel.add(dataPanel, gbc);
        add(panel,BorderLayout.CENTER);

        boolean[] isDelete = {false};
        final int[] rowNumber = new int[1];
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final int row = table.rowAtPoint(e.getPoint());
                rowNumber[0] = row;
                if (isDelete[0]) {
                    tableModel.deleteThread(row);
                    isDelete[0] = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JPanel grid= new JPanel(new GridLayout(6,1,0,5));
        JButton add = new JButton("Добавить");
        JButton delete = new JButton("Удалить");
        JButton start = new JButton("Старт");
        //JButton loop = new JButton("Цикл");
        JButton draw = new JButton("Отрисовать графики стационарности");
        JButton drawstats = new JButton("Статистический ряд");
        JButton drawdeltastats = new JButton("Склейка статистических рядов");


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setTitle("Новый поток");
                dialog.setModal(true);
                dialog.setSize(350, 250);
                JPanel panel = new JPanel(new GridLayout(6,2,0,5));

                panel.add(new JLabel("Номер потока"));
                JTextField idTF = new JTextField();
                panel.add(idTF);
                panel.add(new JLabel("Очередь"));
                JTextField queueTF = new JTextField();
                panel.add(queueTF);
                panel.add(new JLabel("Интенсивность поступления"));
                JTextField lambdaTF = new JTextField();
                panel.add(lambdaTF);
                panel.add(new JLabel("Зеленый свет"));
                JTextField greenTimeTF = new JTextField();
                panel.add(greenTimeTF);
                panel.add(new JLabel("Интенсивность обслуживания"));
                JTextField formulaTF = new JTextField();
                panel.add(formulaTF);
                panel.add(new JLabel("Желтый свет"));
                JTextField yellowTimeTF = new JTextField();
                panel.add(yellowTimeTF);
                dialog.add(panel);

                JButton ok = new JButton("OK");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //проверка на ошибку ввода
                        if(tableModel.getRowCount() > 0){
                            tableModel.addThread(new Thread(Integer.valueOf(idTF.getText()),
                                    Integer.valueOf(queueTF.getText()),
                                    Double.valueOf(lambdaTF.getText()),
                                    Double.valueOf(greenTimeTF.getText()),
                                    new Formula(formulaTF.getText()),
                                    Double.valueOf(yellowTimeTF.getText()),
                                    9));
                        }
                        if(tableModel.getRowCount() == 0)
                        {
                            tableModel.addThread(new Thread(Integer.valueOf(idTF.getText()),
                                    Integer.valueOf(queueTF.getText()),
                                    Double.valueOf(lambdaTF.getText()),
                                    Double.valueOf(greenTimeTF.getText()),
                                    new Formula(formulaTF.getText()),
                                    Double.valueOf(yellowTimeTF.getText()),
                                    0));
                        }

                        dialog.dispose();
                    }
                });
                dialog.add(ok,BorderLayout.SOUTH);
                dialog.setVisible(true);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDelete[0] = true;
            }
        });

//        start.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String str = "";
//                tableModel.getThread(rowNumber[0]).createQueue();
//                int q = tableModel.getThread(rowNumber[0]).getQueue();
//                double avg = tableModel.getThread(rowNumber[0]).getAvgIntens();
//                str = Integer.toString(q) +" "+ Double.toString(avg);
//                text.setText(str);
//            }
//        });

        final Loop[] loopGeneral = new Loop[1];

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loopGeneral[0] = new Loop((ArrayList<Thread>) tableModel.getThreads(), Double.parseDouble(loopTimeTF.getText()), Double.parseDouble(timeTF.getText()));
                try {
                    loopGeneral[0].start(Integer.parseInt(iterNumTF.getText()));
                } catch (CloneNotSupportedException e1) {
                    e1.printStackTrace();
                }
                String str = "";
                int iter = loopGeneral[0].getIter();
                int yellowIter = loopGeneral[0].getYellowIter();
                ArrayList<Double> avgQueues = loopGeneral[0].getAvgQueues();
                str = "Стационарность достигнута: " + Integer.toString(iter);
                stat.setText(str);
                str = "Число заходов в петлю: " + Integer.toString(yellowIter);
                petlya.setText(str);
                str = "Очереди: " + avgQueues;
                ochered.setText(str);
                loopGeneral[0].check();
            }
        });

        final XYSeriesCollection dataset = new XYSeriesCollection();
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XYSeries []xySeries = new XYSeries[2+tableModel.getRowCount()];
                xySeries[0] = new XYSeries("Нулевая");
                xySeries[1] = new XYSeries("Наибольшая");
                for(int i = 2; i < xySeries.length; i++){
                    String str = (i-1) + " очередь";
                    xySeries[i] = new XYSeries(str);
                }
                for(int i = 0; i < Integer.parseInt(iterNumTF.getText()); i++){
                    xySeries[0].add(i,loopGeneral[0].getSintZero().get(i));
                    xySeries[1].add(i,loopGeneral[0].getSintInf().get(i));
                    for(int j = 2; j < xySeries.length; j++){
                        xySeries[j].add(i, loopGeneral[0].getThreadsQ().get(j-2).get(i));
                    }
                }
                dataset.addSeries(xySeries[0]);
                dataset.addSeries(xySeries[1]);
                for(int j = 2; j < xySeries.length; j++){
                    dataset.addSeries(xySeries[j]);
                }
                JFreeChart chart = ChartFactory.createXYLineChart(
                        "",
                        "x",
                        "y",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        true);
                chart.setBackgroundPaint(Color.WHITE);
                XYPlot plot = chart.getXYPlot();
                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

                plot.setRenderer(0,renderer);
                JDialog dialog = new JDialog();
                dialog.setTitle("Визуализация достижения стационарности");
                dialog.setModal(true);
                dialog.setSize(500, 400);
                dialog.add(new ChartPanel(chart));
                dialog.setVisible(true);
                dataset.removeAllSeries();
            }
        });

        drawstats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for(int k = 0; k < tableModel.getRowCount(); k++){
                    for(int i = 0; i < loopGeneral[0].getStats().get(k).size();i++) {
                        String number = String.valueOf(k);
                        String str = String.valueOf(i);
                        dataset.setValue(loopGeneral[0].getStats().get(k).get(i), number, str);
                    }
                }
                JFreeChart chart = ChartFactory.createBarChart(
                        "",
                        "Число обслуженных заявок",
                        "Частота",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        true
                );

                org.jfree.chart.plot.CategoryPlot plot = chart.getCategoryPlot();
                plot.setRangeGridlinePaint(Color.BLACK);
                plot.setBackgroundPaint(Color.lightGray);
                ChartFrame frame = new ChartFrame("Статистический ряд за целый зеленый свет", chart);
                frame.setVisible(true);
                frame.setSize(1000,200);
            }
        });

        drawdeltastats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for(int k = 0; k < tableModel.getRowCount(); k++){
                    for(int i = 0; i < loopGeneral[0].getAllDeltaThreadStat().get(k).size(); i++){
                        String number = String.valueOf(k);
                        String str = String.valueOf(i);
                        dataset.setValue(loopGeneral[0].getAllDeltaThreadStat().get(k).get(i), number, str);
                    }
                }
                JFreeChart chart = ChartFactory.createBarChart(
                        "Склейка статистических рядов за дельты",
                        "Число обслуженных заявок",
                        "Частота",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        true
                );

                CategoryPlot plot = chart.getCategoryPlot();
                plot.setRangeGridlinePaint(Color.BLACK);
                plot.setBackgroundPaint(Color.lightGray);
                ChartFrame frame = new ChartFrame("Склейка статистических рядов за дельты", chart);
                frame.setVisible(true);
                frame.setSize(1000,200);
            }
        });

        grid.add(add);
        grid.add(delete);
        grid.add(start);
        grid.add(draw);
        grid.add(drawstats);
        grid.add(drawdeltastats);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(grid);
        add(flow, BorderLayout.EAST);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Swing();
            }
        });
    }

}

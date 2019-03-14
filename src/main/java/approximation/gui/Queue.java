package approximation.gui;

import javax.swing.*;
import java.awt.*;

public class Queue {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Очередь по потокам");
        JPanel inputPanel = new JPanel(new GridLayout(5, 3,0,5));

        JLabel numOfThreadsLabel = new JLabel("Количество потоков");
        JTextField  numOfThreadsTF = new JTextField();
        JButton makeThreadsPanel = new JButton("Сгенерировать поля ввода");
        //здесь надо сделать генерацию для N потоков
        inputPanel.add(numOfThreadsLabel);
        inputPanel.add(numOfThreadsTF);
        inputPanel.add(makeThreadsPanel);

        JPanel lamdaPanel1 = new JPanel(new GridLayout(1,2,0,5));
        JLabel lambdaLabel1 = new JLabel("Интенсивность поступления заявок 1 потока");
        JTextField lambdaTF1 = new JTextField();
        lamdaPanel1.add(lambdaLabel1);
        lamdaPanel1.add(lambdaTF1);
        JPanel lamdaPanel2 = new JPanel(new GridLayout(1,2,0,5));
        JLabel lambdaLabel2 = new JLabel("Интенсивность поступления заявок 2 потока");
        JTextField lambdaTF2 = new JTextField();
        lamdaPanel2.add(lambdaLabel2);
        lamdaPanel2.add(lambdaTF2);
        JPanel lamdaPanel3 = new JPanel(new GridLayout(1,2,0,5));
        JLabel lambdaLabel3 = new JLabel("Интенсивность поступления заявок 3 потока");
        JTextField lambdaTF3 = new JTextField();
        lamdaPanel3.add(lambdaLabel3);
        lamdaPanel3.add(lambdaTF3);
        inputPanel.add(lamdaPanel1);
        inputPanel.add(lamdaPanel2);
        inputPanel.add(lamdaPanel3);

        JPanel funcPanel1 = new JPanel(new GridLayout(1,2,0,5));
        JLabel funcLabel1 = new JLabel("Интенсивность обслуживания 1 потока");
        JTextField funcTF1 = new JTextField();
        funcPanel1.add(funcLabel1);
        funcPanel1.add(funcTF1);
        JPanel funcPanel2 = new JPanel(new GridLayout(1,2,0,5));
        JLabel funcLabel2 = new JLabel("Интенсивность обслуживания 2 потока");
        JTextField funcTF2 = new JTextField();
        funcPanel2.add(funcLabel2);
        funcPanel2.add(funcTF2);
        JPanel funcPanel3 = new JPanel(new GridLayout(1,2,0,5));
        JLabel funcLabel3 = new JLabel("Интенсивность обслуживания 3 потока");
        JTextField funcTF3 = new JTextField();
        funcPanel3.add(funcLabel3);
        funcPanel3.add(funcTF3);
        inputPanel.add(funcPanel1);
        inputPanel.add(funcPanel2);
        inputPanel.add(funcPanel3);

        JPanel timePanel1 = new JPanel(new GridLayout(1,2,0,5));
        JLabel timeLabel1 = new JLabel("Время работы 1 потока");
        JTextField timeTF1 = new JTextField();
        timePanel1.add(timeLabel1);
        timePanel1.add(timeTF1);
        JPanel timePanel2 = new JPanel(new GridLayout(1,2,0,5));
        JLabel timeLabel2 = new JLabel("Время работы 2 потока");
        JTextField timeTF2 = new JTextField();
        timePanel2.add(timeLabel2);
        timePanel2.add(timeTF2);
        JPanel timePanel3 = new JPanel(new GridLayout(1,2,0,5));
        JLabel timeLabel3 = new JLabel("Время работы 3 потока");
        JTextField timeTF3 = new JTextField();
        timePanel3.add(timeLabel3);
        timePanel3.add(timeTF3);

        inputPanel.add(timePanel1);
        inputPanel.add(timePanel2);
        inputPanel.add(timePanel3);


        JPanel pointsPanel = new JPanel(new GridLayout(1,2,0,5));
        JLabel numOfPointsLabel = new JLabel("Число точек разрыва");
        JTextField numOfPointsTF = new JTextField();
        pointsPanel.add(numOfPointsLabel);
        pointsPanel.add(numOfPointsTF);
        inputPanel.add(pointsPanel);



        frame.add(inputPanel);
        frame.setSize(800,500);
        frame.show();

    }
}

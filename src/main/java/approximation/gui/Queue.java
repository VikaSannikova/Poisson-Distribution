package approximation.gui;

import javax.swing.*;
import java.awt.*;

public class Queue extends JFrame {

    public Queue(){
        super("Моделирование СМО");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable table = new JTable();
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane, BorderLayout.CENTER);

        JButton add = new JButton("Добавить поток");
        JButton change = new JButton("Изменить данные");
        JButton delete = new JButton("Удалить");
        JPanel buttontsPanel = new JPanel(new GridLayout(3,1,0,5));
        buttontsPanel.add(add);
        buttontsPanel.add(change);
        buttontsPanel.add(delete);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(buttontsPanel);
        add(flow, BorderLayout.EAST);

        setSize(800,500);
        show();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Queue();
            }
        });
    }
}

package approximation.DoneClasses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    private Object[] headers = new String[]{"Номер потока", "Начальная очередь", "Интенсивность поступления","ЗС", "Интенсивность обслуживания", "ЖС"};
    private  Object[][] data = new String[][]{};
    private JTable table;
    private DefaultTableModel tableModel;


    public GUI(){
        super("Моделирование СМО");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(addThread());
        createTable();



        setJMenuBar(menuBar);
//        JPanel panel = new JPanel();
//        panel.add(table);
//        getContentPane().add(panel);
        setSize(300, 200);
        setVisible(true);
    }

    public JMenu addThread(){
        JMenu add = new JMenu("Добавить");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = table.getSelectedRow();
                tableModel.insertRow(1, new Object[] {});
            }
        });

        return add;
    }

    public void createTable(){
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(headers);
        table = new JTable(tableModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table));
        getContentPane().add(contents);
//        JPanel panel = new JPanel(new );
//        panel.add(table);
//        getContentPane().add(panel);

    }

    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        new GUI();
    }
}

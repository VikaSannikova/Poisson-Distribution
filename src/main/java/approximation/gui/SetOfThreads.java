package approximation.gui;

import approximation.DoneClasses.Thread;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SetOfThreads extends AbstractTableModel {
    private List<Thread> threads = new ArrayList<>();
    @Override
    public int getRowCount() {
        return threads.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Thread current = threads.get(rowIndex);
        switch (columnIndex){
            case 0:
                return current.getId();
            case 1:
                return current.getQueue();
            case 2:
                return current.getLambda();
            case 3:
                return current.getGreenTime();
            case 4:
                return current.getFormula().toString();
            case 5:
                return current.getYellowTime();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Номер потока";
            case 1:
                return "Начальная очередь";
            case 2:
                return "Интенсивность поступления";
            case 3:
                return "Зеленый свет";
            case 4:
                return "Функция обслуживания";
            case 5:
                return "Желтый свет";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return Integer.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;
            case 4:
                return String.class;
            case 5:
                return Double.class;
        }
        return Object.class;
    }

    public void addThread(Thread thread){
        threads.add(thread);
        fireTableDataChanged();
    }

    public void deleteThread(int row){
        threads.remove(row);
        fireTableDataChanged();
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public Thread getThread(int row){
        return threads.get(row);
    }
}

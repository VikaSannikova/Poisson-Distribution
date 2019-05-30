package example;

import java.awt.Color;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.category.CategoryDataset;

public class BarChartVertical {
    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(80, "Marks", "1");
        dataset.setValue(50, "Marks", "2");
        dataset.setValue(75, "Marks", "3");
        dataset.setValue(90, "Marks", "4");
        dataset.setValue(10, "hello", "3");


        JFreeChart chart = ChartFactory.createBarChart("Student score", "Name", "Mark", dataset,PlotOrientation.VERTICAL,true,true,false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame = new ChartFrame("!", chart);
        frame.setVisible(true);
        frame.setSize(200,200);



    }



}
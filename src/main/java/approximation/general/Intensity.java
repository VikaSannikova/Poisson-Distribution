package approximation.general;
import java.util.ArrayList;
import static java.lang.Math.pow;

public class Intensity {

    int length;
    double[] deltatimes;
    //ArrayList<Double> deltatimes;
    Range[] intervals;
    //ArrayList<Range> intervals;
    double[] intensities;

    public Intensity() {
    }

    //ArrayList<Double> intensities;
    double average;

    public Intensity(int numOfPoints, double T){
        this.length = numOfPoints+1;
        double delta = T/length;
        deltatimes = new double[length];
        for(int i = 0; i < length; i++){
            deltatimes[i]=delta;
        }
        createIntervals();
        createIntensity();
        createAverage();
    }

    void createIntervals(){
        intervals = new Range[length];
        for (int i = 0;i<length;i++){
            //Pair pair;
            Range range;
            if(i == 0){
                range = new Range(0,deltatimes[0]);
            }else
                range = new Range(intervals[i-1].getRight(),intervals[i-1].getRight()+deltatimes[i]);
            intervals[i] = range;
        }
    }

    void createIntensity() {
        intensities = new double[length];
        for (int i = 0; i < length; i++) {
            intensities[i] = pow(intervals[i].getLeft(), 2);
        }
    }

    void createAverage(){
      average = 0;
      for(int i = 0; i< intensities.length; i++){
          average+= intensities[i];
      }
      average/= intensities.length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double[] getDeltatimes() {
        return deltatimes;
    }

    public void setDeltatimes(double[] deltatimes) {
        this.deltatimes = deltatimes;
    }

    public Range[] getIntervals() {
        return intervals;
    }

    public void setIntervals(Range[] intervals) {
        this.intervals = intervals;
    }

    public double[] getIntensities() {
        return intensities;
    }

    public void setIntensities(double[] intensities) {
        this.intensities = intensities;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) { this.average = average; }
}

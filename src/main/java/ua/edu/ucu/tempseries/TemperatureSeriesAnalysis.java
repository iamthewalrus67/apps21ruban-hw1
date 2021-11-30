package ua.edu.ucu.tempseries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int temperatureSeriesCapacity = 0;
    private int temperatureSeriesSize = 0;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temperature: temperatureSeries) {
            if (temperature < -273) {
                throw new InputMismatchException();
            }
        }

        temperatureSeriesCapacity = temperatureSeries.length;
        temperatureSeriesSize = temperatureSeriesCapacity;
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public double[] getTemperatureSeries() {
        return temperatureSeries;
    }

    public void setTemperatureSeries(double[] temperatureSeries) {
        for (double temperature: temperatureSeries) {
            if (temperature < -273) {
                throw new InputMismatchException();
            }
        }
        temperatureSeriesCapacity = temperatureSeries.length;
        temperatureSeriesSize = temperatureSeriesCapacity;
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public double average() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        double sum = 0;

        for (int i = 0; i < temperatureSeriesSize; i++) {
            sum += temperatureSeries[i];
        }

        return sum/temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        double mean = average();
        double sum = 0;

        for (int i = 0; i < temperatureSeriesSize; i++) {
            sum += Math.pow(temperatureSeries[i]-mean, 2);
        }

        return Math.sqrt(sum/(temperatureSeries.length-1));
    }

    public double min() {
        return findTempClosestToValue(-273);
    }

    public double max() {
        return findTempClosestToValue(Double.MAX_VALUE);
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        double closest = temperatureSeries[0];
        double minDifference = Math.abs(temperatureSeries[0] - tempValue);

        for (int i = 0; i < temperatureSeriesSize; i++) {
            double temp = temperatureSeries[i];
            double difference = Math.abs(temp - tempValue);
            if (difference < minDifference) {
                minDifference = difference;
                closest = temp;
            } else if (difference == minDifference) {
                closest = Math.max(closest, temp);
            }
        }

        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        
        double[] lessThanArray = new double[temperatureSeriesSize];

        int lessThanCounter = 0;
        for (int i = 0; i < temperatureSeriesSize; i++) {
            if (temperatureSeries[i] < tempValue) {
                lessThanArray[lessThanCounter] = temperatureSeries[i];
                lessThanCounter++;
            }
        }

        double[] result = new double[lessThanCounter];
        System.arraycopy(lessThanArray, 0, result, 0, lessThanCounter);

        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        double[] greaterThanArray = new double[temperatureSeriesSize];

        int greaterThanCounter = 0;
        for (int i = 0; i < temperatureSeriesSize; i++) {
            if (temperatureSeries[i] >= tempValue) {
                greaterThanArray[greaterThanCounter] = temperatureSeries[i];
                greaterThanCounter++;
            }
        }

        double[] result = new double[greaterThanCounter];
        System.arraycopy(greaterThanArray, 0, result, 0, greaterThanCounter);

        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temp: temps) {
            if (temp < -273) {
                throw new InputMismatchException();
            }
        }

        int startIndex = temperatureSeriesSize;
        int endIndex = temps.length + temperatureSeriesSize;
        for (int i = startIndex; i < endIndex; i++) {
            if (i+1 > temperatureSeriesCapacity) {
                expandArray();
            }
            temperatureSeries[i] = temps[i-startIndex];
            temperatureSeriesSize++;
        }

        temperatureSeriesSize += temps.length;
        return temperatureSeriesSize;
    }

    private void expandArray() {
        double[] newTempSeries = new double[temperatureSeriesCapacity*2];
        temperatureSeriesCapacity *= 2;
        System.arraycopy(temperatureSeries, 0, newTempSeries, 0, temperatureSeriesSize);
        temperatureSeries = newTempSeries;
    }
}

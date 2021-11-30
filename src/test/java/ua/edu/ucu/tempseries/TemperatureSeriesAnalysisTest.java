package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.Arrays;


public class TemperatureSeriesAnalysisTest {
    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }


    @Test
    public void testDeviation() {
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(new double[]{1,2,3,4});
        assertEquals(1.82, temperatureSeriesAnalysis.deviation(), 0.01);
    }

    @Test
    public void testMin() {
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(new double[]{0, -1, -4, 1, 2, 3, 4});
        assertEquals(-4, temperatureSeriesAnalysis.min(), 0.01);
    }

    @Test
    public void testMax() {
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(new double[]{1,2,3,4});
        assertEquals(4, temperatureSeriesAnalysis.max(), 0.01);
    }

    @Test
    public void testFindTempClosestToZero() {
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(new double[]{1,2,3,4});
        assertEquals(1, temperatureSeriesAnalysis.findTempClosestToZero(), 0.01);
    }

    @Test
    public void testFindTempClosestToValue() {
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(new double[]{1,2,3,4});
        assertEquals(4, temperatureSeriesAnalysis.findTempClosestToValue(6), 0.01);
    }

    @Test
    public void testFindTempsLessThen() {
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(new double[]{1,2,3,4});
        assertArrayEquals(new double[]{1,2}, temperatureSeriesAnalysis.findTempsLessThen(3), 0.01);
    }

    @Test
    public void testFindTempsGreaterThen() {
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(new double[]{1,2,3,4});
        assertArrayEquals(new double[]{3,4}, temperatureSeriesAnalysis.findTempsGreaterThen(3), 0.01);
    }

    @Test
    public void testSummaryStatistics() {
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(new double[]{1,2,3,4});
        TempSummaryStatistics tempSummaryStatistics = temperatureSeriesAnalysis.summaryStatistics();
        assertEquals(2.5, tempSummaryStatistics.getAvgTemp(), 0.01);
        assertEquals(1.82, tempSummaryStatistics.getDevTemp(), 0.01);
        assertEquals(1, tempSummaryStatistics.getMinTemp(), 0.01);
        assertEquals(4, tempSummaryStatistics.getMaxTemp(), 0.01);

    }

    @Test
    public void testAddTemps() {
        TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(new double[]{1,2,3,4});
        assertEquals(16, temperatureSeriesAnalysis.addTemps(1, 2, -4, -5, -10, 3));
        assertArrayEquals(new double[]{1, 2, 3, 4, 1, 2, -4, -5, -10, 3, 0, 0, 0, 0, 0, 0}, temperatureSeriesAnalysis.getTemperatureSeries(), 0.01);

    }
}

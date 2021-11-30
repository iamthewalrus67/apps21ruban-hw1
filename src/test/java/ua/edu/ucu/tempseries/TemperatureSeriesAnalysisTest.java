package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.InputMismatchException;


public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis emptyTemperatureSeriesAnalysis = new TemperatureSeriesAnalysis();
    private TemperatureSeriesAnalysis tempSeriesAnalysis;
    private TemperatureSeriesAnalysis oneElementSeriesAnalysis;

    @BeforeEach
    public void setUp() {
        tempSeriesAnalysis = new TemperatureSeriesAnalysis(new double[] {5, 1, 3, 6, 2, -1, -2, -5});
        oneElementSeriesAnalysis = new TemperatureSeriesAnalysis(new double[] {1});
    }

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
        setUp();
        assertEquals(3.68, tempSeriesAnalysis.deviation(), 0.01);
        assertEquals(0, oneElementSeriesAnalysis.deviation(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationException() {
        emptyTemperatureSeriesAnalysis.deviation();
    }

    @Test
    public void testMin() {
        setUp();
        assertEquals(-5, tempSeriesAnalysis.min(), 0.00001);
        assertEquals(1, oneElementSeriesAnalysis.min(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinException() {
        emptyTemperatureSeriesAnalysis.min();
    }

    @Test
    public void testMax() {
        setUp();
        assertEquals(6, tempSeriesAnalysis.max(), 0.00001);
        assertEquals(1, oneElementSeriesAnalysis.max(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxException() {
        emptyTemperatureSeriesAnalysis.max();
    }

    @Test
    public void testFindTempClosestToZero() {
        setUp();
        assertEquals(1, tempSeriesAnalysis.findTempClosestToZero(), 0.00001);
        assertEquals(1, oneElementSeriesAnalysis.findTempClosestToZero(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroException() {
        emptyTemperatureSeriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToValue() {
        setUp();
        assertEquals(-5, tempSeriesAnalysis.findTempClosestToValue(-4), 0.00001);
        assertEquals(1, oneElementSeriesAnalysis.findTempClosestToValue(6), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueException() {
        emptyTemperatureSeriesAnalysis.findTempClosestToValue(6);
    }

    @Test
    public void testFindTempsLessThen() {
        setUp();
        assertArrayEquals(new double[]{1, 2, -1, -2, -5}, tempSeriesAnalysis.findTempsLessThen(3), 0.00001);
        assertArrayEquals(new double[]{1}, oneElementSeriesAnalysis.findTempsLessThen(3), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThenException() {
        emptyTemperatureSeriesAnalysis.findTempsLessThen(2);
    }

    @Test
    public void testFindTempsGreaterThen() {
        setUp();
        assertArrayEquals(new double[]{5, 3, 6}, tempSeriesAnalysis.findTempsGreaterThen(3), 0.00001);
        assertArrayEquals(new double[]{}, oneElementSeriesAnalysis.findTempsGreaterThen(3), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThenException() {
        emptyTemperatureSeriesAnalysis.findTempsGreaterThen(3);
    }

    @Test
    public void testSummaryStatistics() {
        setUp();
        TempSummaryStatistics tempSummaryStatistics = tempSeriesAnalysis.summaryStatistics();
        assertEquals(1.125, tempSummaryStatistics.getAvgTemp(), 0.00001);
        assertEquals(3.68, tempSummaryStatistics.getDevTemp(), 0.01);
        assertEquals(-5, tempSummaryStatistics.getMinTemp(), 0.00001);
        assertEquals(6, tempSummaryStatistics.getMaxTemp(), 0.00001);

        tempSummaryStatistics = oneElementSeriesAnalysis.summaryStatistics();
        assertEquals(1, tempSummaryStatistics.getAvgTemp(), 0.00001);
        assertEquals(0, tempSummaryStatistics.getDevTemp(), 0.01);
        assertEquals(1, tempSummaryStatistics.getMinTemp(), 0.00001);
        assertEquals(1, tempSummaryStatistics.getMaxTemp(), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsException() {
        emptyTemperatureSeriesAnalysis.summaryStatistics();
    }

    @Test
    public void testAddTemps() {
        setUp();
        assertEquals(14, tempSeriesAnalysis.addTemps(1, 2, -4, -5, -10, 3));
        assertEquals(7, oneElementSeriesAnalysis.addTemps(1, 2, -4, -5, -10, 3));
        assertArrayEquals(new double[]{5, 1, 3, 6, 2, -1, -2, -5, 1, 2, -4, -5, -10, 3}, tempSeriesAnalysis.getTemperatureSeries(), 0.00001);
        assertArrayEquals(new double[]{1, 1, 2, -4, -5, -10, 3}, oneElementSeriesAnalysis.getTemperatureSeries(), 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsException() {
        emptyTemperatureSeriesAnalysis.addTemps(2, 3, 4, 5, -274, 0);
    }
}

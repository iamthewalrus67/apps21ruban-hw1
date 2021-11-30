package ua.edu.ucu.tempseries;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TempSummaryStatistics {
    private double avgTemp;
    private double devTemp;
    private double minTemp;
    private double maxTemp;


    public TempSummaryStatistics() {}

    public TempSummaryStatistics(double avgTemp, double devTemp, double minTemp, double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
    
}

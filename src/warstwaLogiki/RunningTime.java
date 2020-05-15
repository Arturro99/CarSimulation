package warstwaLogiki;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;

public class RunningTime {
    private static long timeSinceStartOfEngine;
    private static long timeSinceStartOfApp;
    private static long startCountingApp;
    private static long startCountingEngine;
    private static boolean IsEngineOn = false;

    public static void startCountingTimeForApp() {
        startCountingApp = Instant.now().toEpochMilli();
    }

    public static void startCountingTimeForEngine() {
        startCountingEngine = Instant.now().toEpochMilli();
    }

    public static double getRunningEngineTime() {
        long end = Instant.now().toEpochMilli();
        if(IsEngineOn)
            return Math.round(((end - startCountingEngine)/1000.0)*100.0)/100.0/60.0;
        else
            return 0;
    }

    public static double getRunningAppTime() {
        long end = Instant.now().toEpochMilli();
        return Math.round(((end - startCountingApp)/1000.0))/60.0;
    }

    public static void setIsEngineOn(boolean bool) {IsEngineOn = bool;}
    public static boolean getIsEngineOn() {return IsEngineOn;}
}

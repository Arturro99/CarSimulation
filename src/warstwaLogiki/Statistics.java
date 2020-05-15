package warstwaLogiki;

import warstwaDanych.Mileage;

public class Statistics {
    private static double[] forAvgSpeed = {1.0,0.0};
    private static int maxSpeed = 0;
    private static double travelDistance = 0;

    public static double calculateAvgSpeed(int power) {
        forAvgSpeed[1]+=power;
        forAvgSpeed[0]+=1;
        return forAvgSpeed[1]/forAvgSpeed[0];
    }

    public static double getAvgSpeed() {
        return forAvgSpeed[1]/forAvgSpeed[0];
    }

    public static int getMaxSpeed(int power) {
        if(power>maxSpeed)
            maxSpeed = power;
        return maxSpeed;
    }

    public static double getAvgFuelConsumption(double avgSpeed) {
        if(!RunningTime.getIsEngineOn())
            return 0;
        if(avgSpeed<=90)
            return -0.02222*avgSpeed+7;
        else
            return 0.0727272*avgSpeed-1.545454;
    }

    public static double getTravelDistance(int power, Mileage mileage) {
        double tmp = power*(0.1/60.0/60.0);
        travelDistance+=tmp;
        mileage.addToMileage(tmp);
        return travelDistance;
    }

    public static void resetAvgAndMaxSpeed() {
        forAvgSpeed[0] = 1.0;
        forAvgSpeed[1] = 0.0;
        maxSpeed = 0;
        travelDistance = 0;
    }
}

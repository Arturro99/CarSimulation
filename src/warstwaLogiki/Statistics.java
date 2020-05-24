package warstwaLogiki;

import warstwaDanych.Mileage;

/**
 * Klasa odpowiada za zarzadzanie statystykami
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class Statistics {
    /**
     * Zmienna przechowujaca tablice potrzebna do wyliczenia sredniej predkosci pojazdu
     */
    private static double[] forAvgSpeed = {1.0,0.0};
    /**
     * Zmienna przechowujaca maksymalna do danej chwili predkosc pojazdu
     */
    private static int maxSpeed = 0;
    /**
     * Zmienna przechowujaca przebyta odleglosc
     */
    private static double travelDistance = 0;

    /**
     *  Metoda oblicza srednia predkosc pojazdu
     */
    public static double calculateAvgSpeed(int power) {
        forAvgSpeed[1]+=power;
        forAvgSpeed[0]+=1;
        return forAvgSpeed[1]/forAvgSpeed[0];
    }

    /**
     * Metoda zwraca srednia predkosc pojazdu
     * @return  Srednia predkosc pojazdu
     */
    public static double getAvgSpeed() {
        return forAvgSpeed[1]/forAvgSpeed[0];
    }

    /**
     * Metoda sprawdza aktualna maksymalna predkosc
     * @param power  Aktualna predkosc
     * @return  Maksymalna predkosc
     */
    public static int getMaxSpeed(int power) {
        if(power>maxSpeed)
            maxSpeed = power;
        return maxSpeed;
    }

    /**
     * Metoda zwraca srednie zuzycie paliwa na okreslona chwile
     * @param avgSpeed  Srednia predkosc na dany moment jazdy
     * @return  Wartosc spalanego paliwa na 100 km
     */
    public static double getAvgFuelConsumption(double avgSpeed) {
        if(!RunningTime.getIsEngineOn())
            return 0;
        if(avgSpeed<=90)
            return -0.02222*avgSpeed+7;
        else
            return 0.0727272*avgSpeed-1.545454;
    }

    /**
     * Metoda zwraca przebyta odleglosc
     * @param power  Predkosc pojazdu
     * @param mileage  Obiekt, do ktorego zostanie zapisana przebyta odleglosc
     * @return  Wartosc przebytej odleglosci
     */
    public static double getTravelDistance(int power, Mileage mileage) {
        double tmp = power*(0.1/60.0/60.0);
        travelDistance+=tmp;
        mileage.addToMileage(tmp);
        return travelDistance;
    }

    /**
     * Metoda resetujaca statystyki biezace
     */
    public static void resetAvgAndMaxSpeed() {
        forAvgSpeed[0] = 1.0;
        forAvgSpeed[1] = 0.0;
        maxSpeed = 0;
        travelDistance = 0;
    }
}

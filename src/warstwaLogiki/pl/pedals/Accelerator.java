package warstwaLogiki.pl.pedals;

import warstwaLogiki.pl.exceptions.TooFastException;

public class Accelerator implements Pedals {
    private static int power = 0;

    public Accelerator(){}

    @Override
    public void pressPedal(Integer powerInPercentage) throws TooFastException {
            if (power + powerInPercentage > 250) {
                throw new TooFastException("Maksymalna wartość prędkości nie może przekroczyć 250 km/h (" + (int)(power + powerInPercentage) + " km/h");
            }
            else {
                power += powerInPercentage;
                //System.out.println("Przyspieszono samochód, jego aktualna prędkość to " + power + "% maksymalnej prędkości.");
            }
    }

    @Override
    public void releasePedal(Integer powerInPercentage) {
        if(power == 0)
            System.out.println("Na pedał gazu nie oddziałuje żadna siła nacisku.");
        else{
            if (power - powerInPercentage < 0) {
                power = 0;
            }
            else
                power -= powerInPercentage;
            //System.out.println("Stopniowo wytracono prędkość, aktualna prędkość samochodu to " + power + "% maksymalnej prędkości.");
        }
    }

    public static int getPower() {
        return power;
    }

    static void setPower(int valueInPercentage){
        power += valueInPercentage;
    }


}

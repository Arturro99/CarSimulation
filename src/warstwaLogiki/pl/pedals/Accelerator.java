package warstwaLogiki.pl.pedals;

import warstwaLogiki.pl.exceptions.TooFastException;

/**
 * Klasa odpowiadajaca za dzialanie pedalu gazu
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class Accelerator implements Pedals {
    /**
     * Zmienna przechowujaca predkosc pojazdu
     */
    private static int power = 0;

    /**
     * Domyslny konstruktor pedalu gazu
     */
    public Accelerator(){}

    /**
     * Metoda odpowiadajaca za wywolywanie nacisku na pedal
     * @param powerInPercentage  Sila, z jaka bedzie sie oddzialywac na pedal
     * @throws TooFastException  Wyjatek zostanie rzucony w przypadku przekroczenia pewnej okreslonej predkosci
     */
    @Override
    public void pressPedal(Integer powerInPercentage) throws TooFastException {
            if (power + powerInPercentage > 250) {
                throw new TooFastException("Maksymalna wartość prędkości nie może przekroczyć 250 km/h (" + (power + powerInPercentage) + " km/h)");
            }
            else {
                power += powerInPercentage;
                //System.out.println("Przyspieszono samochód, jego aktualna prędkość to " + power + "% maksymalnej prędkości.");
            }
    }

    /**
     * Metoda odpowiadajaca za zmniejszenie nacisku na pedal
     * @param powerInPercentage  Ilosc sily nacisku zdjetej z pedalu gazu
     */
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

    /**
     * Metoda zwracajaca predkosc pojazdu
     * @return  Predkosc pojazdu
     */
    public static int getPower() {
        return power;
    }

    /**
     * Metoda ustawiajaca predkosc pojazdu
     * @param valueInPercentage  Wartosc, o jaka nalezy zwiekszyc predkosc pojazdu
     */
    static void setPower(int valueInPercentage){
        power += valueInPercentage;
    }


}

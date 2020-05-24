package warstwaLogiki.pl.lights;

/**
 * Klasa odpowiada za obsluge kierunkowskazow
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class Indicator extends LightingSystem implements Lights {
    /**
     * Zmienna przechowujaca informacje o rodzaju kierunkowskazu (lewy/prawy)
     */
    Side side;

    /**
     * Konstruktor kierunkowskazu
     * @param side  Wybor pomiedzy prawym/lewym kierunkowskazem
     */
    public Indicator(Side side) {this.side = side; }

    /**
     * Metoda wlaczajaca kierunkowskaz - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "true"
     */
    public void turnOn() {
        if(!isOn) { super.isOn = true;
        LightingSystem.incrementNumberOfTurningSignals();
            if (side == Side.left) {
                System.out.println("Włączono lewy kierunkowskaz.");
            } else {
                System.out.println("Włączono prawy kierunkowskaz.");
            }
        }
        else
            System.out.println("Kierunkowskaz jest już włączony.");
    }

    /**
     * Metoda wylaczajaca kierunkowskaz - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "false"
     */
    public void turnOff() {
    if(isOn) {
        super.isOn = false;
        LightingSystem.decrementNumberOfTurningSignals();
        System.out.println("Wyłączono kierunkowskaz.");
    }
    else
        System.out.println("Żaden kierunkowskaz nie jest włączony");
    }

    /**
     * Metoda wypisuje inforamcje o rodzaju kierukowskazu
     * @return  Podpis swiatel
     */
    public String toString()
    {
        if (side == Side.left) {
            return "Lewy kierunkowskaz";
        } else {
            return "Prawy kierunkowskaz";
        }
    }

    /**
     * Metoda zwraca stan dzialania kierunkowskazu (true/false)
     * @return  Flaga wlaczania swiatla
     */
    public boolean getIsOn() {return isOn;}

}

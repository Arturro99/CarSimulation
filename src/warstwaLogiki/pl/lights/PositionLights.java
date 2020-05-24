package warstwaLogiki.pl.lights;

/**
 * Klasa odpowiada za obsluge swiatel pozycyjnych
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class PositionLights extends LightingSystem implements Lights{

    /**
     * Domyslny konstruktor klasy
     */
    public PositionLights() {}

    /**
     * Metoda wlaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "true"
     */
    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
            System.out.println("Włączono światła pozycyjne.");
        }
        else
            System.out.println("Swiatła pozycyjne są już włączone.");
    }

    /**
     * Metoda wylaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "false"
     */
    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła pozycyjne.");
        }
        else
            System.out.println("Swiatła pozycyjne nie są włączone");
    }

    /**
     * Metoda wypisuje inforamcje o rodzaju swiatla
     * @return  Podpis swiatel
     */
    public String toString()
    {
        return "Swiatła pozycyjne";
    }

    /**
     * Metoda zwraca stan dzialania swiatel (true/false)
     * @return  Flaga wlaczania swiatla
     */
    public boolean getIsOn() {return isOn;}
}

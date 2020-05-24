package warstwaLogiki.pl.lights;

/**
 * Klasa odpowiada za obsluge swiatel przeciwmgielnych
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class FogLights extends LightingSystem implements Lights {
    /**
     * Domyslny konstruktor klasy
     */
    public FogLights() {}

    /**
     * Metoda wlaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "true"
     */
    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
            System.out.println("Włączono światła przeciwmgielne.");
        }
        else
            System.out.println("Swiatła przeciwmgielne są już włączone.");
    }

    /**
     * Metoda wylaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "false"
     */
    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła przeciwmgielne.");
        }
        else
            System.out.println("Swiatła przeciwmgielne nie są włączone");
    }

    /**
     * Metoda wypisuje inforamcje o rodzaju swiatla
     * @return - podpis swiatel
     */
    public String toString()
    {
        return "Swiatła przeciwmgielne";
    }

    /**
     * Metoda zwraca stan dzialania swiatel (true/false)
     * @return - flaga wlaczania swiatla
     */
    public boolean getIsOn() {return isOn;}
}
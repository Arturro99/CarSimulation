package warstwaLogiki.pl.lights;

/**
 * Klasa odpowiada za obsluge swiatel mijania
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class PassingLights extends LightingSystem implements Lights {

    /**
     * Domyslny konstruktor klasy
     */
    public PassingLights() {}

    /**
     * Metoda wlaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "true"
     */
    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
                System.out.println("Włączono światła mijania.");
            }
        else
            System.out.println("Swiatła mijania są już włączone.");
    }

    /**
     * Metoda wylaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "false"
     */
    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła mijania.");
        }
        else
            System.out.println("Swiatła mijania nie są włączone");
    }

    /**
     * Metoda wypisuje inforamcje o rodzaju swiatla
     * @return - podpis swiatel
     */
    public String toString()
    {
            return "Swiatła mijania";
    }

    /**
     * Metoda zwraca stan dzialania swiatel (true/false)
     * @return - flaga wlaczania swiatla
     */
    public boolean getIsOn() {return isOn;}

}

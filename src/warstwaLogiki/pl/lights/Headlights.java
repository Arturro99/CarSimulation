package warstwaLogiki.pl.lights;

/**
 * Klasa odpowiada za obsluge swiatel drogowych
 */
public class Headlights extends LightingSystem implements Lights {

    /**
     * Domyslny konstruktor klasy
     */
    public Headlights() {}

    /**
     * Metoda wlaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "true"
     */
    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
            System.out.println("Włączono światła długie.");
        }
        else
            System.out.println("Swiatła długie są już włączone.");
    }

    /**
     * Metoda wylaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "false"
     */
    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła długie.");
        }
        else
            System.out.println("Swiatła długie nie są włączone");
    }

    /**
     * Metoda wypisuje inforamcje o rodzaju swiatla
     * @return - podpis swiatel
     */
    public String toString()
    {
        return "Swiatła długie";
    }

    /**
     * Metoda zwraca stan dzialania swiatel (true/false)
     * @return - flaga wlaczania swiatla
     */
    public boolean getIsOn() {return isOn;}
}

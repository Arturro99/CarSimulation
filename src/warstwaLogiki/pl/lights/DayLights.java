package warstwaLogiki.pl.lights;

/**
 * Klasa odpowiada za obsluge swiatel dziennych
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class DayLights extends LightingSystem implements Lights{
    /**
     * Domyslny konstruktor klasy
     */
    public DayLights() {}

    /**
    * Metoda wlaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "true"
    */
    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
            System.out.println("Włączono światła dzienne.");
        }
        else
            System.out.println("Swiatła dzienne są już włączone.");
    }

    /**
     * Metoda wylaczajaca swiatla - ustawia flage wlaczania swiatla w klasie nadrzednej na wartosc "false"
     */
    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła dzienne.");
        }
        else
            System.out.println("Swiatła dzienne nie są włączone");
    }

    /**
     * Metoda wypisuje inforamcje o rodzaju swiatla
     * @return  Podpis swiatel
     */
    public String toString()
    {
        return "Swiatła dzienne";
    }

    /**
     * Metoda zwraca stan dzialania swiatel (true/false)
     * @return  Flaga wlaczania swiatla
     */
    public boolean getIsOn() {return isOn;}
}

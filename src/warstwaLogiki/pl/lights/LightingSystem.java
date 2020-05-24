package warstwaLogiki.pl.lights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
/**
 *  Klasa bazowa dla wszystkich swiatel
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class LightingSystem {
    /**
     * Zmienna przechowujaca liste istniejacych swiatel w samochodzie
     */
    private ArrayList<LightingSystem>list = new ArrayList<>();  //Lista istniejących świateł w samochodzie
    /**
     * Zmienna przechowujaca informacje o stanie danych swiatel (wlaczone/wylaczone)
     */
    protected boolean isOn = false;                         //Zmienna przechowująca informację o tym, czy dane światła są włączone
    /**
     * Zmienna przechowujaca o liczbie wlaczonych kierunkowskazow
     */
    static private int numberOfTurningSignals = 0;        //Zmienna statyczna licząca ilość włączonych kierunkowskazów

    /**
     * Metoda dodaje swiatla do listy
     * @param obj  Obiekt reprezentujacy swiatlo
     */
    public void addToList(LightingSystem obj) { list.add(obj); }

    /**
     *  Metoda zwraca informacje o swiatlach
     * @return  Informacje o wlaczonych swiatlach
     */
    public String info()
    {
        StringBuilder out = new StringBuilder();
        System.out.println(list.size());
        for(LightingSystem obj : list)
        {
            if (obj.isOn) {
                if (obj instanceof PassingLights) {             //Jeśli obiekt z listy należy do klasy PassingLights
                    PassingLights passing = (PassingLights) obj;
                    out.append(passing.toString()).append("\n");
                } else if (obj instanceof Indicator && numberOfTurningSignals == 1) {      //Jeśli obiekt należy do klasy TurnSignal i jest włączony tylko jeden kierunkowskaz
                    Indicator indicator = (Indicator) obj;
                    out.append(indicator.toString()).append("\n");
                }
                else if(obj instanceof Indicator && numberOfTurningSignals == 2) {         //Włączone dwa kierunkowskazy -> światła awaryjne
                    out.append("Awaryjne\n");
                }
                else if(obj instanceof Headlights){         //Jeśli obiekt z listy należy do klasy Headlights
                    Headlights heads = (Headlights)obj;
                    out.append(heads.toString()).append("\n");
                }
                else if(obj instanceof DayLights){         //Jeśli obiekt z listy należy do klasy Headlights
                    DayLights day = (DayLights)obj;
                    out.append(day.toString()).append("\n");
                }
                else if(obj instanceof FogLights){         //Jeśli obiekt z listy należy do klasy FogLights
                    FogLights fog = (FogLights) obj;
                    out.append(fog.toString()).append("\n");
                }
            }
        }
        out = new StringBuilder(Arrays.stream(out.toString().split("\n")).distinct().collect(Collectors.joining("\n")));      //Aby nie powtarzały się światła awaryjne w oucie
        return "Włączone światła:\n" + out;
    }

    /**
     *  Inkrementacja statycznego pola liczacego ilosc wlaczonych kierunkowskazow
     */
    protected static void incrementNumberOfTurningSignals()     //inkrementacja statycznego pola liczącego ilość włączonych kierunkowskazów
    {
        numberOfTurningSignals++;
    }

    /**
     *  Dekrementacja statycznego pola liczacego ilosc wlaczonych kierunkowskazow
     */
    protected static void decrementNumberOfTurningSignals()     //dekrementacja -------------------------||-------------------------------
    {
        numberOfTurningSignals--;
    }

    /**
     * Metoda zwraca ilosc wlaczonych kierunkowskazow
     * @return  Ilosc wloczonych kierunkowskazow
     */
    public static int getNumberOfTurningSignals() {
        return numberOfTurningSignals;
    }

}

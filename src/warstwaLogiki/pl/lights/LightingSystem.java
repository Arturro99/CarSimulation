package warstwaLogiki.pl.lights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
/**
 *  Klasa bazowa dla wszystkich swiatel
 */
public class LightingSystem {
    private ArrayList<LightingSystem>list = new ArrayList<>();  //Lista istniejących świateł w samochodzie
    protected boolean isOn = false;                         //Zmienna przechowująca informację o tym, czy dane światła są włączone
    static private int numberOfTurningSignals = 0;        //Zmienna statyczna licząca ilość włączonych kierunkowskazów

    /**
     * Metoda dodaje swiatla do listy
     * @param obj - obiekt reprezentujacy swiatlo
     */
    public void addToList(LightingSystem obj) { list.add(obj); }

    /**
     *  Metoda zwraca informacje o swiatlach
     * @return - informacje o wlaczonych swiatlach
     */
    public String info()
    {
        String out = "";
        System.out.println(list.size());
        for(LightingSystem obj : list)
        {
            if (obj.isOn) {
                if (obj instanceof PassingLights) {             //Jeśli obiekt z listy należy do klasy PassingLights
                    PassingLights passing = (PassingLights) obj;
                    out += passing.toString() + "\n";
                } else if (obj instanceof Indicator && numberOfTurningSignals == 1) {      //Jeśli obiekt należy do klasy TurnSignal i jest włączony tylko jeden kierunkowskaz
                    Indicator indicator = (Indicator) obj;
                    out += indicator.toString() + "\n";
                }
                else if(obj instanceof Indicator && numberOfTurningSignals == 2) {         //Włączone dwa kierunkowskazy -> światła awaryjne
                    out += "Awaryjne\n";
                }
                else if(obj instanceof Headlights){         //Jeśli obiekt z listy należy do klasy Headlights
                    Headlights heads = (Headlights)obj;
                    out += obj.toString() + "\n";
                }
                else if(obj instanceof DayLights){         //Jeśli obiekt z listy należy do klasy Headlights
                    DayLights heads = (DayLights)obj;
                    out += obj.toString() + "\n";
                }
                else if(obj instanceof FogLights){         //Jeśli obiekt z listy należy do klasy FogLights
                    FogLights heads = (FogLights) obj;
                    out += obj.toString() + "\n";
                }
            }
        }
        out = Arrays.stream(out.split("\n")).distinct().collect(Collectors.joining("\n"));      //Aby nie powtarzały się światła awaryjne w oucie
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
     * @return - ilosc wloczonych kierunkowskazow
     */
    public static int getNumberOfTurningSignals() {
        return numberOfTurningSignals;
    }

}

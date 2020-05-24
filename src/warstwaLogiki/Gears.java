package warstwaLogiki;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import warstwaLogiki.pl.pedals.Accelerator;
import warstwaLogiki.pl.pedals.Clutch;

import java.util.ArrayList;

/**
 * Klasa ta sluzy do przeprowadzania operacji zwiazanych ze skrzynia biegow
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class Gears {
    /**
     * Sprawdzanie, czy wrzucony bieg znajduje sie w bezpiecznym przedziale(czy silnik nie ulega przegrzaniu)
     * @param listOfGears - lista biegow, flaga ustawiona jest na "true" jesli dany bieg jest wlaczony
     * @param listOfDiods - lista diod odpowiadajacych odpowiednim biegom
     * @param mainColor - glowny kolor w aplikacji
     */
    public static void checkEngineSpeed(ArrayList<Boolean> listOfGears, ArrayList<Circle> listOfDiods, Color mainColor){
        if(listOfGears.get(0) && Accelerator.getPower() > 20 ||
                listOfGears.get(1) && Accelerator.getPower() > 20 ||
                listOfGears.get(2) && Accelerator.getPower() > 70 ||
                listOfGears.get(3) && Accelerator.getPower() > 120 ||
                listOfGears.get(4) && Accelerator.getPower() > 140 ||
                listOfGears.get(5) && Accelerator.getPower() > 160 ||
                listOfGears.get(6) && Accelerator.getPower() > 220)
            listOfDiods.get(1).setFill(Color.RED);
        else
            listOfDiods.get(1).setFill(mainColor);
    }

    /**
     * Sprawdzanie wartosci predkosci, przy ktorej mozna zmienic bieg na wyzszy
     * @param listOfGears - lista biegow, flaga ustawiona jest na "true" jesli dany bieg jest wlaczony
     * @return - zwraca "true" jesli mozna zmienic bieg, w przeciwnym razie - "false"
     */
    public static boolean isGearInProperRangeUP(ArrayList<Boolean> listOfGears){
        return listOfGears.get(1) && Accelerator.getPower() >= 5 ||
                listOfGears.get(2) && Accelerator.getPower() >= 30 ||
                listOfGears.get(3) && Accelerator.getPower() >= 60 ||
                listOfGears.get(4) && Accelerator.getPower() >= 90 ||
                listOfGears.get(5) && Accelerator.getPower() >= 120;
    }

    /**
     * Sprawdzanie wartosci predkosci, przy ktorej mozna zmienic bieg na nizszy
     * @param listOfGears - lista biegow, flaga ustawiona jest na "true" jesli dany bieg jest wlaczony
     * @return - zwraca "true" jesli mozna zmienic bieg, w przeciwnym razie - "false"
     */
    public static boolean isGearInProperRangeDOWN(ArrayList<Boolean> listOfGears){
        return listOfGears.get(2) && Accelerator.getPower() < 30 ||
                listOfGears.get(3) && Accelerator.getPower() < 80 ||
                listOfGears.get(4) && Accelerator.getPower() < 130 ||
                listOfGears.get(5) && Accelerator.getPower() < 180 ||
                listOfGears.get(6) && Accelerator.getPower() < 200;
    }

    /**
     * Sprawdzanie, czy na danym biegu mozna szybciej/wolniej pojechac
     * @param listOfGears - lista biegow, flaga ustawiona jest na "true" jesli dany bieg jest wlaczony
     * @return - zwraca "true" jesli mozna szybciej pojechac, w przeciwnym razie - "false"
     */
    public static boolean canGoFurtherOnGear(ArrayList<Boolean> listOfGears){
        return listOfGears.get(0) && Accelerator.getPower() < 30 ||
                listOfGears.get(1) && Accelerator.getPower() < 30 ||
                listOfGears.get(2) && Accelerator.getPower() < 80 && Accelerator.getPower() > 5 ||
                listOfGears.get(3) && Accelerator.getPower() < 130 && Accelerator.getPower() > 30 ||
                listOfGears.get(4) && Accelerator.getPower() < 180 && Accelerator.getPower() > 60 ||
                listOfGears.get(5) && Accelerator.getPower() < 200 && Accelerator.getPower() > 90 ||
                listOfGears.get(6) &&  Accelerator.getPower() > 120;
    }

    /**
     * Sprawdzanie, czy na danym biegu mozna szybciej/wolniej pojechac wykorzystujac do tego tempomat
     * @param listOfGears - lista biegow, flaga ustawiona jest na "true" jesli dany bieg jest wlaczony
     * @param speed - wartosc predkosci tempomatu
     * @return - zwraca "true" jesli mozna szybciej pojechac, w przeciwnym razie - "false"
     */
    public static boolean canGoFurtherOnGear(ArrayList<Boolean> listOfGears, int speed){
         if(listOfGears.get(0) && Accelerator.getPower() < 30 ||
                listOfGears.get(1) && speed <= 30 ||
                listOfGears.get(2) && speed <= 80 && speed >= 0 ||
                listOfGears.get(3) && speed <= 130 && speed >= 25 ||
                listOfGears.get(4) && speed <= 180 && speed >= 55 ||
                listOfGears.get(5) && speed <= 200 && speed >= 85 ||
                listOfGears.get(6) && speed <= 250 && speed >= 115)
             return true;
         else
             return false;
    }

    /**
     * Metoda sluzy do obslugi zmiany biegow
     * @param key - informuje, ktory przycisk na klawiaturze zostal nacisniety
     * @param listOfGears - lista biegow, flaga ustawiona jest na "true" jesli dany bieg jest wlaczony
     * @param listOfGearsControls - lista diod odpowiadajacych odpowiednim biegom
     * @param listOfGearsCaption - lista podpisow dla odpowiednich biegow
     * @param mainColor - glowny kolor w aplikacji
     */
    public static void setGear(KeyEvent key, ArrayList<Boolean> listOfGears, ArrayList<Circle> listOfGearsControls, ArrayList<Text> listOfGearsCaption, Color mainColor){
        if(key.getCode() == KeyCode.NUMPAD8) {
            for (int i = 0; i < listOfGears.size(); i++) {
                if (Clutch.getIsOn()) {
                    if(listOfGears.get(i) && i != 6 && i > 0 && Gears.isGearInProperRangeUP(listOfGears) || (listOfGears.get(i) && i == 0 && Accelerator.getPower() == 0)) {
                        listOfGears.set(i, false);
                        listOfGears.set(i + 1, true);
                        System.out.println("Włączono bieg: " + "(" + listOfGears.get(i + 1) + ")" + listOfGearsCaption.get(i + 1).getText());
                        System.out.println("Wyłączono bieg: " + "(" + listOfGears.get(i) + ")" + listOfGearsCaption.get(i).getText());
                        listOfGearsControls.get(i + 1).setFill(Color.RED);
                        listOfGearsControls.get(i).setFill(mainColor);
                        break;
                    }
                }
            }
        }
        else if(key.getCode() == KeyCode.NUMPAD2) {
            for (int i = 0; i < listOfGears.size(); i++) {
                if (Clutch.getIsOn()) {
                    if(listOfGears.get(i) && i > 1  && isGearInProperRangeDOWN(listOfGears)|| (listOfGears.get(i) && i == 1 && Accelerator.getPower() == 0)) {
                        listOfGears.set(i, false);
                        listOfGears.set(i - 1, true);
                        System.out.println("Włączono bieg: " + "(" + listOfGears.get(i - 1) + ")" + listOfGearsCaption.get(i - 1).getText());
                        listOfGearsControls.get(i - 1).setFill(Color.RED);
                        listOfGearsControls.get(i).setFill(mainColor);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Metoda kalkuluje obroty silnika w zaleznosci od wartosci predkosci
     * @param listOfGears - lista biegow, flaga ustawiona jest na "true" jesli dany bieg jest wlaczony
     * @return - zwracana wartosc oznacza ilosc obrotow na minute silnika samochodowego
     */
    public static double calculateEngineSpeed(ArrayList<Boolean>listOfGears){
        if(listOfGears.get(1) || listOfGears.get(0))
            return Accelerator.getPower()*226.66 + 200;
        else if(listOfGears.get(2))
            return Accelerator.getPower()*82.66 + 386.66;
        else if(listOfGears.get(3))
            return Accelerator.getPower()*62 - 1060;
        else if(listOfGears.get(4))
            return Accelerator.getPower()*51.66 - 2300;
        else if(listOfGears.get(5))
            return Accelerator.getPower()*56.36 - 4272.72;
        else if(listOfGears.get(6))
            return Accelerator.getPower()*47.7 - 4923;
        return 0.0;
    }
}

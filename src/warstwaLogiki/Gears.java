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
 * Klasa ta służy do przechowywania operacji związanych ze skrzynią biegów
 */
public class Gears {
    /**
     *Sprawdzanie, czy wrzucony bieg znajduje się w bezpiecznym przedziale(czy silnik nie ulega przegrzaniu)
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
     *Sprawdzanie wartości prędkości, przy której można zmienić bieg
     */
    public static boolean isGearInProperRange(ArrayList<Boolean> listOfGears){
        return listOfGears.get(1) && Accelerator.getPower() >= 5 ||
                listOfGears.get(2) && Accelerator.getPower() >= 30 ||
                listOfGears.get(3) && Accelerator.getPower() >= 60 ||
                listOfGears.get(4) && Accelerator.getPower() >= 90 ||
                listOfGears.get(5) && Accelerator.getPower() >= 120;
    }

    /**
     *Sprawdzanie, czy na danym biegu można szybciej/wolniej pojechać
     */
    public static boolean canGoFurtherOnGear(ArrayList<Boolean> listOfGears){
        return listOfGears.get(0) && Accelerator.getPower() < 30 ||
                listOfGears.get(1) && Accelerator.getPower() < 30 ||
                listOfGears.get(2) && Accelerator.getPower() < 80 && Accelerator.getPower() > 5 ||
                listOfGears.get(3) && Accelerator.getPower() < 130 && Accelerator.getPower() > 30 ||
                listOfGears.get(4) && Accelerator.getPower() < 180 && Accelerator.getPower() > 60 ||
                listOfGears.get(5) && Accelerator.getPower() < 200 && Accelerator.getPower() > 90 ||
                listOfGears.get(6) && Accelerator.getPower() < 250 && Accelerator.getPower() > 120;
    }

    /**
     *Sprawdzanie, czy na danym biegu można zwiekszyc/zmniejszyć predkosc na tempomacie
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
     *Zmiana biegów
     */
    public static void setGear(KeyEvent key, ArrayList<Boolean> listOfGears, ArrayList<Circle> listOfGearsControls, ArrayList<Text> listOfGearsCaption, Color mainColor){
        if(key.getCode() == KeyCode.NUMPAD8) {
            for (int i = 0; i < listOfGears.size(); i++) {
                if (Clutch.getIsOn()) {
                    if(listOfGears.get(i) && i != 6 && i > 0 && Gears.isGearInProperRange(listOfGears) || (listOfGears.get(i) && i == 0 && Accelerator.getPower() == 0)) {
                        listOfGears.set(i, false);
                        listOfGears.set(i + 1, true);
                        System.out.println("Włączono bieg: " + "(" + listOfGears.get(i + 1) + ")" + listOfGearsCaption.get(i + 1).getText());
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
                    if(listOfGears.get(i) && i > 1 || (listOfGears.get(i) && i == 1 && Accelerator.getPower() == 0)) {
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

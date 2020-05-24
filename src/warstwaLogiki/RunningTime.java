package warstwaLogiki;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
/**
 *
 *  Klasa jest odpowiedzialna za zarzadzanie czasem oraz silnikiem w programie
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class RunningTime {
    private static long startCountingEngine;
    private static boolean IsEngineOn = false;
    private static Timeline clock;

    /**
     * Rozpoczecie liczenia czasu w momencie wlaczenia silnika
     */
    public static void startCountingTimeForEngine() {
        startCountingEngine = Instant.now().toEpochMilli();
    }

    /**
     * Pobieranie czasu dzialania silnika
     * @return - zwracana wartosc to czas dzialania silnika samochodowego
     */
    public static double getRunningEngineTime() {
        long end = Instant.now().toEpochMilli();
        if(IsEngineOn)
            return Math.round(((end - startCountingEngine)/1000.0)*100.0)/100.0/60.0;
        else
            return 0;
    }

    /**
     * Metoda ustawiajaca czas w odpowiednim formacie oraz o odpowiedniej wartosci
     * @param timeText - miejsce, w ktorym czas bedzie pokazywany
     * @param color - kolor, w jakim czas bedzie pokazany (kolor dodatkowy programu)
     * @param englishFormat - flaga informujaca o formacie godziny ("true" - 12-godzinny, "false" - 24-godzinny)
     */
    public static void showTime(Text timeText, Color color, boolean englishFormat){
        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            if(englishFormat) {
                DateTimeFormatter dateFormer = DateTimeFormatter.ofPattern("hh:mm:ss a");
                timeText.setText(currentTime.format(dateFormer));
            }
            else {
                DateTimeFormatter dateFormer = DateTimeFormatter.ofPattern("HH:mm:ss");
                timeText.setText(currentTime.format(dateFormer));
            }
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeText.setFill(color);
        timeText.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /**
     * Metoda wlaczajaca/wylaczajaca silnik
     * @param bool - flaga informujaca czy silnik ma byc wylaczony("false") lub wlaczony("true")
     */
    public static void setIsEngineOn(boolean bool) {IsEngineOn = bool;}

    /**
     * Metoda zwraca stan silnika
     * @return - zwraca wartosc "true" jesli silnik jest wlaczony, w przciwnym wypadku - "false"
     */
    public static boolean getIsEngineOn() {return IsEngineOn;}

    /**
     * Metoda zatrzymujaca odliczanie zegaru
     */
    public static void stopClock() {clock.stop();}
}
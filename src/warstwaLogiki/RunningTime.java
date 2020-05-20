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
 *  Odpowiada za liczenie czasu
 *
 */
public class RunningTime {
    private static long startCountingEngine;
    private static boolean IsEngineOn = false;
    private static Timeline clock;


    public static void startCountingTimeForEngine() {
        startCountingEngine = Instant.now().toEpochMilli();
    }

    public static double getRunningEngineTime() {
        long end = Instant.now().toEpochMilli();
        if(IsEngineOn)
            return Math.round(((end - startCountingEngine)/1000.0)*100.0)/100.0/60.0;
        else
            return 0;
    }

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

    public static void setIsEngineOn(boolean bool) {IsEngineOn = bool;}
    public static boolean getIsEngineOn() {return IsEngineOn;}
    public static void stopClock() {clock.stop();}
}

package warstwaDanych;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 *  Odpowiada za przebiegi w samochodzie
 *
 */
public class Mileage {
    private double totalMileage = 0;
    private double dailyMileage = 0;
    private double userMileage = 0;
    private LocalDate data = LocalDate.now();
    /**
     *
     *  Dodaje przebyty dystans do wszystkich przebiegow
     *
     */
    public void addToMileage(double distance) {
        totalMileage += distance;
        if(LocalDateTime.now().getHour() == 0) {
            if(LocalDateTime.now().getMinute() == 0) {
                if(LocalDateTime.now().getSecond() == 0) {
                    dailyMileage = 0;
                }
            }
        }

        dailyMileage += distance;
        userMileage += distance;
    }
    /**
     *
     *  Zwraca przebieg calkowity
     *
     */
    public double getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(double totalMileage) {
        this.totalMileage = totalMileage;
    }
    /**
     *
     *  Zwraca przebieg dzienny
     *
     */
    public double getDailyMileage() {
        return dailyMileage;
    }
    /**
     *
     *  Ustawia przebieg dzienny
     *
     */
    public void setDailyMileage(double dailyMileage) {
        this.dailyMileage = dailyMileage;
    }
    /**
     *
     *  Zwraca przebieg uzytkownika
     *
     */
    public double getUserMileage() {
        return userMileage;
    }
    /**
     *
     *  Ustawia przebieg uzytkownika
     *
     */
    public void setUserMileage(double userMileage) {
        this.userMileage = userMileage;
    }
    /**
     *
     *  Sprawdza czy dzien w przebiegu dziennym z pliku XML jest taki sam jak
     *  dzisiejszy, jeśli nie to zeruje przebieg dzienny
     */
    public void checkData() {
        if(data.getDayOfMonth() != LocalDateTime.now().getDayOfMonth()) {
            setDailyMileage(0.0);
            this.data = LocalDate.now();
        }
    }
}

package warstwaDanych;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *  Klasa odpowiada za przebiegi w samochodzie
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class Mileage {
    private double totalMileage = 0;
    private double dailyMileage = 0;
    private double userMileage = 0;
    private LocalDate data = LocalDate.now();
    /**
     * Metoda dodaje przebyty dystans do wszystkich przebiegow
     * @param distance - Dystans, ktory dodajemy pod wszystkich dystansow
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
     * Metoda zwraca przebieg calkowity pojazdu
     * @return Przebieg calkowity pojazdu
     */
    public double getTotalMileage() {
        return totalMileage;
    }
    /**
     * Metoda zwraca przebieg dzienny pojazdu
     * @return Przebieg dzienny pojazdu
     */
    public double getDailyMileage() {
        return dailyMileage;
    }
    /**
     *
     *  Ustawia przebieg dzienny
     *
     */
    /**
     * Metoda ustawia przebieg dzienny
     * @param dailyMileage - Przebieg, ktory ma byc ustawiony
     */
    public void setDailyMileage(double dailyMileage) {
        this.dailyMileage = dailyMileage;
    }
    /**
     * Metoda zwraca przebieg uzytkownika pojazdu
     * @return Przebieg uzytkownika pojazdu
     */
    public double getUserMileage() {
        return userMileage;
    }
    /**
     * Metoda ustawia przebieg uzytkownika
     * @param userMileage - Przebieg, ktory ma byc ustawiony
     */
    public void setUserMileage(double userMileage) {
        this.userMileage = userMileage;
    }
    /**
     *  Metoda sprawdza czy dzien w przebiegu dziennym z pliku XML jest taki sam jak
     *  dzisiejszy, jeśli nie to zeruje przebieg dzienny
     */
    public void checkData() {
        if(data.getDayOfMonth() != LocalDateTime.now().getDayOfMonth()) {
            setDailyMileage(0.0);
            this.data = LocalDate.now();
        }
    }
}

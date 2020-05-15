package warstwaDanych;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Mileage {
    private double totalMileage = 0;
    private double dailyMileage = 0;
    private double userMileage = 0;
    private LocalDate data = LocalDate.now();

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

    public double getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(double totalMileage) {
        this.totalMileage = totalMileage;
    }

    public double getDailyMileage() {
        return dailyMileage;
    }

    public void setDailyMileage(double dailyMileage) {
        this.dailyMileage = dailyMileage;
    }

    public double getUserMileage() {
        return userMileage;
    }

    public void setUserMileage(double userMileage) {
        this.userMileage = userMileage;
    }

    public void checkData() {
        if(data.getDayOfMonth() != LocalDateTime.now().getDayOfMonth()) {
            setDailyMileage(0.0);
            this.data = LocalDate.now();
        }
    }
}

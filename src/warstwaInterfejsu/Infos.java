package warstwaInterfejsu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Klasa odpowiedziana za okna informacyjne o programie i pojezdzie
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class Infos {
    /**
     * Metoda wyswietla nowe okno z informacjami dotyczacymi programu
     * @param title  Tytul okna
     */
    public static void displayProgramInfo(String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(800);
        window.setMinHeight(400);
        GridPane grid = new GridPane();
        Text text = new Text("Program ma za zadanie ukazać \"symulację\" samochodu osobowego.\n" +
                "Użytkownik do dyspozycji ma standardowe wyposażenie, takie jak: \n" +
                "- pedał gazu (strzałka w górę)\n" +
                "- pedał hamulca (strzałka w dół)\n" +
                "- pedał sprzęgła (klawisz\"C\")\n" +
                "- prawy/lewy kierunkowskaz (strzałki prawo/lewo)\n"+
                "- zmiana biegu w górę (klawisz \"NUM8\")\n" +
                "- zmiana biegu w dół (klawisz \"NUM2\")\n" +
                "- zapięcie pasów (klawisz \"P\")\n" +
                "- użycie klaksonu (klawisz \"K\")\n" +
                "- włączenie silnika\n" +
                "- obsługa świateł awaryjnych\n" +
                "- obsługa tempomatu \n" +
                "- obsługa świateł pozycyjnych/mijania/drogowych/dziennych/przeciwmgielnych\n" +
                "Ponadto istnieje możliwość odtwarzania utworów *.mp3, dodawania ich oraz usuwania." +
                "");
        text.setFont(new Font("Verdana", 12));
        grid.getChildren().add(text);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
    }
    /**
     * Metoda wyswietla nowe okno z informacjami dotyczacymi auta
     * @param title  Tytul okna
     */
    public static void displayAutoInfo(String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(350);
        window.setMinHeight(400);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Text text = new Text("Producent: Fiat\n" +
                "Model: 126p\n" +
                "Kraj produkcji: Polska\n" +
                "Rok produkcji: 1980\n" +
                "Liczba drzwi: 3\n" +
                "Rodzaj paliwa: Benzyna\n" +
                "Pojemność silnika: 703cm^3\n" +
                "Moc silnika: 25,2KM\n" +
                "Skrzynia biegów: 6-biegowa manualna\n" +
                "Napęd: Tylny\n" +
                "Wymiary: \n" +
                "  Długość: 3054 mm\n" +
                "  Szerokość: 1377 mm\n" +
                "  Wysokość: 1335 mm\n" +
                "Masa własna: 590kg: \n" +
                "Liczba miejsc: 4\n" +
                "Ladowność: 320kg\n" +
                "Bagażnik: 100L\n" +
                "");
        text.setFont(new Font("Verdana", 12));
        grid.getChildren().add(text);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
    }
    /**
     * Metoda wyswietla nowe okno z informacjami dotyczacymi gwarancji auta
     * @param title  Tytul okna
     */
    public static void displayWarrantyInfo(String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(350);
        window.setMinHeight(200);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        LocalDate warrantyDate = LocalDate.of(2020, 7,21);
        Text text = new Text("Gwarancja trwa do: " + warrantyDate.toString() + "\n" +
                             "Do końca gwarancji zostało: "+ DAYS.between(LocalDate.now(), warrantyDate) + " dni");
        text.setFont(new Font("Verdana", 12));
        grid.getChildren().add(text);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
    }
}

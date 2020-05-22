package warstwaInterfejsu;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Infos {
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
}

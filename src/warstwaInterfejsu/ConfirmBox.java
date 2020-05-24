package warstwaInterfejsu;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *  Klasa wyswietla nowe okno z podanym komunikatem i zwraca odp. Tak lub Nie
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class ConfirmBox {
    /**
     * Metoda wyswietla nowe okno z podanych tytulem i komunikatem, uzytkownik wybiera jedna z opcji: TAK lub NIE
     * @param title - Tytul okna
     * @param message - Wiadomosc
     * @return Odpowiedz uzytkownika - TAK lub NIE (true/false)
     */
    public static boolean display(String title, String message) {
        AtomicBoolean answer = new AtomicBoolean(false);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(500);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("TAK");
        Button noButton = new Button("NIE");

        yesButton.setOnAction(e -> {
            answer.set(true);
            window.close();
        });
        noButton.setOnAction(e -> {
            answer.set(false);
            window.close();
        });

        HBox layout = new HBox();


        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer.get();
    }

}
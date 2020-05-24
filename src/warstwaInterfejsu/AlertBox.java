package warstwaInterfejsu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Klasa wyswietla nowe okno z podanym komunikatem
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class AlertBox {
    /**
     * Metoda wyswietla nowe okno z podanych tytulem i komunikatem
     * @param title - Tytul okna
     * @param message - Wiadomosc
     */
    public static void display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(500);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);

        Button button = new Button("OK");
        button.setOnAction(e->window.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}

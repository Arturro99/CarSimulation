package warstwaInterfejsu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class InsertingBox {
    static ArrayList<TextField>list = new ArrayList<>();
    static TextField textTitle = new TextField();
    static TextField textAlbum = new TextField();
    static TextField textDuration = new TextField();
    static TextField textId = new TextField();
    static TextField textPerformer = new TextField();
    static TextField textPath = new TextField();

    public static void display(String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(500);
        window.setMinHeight(300);

        Label labelTitle = new Label();
        Label labelPerformer = new Label();
        Label labelAlbum = new Label();
        Label labelDuration = new Label();
        Label labelId = new Label();
        Label labelPath = new Label();
        list.add(textTitle);
        list.add(textAlbum);
        list.add(textDuration);
        list.add(textId);
        list.add(textPerformer);
        list.add(textPath);
        labelTitle.setText("Tytuł: ");
        labelPerformer.setText("Wykonawca: ");
        labelAlbum.setText("Album: ");
        labelDuration.setText("Czas trwania: ");
        labelId.setText("Id: ");
        labelPath.setText("Ścieżka do pliku: ");
        Button button = new Button("Zapisz");


        GridPane.setConstraints(labelTitle, 0, 0);
        GridPane.setConstraints(labelPerformer, 0, 1);
        GridPane.setConstraints(labelAlbum, 0, 2);
        GridPane.setConstraints(labelDuration, 0, 3);
        GridPane.setConstraints(labelId, 0, 4);
        GridPane.setConstraints(labelPath, 0, 5);
        GridPane.setConstraints(textTitle, 1, 0);
        GridPane.setConstraints(textPerformer, 1, 1);
        GridPane.setConstraints(textAlbum, 1, 2);
        GridPane.setConstraints(textDuration, 1, 3);
        GridPane.setConstraints(textId, 1, 4);
        GridPane.setConstraints(textPath, 1, 5);
        GridPane.setConstraints(button, 2, 6);

        button.setOnAction(e->{
            if(validate())
                window.close();
            else
                AlertBox.display("Alert", "Wszystkie pola muszą być uzupełnione!");
        });

        GridPane layout = new GridPane();
        layout.getChildren().addAll(labelTitle, labelDuration, labelAlbum, labelId, labelPath, labelPerformer,
                textAlbum, textDuration, textId, textPath, textPerformer, textTitle, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    private static boolean validate(){
        for(TextField i : list){
            if(i.getText().equals("")){
                return false;
            }
        }
        return true;
    }

    public static TextField getTextTitle() {
        return textTitle;
    }

    public static TextField getTextAlbum() {
        return textAlbum;
    }

    public static TextField getTextDuration() {
        return textDuration;
    }

    public static TextField getTextId() {
        return textId;
    }

    public static TextField getTextPerformer() {
        return textPerformer;
    }

    public static TextField getTextPath() {
        return textPath;
    }
}

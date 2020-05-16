package warstwaInterfejsu;

import javafx.css.Styleable;
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
import warstwaDanych.OperateOnDataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class InsertingBox {
    static ArrayList<TextField>list = new ArrayList<>();
    static TextField textTitle;
    static TextField textAlbum;
    static TextField textDuration;
    static TextField textId;
    static TextField textPerformer;

    public static void displayNewSongStage(String title, OperateOnDataBase operateOnDataBase){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(500);
        window.setMinHeight(300);

        textId = new TextField();
        textPerformer = new TextField();
        textDuration = new TextField();
        textAlbum = new TextField();
        textTitle = new TextField();
        Label labelTitle = new Label();
        Label labelPerformer = new Label();
        Label labelAlbum = new Label();
        Label labelDuration = new Label();
        Label labelId = new Label();
        list.clear();
        list.add(textTitle);
        list.add(textAlbum);
        list.add(textDuration);
        list.add(textId);
        list.add(textPerformer);
        labelTitle.setText("Tytuł: ");
        labelPerformer.setText("Wykonawca: ");
        labelAlbum.setText("Album: ");
        labelDuration.setText("Czas trwania: ");
        labelId.setText("Id: ");
        Button button = new Button("Zapisz");


        GridPane.setConstraints(labelTitle, 0, 0);
        GridPane.setConstraints(labelPerformer, 0, 1);
        GridPane.setConstraints(labelAlbum, 0, 2);
        GridPane.setConstraints(labelDuration, 0, 3);
        GridPane.setConstraints(labelId, 0, 4);
        GridPane.setConstraints(textTitle, 1, 0);
        GridPane.setConstraints(textPerformer, 1, 1);
        GridPane.setConstraints(textAlbum, 1, 2);
        GridPane.setConstraints(textDuration, 1, 3);
        GridPane.setConstraints(textId, 1, 4);
        GridPane.setConstraints(button, 2, 6);

        button.setOnAction(e->{
            if(validate()) {
                window.close();
                try {
                    operateOnDataBase.insert(getTextTitle(),getTextPerformer(), getTextAlbum(),
                            getTextDuration(), getTextId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            else
                AlertBox.display("Alert", "Wszystkie pola muszą być uzupełnione!");
        });

        GridPane layout = new GridPane();
        layout.getChildren().addAll(labelTitle, labelDuration, labelAlbum, labelId, labelPerformer,
                textAlbum, textDuration, textId, textPerformer, textTitle, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

    }

    public static void displayDeleteSongStage(String title, OperateOnDataBase operateOnDataBase){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
        window.setMinHeight(200);

        textId = new TextField();
        Label labelId = new Label();
        labelId.setText("Podaj id utworu, który zamierzasz usunąć: ");
        Button button = new Button("Zapisz");
        list.clear();
        list.add(textId);

        GridPane.setConstraints(labelId, 0, 0);
        GridPane.setConstraints(textId, 1, 0);
        GridPane.setConstraints(button, 1, 1);

        button.setOnAction(e-> {
                    if (validate()) {
                        window.close();
                        try {
                            operateOnDataBase.delete(getTextId());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } else
                        AlertBox.display("Alert", "Wskazane pole musi być uzupełnione!");
                });

        GridPane layout = new GridPane();
        layout.getChildren().addAll(labelId, textId, button);
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

    public static String getTextTitle() {
        return textTitle.getText();
    }

    public static String getTextAlbum() {
        return textAlbum.getText();
    }

    public static String getTextDuration() {
        return textDuration.getText();
    }

    public static Integer getTextId() {
        return Integer.parseInt(textId.getText());
    }

    public static String getTextPerformer() {
        return textPerformer.getText();
    }

}

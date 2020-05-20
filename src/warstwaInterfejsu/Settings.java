package warstwaInterfejsu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import warstwaLogiki.RunningTime;

public class Settings {
    private boolean darkOn = false;
    boolean lightOn = false;
    private boolean retroOn = true;
    private boolean englishFormatOn = false;
    private boolean normalFormatOn = true;
    public void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ustawienia");
        window.setWidth(700);
        window.setHeight(400);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        Text themeText = new Text("Wybierz motyw:");
        RadioButton dark = new RadioButton("Motyw ciemny");
        RadioButton light = new RadioButton("Motyw jasny");
        RadioButton retro = new RadioButton("Motyw retro");
        Text formatText = new Text("Wybierz tryb zegarka:");
        RadioButton englishFormat = new RadioButton("Format 12-godzinny");
        RadioButton normalFormat = new RadioButton("Format 24-godzinny");
        ToggleGroup groupS = new ToggleGroup();
        ToggleGroup groupL = new ToggleGroup();

        dark.setToggleGroup(groupL);
        light.setToggleGroup(groupL);
        retro.setToggleGroup(groupL);
        englishFormat.setToggleGroup(groupS);
        normalFormat.setToggleGroup(groupS);

        grid.getChildren().addAll(themeText, dark, light, retro, formatText, englishFormat, normalFormat);
        grid.setHgap(200);

        grid.setConstraints(themeText, 0, 0);
        grid.setConstraints(dark, 0, 1);
        grid.setConstraints(light, 0, 2);
        grid.setConstraints(retro, 0, 3);
        grid.setConstraints(formatText, 1, 0);
        grid.setConstraints(englishFormat, 1, 1);
        grid.setConstraints(normalFormat, 1, 2);

        normalFormat.setSelected(normalFormatOn);
        englishFormat.setSelected(englishFormatOn);
        dark.setSelected(darkOn);
        light.setSelected(lightOn);
        retro.setSelected(retroOn);
        dark.setOnAction(e->{
            this.darkOn = true;
            this.lightOn = false;
            this.retroOn = false;
            Gui.mainColor = Color.DIMGRAY;
            Gui.additionalColor = Color.DARKBLUE;
            Gui.drawAll();
            Gui.showVelocity();
            Gui.showImages(Gui.mainColor);
            RunningTime.stopClock();
            RunningTime.showTime(Gui.time, Gui.additionalColor, Gui.englishSystem);
            Gui.grid.setStyle("-fx-background-color: DIMGRAY ");
            Gui.images.setStyle("-fx-background-color: DIMGRAY ");
            Gui.listOfGearsControls.get(1).setFill(Color.RED);
        });
        light.setOnAction(e->{
            this.lightOn = true;
            this.retroOn = false;
            this.darkOn = false;
            Gui.mainColor = Color.AZURE;
            Gui.additionalColor = Color.CORAL;
            Gui.drawAll();
            Gui.showVelocity();
            Gui.showImages(Gui.mainColor);
            RunningTime.stopClock();
            RunningTime.showTime(Gui.time, Gui.additionalColor, Gui.englishSystem);
            Gui.grid.setStyle("-fx-background-color: AZURE ");
            Gui.images.setStyle("-fx-background-color: AZURE ");
            Gui.listOfGearsControls.get(1).setFill(Color.RED);
        });
        retro.setOnAction(e->{
            this.retroOn = true;
            this.lightOn = false;
            this.darkOn = false;
            Gui.mainColor = Color.CRIMSON;
            Gui.additionalColor = Color.BLACK;
            Gui.drawAll();
            Gui.showVelocity();
            Gui.showImages(Gui.mainColor);
            RunningTime.stopClock();
            RunningTime.showTime(Gui.time, Gui.additionalColor, Gui.englishSystem);
            Gui.grid.setStyle("-fx-background-color: CRIMSON ");
            Gui.images.setStyle("-fx-background-color: CRIMSON ");
            Gui.listOfGearsControls.get(1).setFill(Color.RED);
        });
        englishFormat.setOnAction(e->{
            this.englishFormatOn = true;
            this.normalFormatOn = false;
            if(!Gui.englishSystem) {
                Gui.englishSystem = true;
                RunningTime.stopClock();
                RunningTime.showTime(Gui.time, Gui.additionalColor, Gui.englishSystem);
            }
        });
        normalFormat.setOnAction(e->{
            this.normalFormatOn = true;
            this.englishFormatOn = false;
            if(Gui.englishSystem) {
                Gui.englishSystem = false;
                RunningTime.stopClock();
                RunningTime.showTime(Gui.time, Gui.additionalColor, Gui.englishSystem);
            }
        });


        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
    }

    public Color getMainColor() {
        if(darkOn)
            return Color.DIMGRAY;
        else if(retroOn)
            return Color.CRIMSON;
        else
            return Color.AZURE;
    }
    public Color getAdditionalColor() {
        if(darkOn)
            return Color.DARKBLUE;
        else if(retroOn)
            return Color.BLACK;
        else
            return Color.CORAL;
    }
    public boolean getEnglishSystem() {return englishFormatOn;}
}

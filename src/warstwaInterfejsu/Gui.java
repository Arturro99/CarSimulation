package warstwaInterfejsu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import warstwaDanych.*;
import warstwaLogiki.*;
import warstwaLogiki.pl.exceptions.*;
import warstwaLogiki.pl.lights.*;
import warstwaLogiki.pl.pedals.*;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Gui extends Application {
    static BorderPane wholeGrid = new BorderPane();

    static boolean isTempomatOn = false;
    static boolean isFogLightsOn = false;
    boolean isMusicPlaying = false;
    static boolean englishSystem = false;
    static int whichLightOn = 0;
    int tempomatSpeedValue = 0;
    boolean isDBworking = false;
    Button tempomatButton = new Button("Włącz tempomat");
    Button plusButton = new Button("+");
    Button minusButton = new Button("-");

    ////////////////MP3//////////////////////
    Text radioTitle = new Text("Odtwarzacz MP3");
    Text songText = new Text("Piosenka");
    Button previousSong = new Button("Poprzednia");
    Button nextSong = new Button("Następna");
    Button startSong = new Button("Start");
    Button pauseSong = new Button("Pause");
    Button stopSong = new Button("Stop");
    Button addSong = new Button("Dodaj utwór");
    Button deleteSong = new Button("Usuń utwór");
    ///////////////Kierunkowskazy//////////////
    static Polygon rightArrow = new Polygon();
    static Polygon leftArrow = new Polygon();
    ///////////////Swiatla/////////////////////
    static ImageView dlugie, dlugieczarnobiale, dzienne, dzienneczarnobiale, mijania, mijaniaczarnobiale, pozycyjne, pozycyjneczarnobiale,
    przeciwmgielne, przeciwmgielneczarnobiale, tempomat, tempomatczarnobiale;

    static {
        try {
            dlugie = new ImageView(new Image(new FileInputStream("swiatla\\dlugie.png")));
            dlugieczarnobiale = new ImageView(new Image(new FileInputStream("swiatla\\dlugieczarnobiale.png")));
            dzienne = new ImageView(new Image(new FileInputStream("swiatla\\dzienne.png")));
            dzienneczarnobiale = new ImageView(new Image(new FileInputStream("swiatla\\dzienneczarnobiale.png")));
            mijania = new ImageView(new Image(new FileInputStream("swiatla\\mijania.png")));
            mijaniaczarnobiale = new ImageView(new Image(new FileInputStream("swiatla\\mijaniaczarnobiale.png")));
            pozycyjne = new ImageView(new Image(new FileInputStream("swiatla\\pozycyjne.png")));
            pozycyjneczarnobiale = new ImageView(new Image(new FileInputStream("swiatla\\pozycyjneczarnobiale.png")));
            przeciwmgielne = new ImageView(new Image(new FileInputStream("swiatla\\przeciwmgielne.png")));
            przeciwmgielneczarnobiale = new ImageView(new Image(new FileInputStream("swiatla\\przeciwmgielneczarnobiale.png")));
            tempomat = new ImageView(new Image(new FileInputStream("swiatla\\tempomat.png")));
            tempomatczarnobiale = new ImageView(new Image(new FileInputStream("swiatla\\tempomatczarnobiale.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static VBox images = new VBox();

    //////////////Podpisy//////////////////////
    static Text velocity = new Text();
    Text avgSpeed = new Text();
    Text maxSpeed = new Text();
    Text travelTime = new Text();
    Text travelDistance = new Text();
    Text avgFuelConsumption = new Text();
    Text totalMileage = new Text();
    Text dailyMileage = new Text();
    Text userMileage = new Text();
    static Text time = new Text();
    Text engineSpeed = new Text();
    static Text gearsCaption = new Text();
    static Text tempomatSpeedText;

    ////////////////////////Interakcje z użytkownikiem////////////////////////////////////
    Button engineButton = new Button("Włącz silnik");
    Button emergencyLightsButton = new Button("Światła awaryjne");
    Button resetUserMileageButton = new Button("Zresetuj przebieg");
    RadioButton noLights = new RadioButton("Światła wyłączone");
    RadioButton positionLightsRadio = new RadioButton("Światła pozycyjne");
    RadioButton passingLightsRadio = new RadioButton("Światła mijania");
    RadioButton headlightsRadio = new RadioButton("Światła długie");
    RadioButton dayLightsRadio = new RadioButton("Światła dzienne");
    CheckBox fogLightsRadio = new CheckBox("Światła przeciwmgielne");
    ToggleGroup groupOfLights = new ToggleGroup();

    /////////////////////Listy pomocnicze do grupowania elementów/////////////////////////
    static ArrayList<Circle> listOfGearsControls = new ArrayList<>();
    ArrayList<Boolean> listOfGears = new ArrayList<>();
    static ArrayList<Text> listOfGearsCaption = new ArrayList<>();
    static ArrayList<Circle> diodes = new ArrayList<>();
    static ArrayList<Text> diodesCaption = new ArrayList<>();

    ////////////////////////Obiekty klas z innych plików///////////////////////////////////
    Accelerator accelerator = new Accelerator();
    Brake brake = new Brake();
    Clutch clutch = new Clutch();
    Indicator left = new Indicator(Side.left);
    Indicator right = new Indicator(Side.right);
    PositionLights positionLights = new PositionLights();
    PassingLights passingLights = new PassingLights();
    FogLights fogLights = new FogLights();
    Headlights headlights = new Headlights();
    DayLights dayLights = new DayLights();
    Mileage mileage = new Mileage();
    OperateOnFiles operateOnFiles = new OperateOnFiles();
    OperateOnDataBase operateOnDataBase = new OperateOnDataBase();
    ListOfSongs listOfSongs = new ListOfSongs();
    Media song;
    MediaPlayer mediaPlayer;
    Settings  settings = new Settings();

    ////////////Kolory w programie///////////////
    static Color mainColor;
    static Color additionalColor;

    static GridPane grid = new GridPane();

    @Override
    public void start(Stage stage) throws Exception {
        /////////////////////////////////////////////Wczytywanie danych z pliku////////////////////////////////
        try {
            mileage = operateOnFiles.loadFromXmlFile("Próba.xml", mileage);
            settings = operateOnFiles.loadFromXmlFile("Config.xml", settings);
        }
        catch(SuchFileDoesNotExist exc) {
            System.err.println(exc);
            AlertBox.display("Alert", "Nie podano pliku do wczytania danych lub podany plik nie istnieje");}

        try {
            operateOnDataBase.fromDBToListOfSongs(listOfSongs);
            operateOnFiles.saveToXmlFile("listOfSongs.xml", listOfSongs);
            System.out.println("Wczytano piosenki z bazy danych");
            isDBworking = true;
        } catch (SQLException e) {
            System.err.println(e);
            operateOnFiles.loadFromXmlFile("listOfSongs.xml", listOfSongs);
            System.out.println("Wczytano piosenki z pliku xml");
        }


        additionalColor = settings.getAdditionalColor();
        mainColor = settings.getMainColor();
        englishSystem = settings.getEnglishSystem();
        ////////////////////////////Ustawianie okienka////////////////////////////////////
        stage.setTitle("Fiat 126p");

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(20);
        grid.setVgap(10);

        //////////////////////Dodawanie paska menu////////////////////////////////////////////////////////
        Menu fileMenu = new Menu("Plik");
        MenuItem save = new MenuItem("Zapisz przebiegi");
        MenuItem exit = new MenuItem("Zakończ program");
        fileMenu.getItems().addAll(save, exit);
        Menu helpMenu = new Menu("Pomoc");
        MenuItem programInfo = new MenuItem("Informacje o programie");
        helpMenu.getItems().addAll(programInfo);
        Menu infoMenu = new Menu("Info");
        infoMenu.getItems().addAll(new MenuItem("Informacje o samochodzie"), new MenuItem("Gwarancja"));
        Menu settingsMenu = new Menu("Ustawienia");
        MenuItem settingsItem = new MenuItem("Dostosuj");
        Menu changeTheme = new Menu("Zmień motyw");
        Menu changeTimeFormat = new Menu("Zmień format czasu");
        MenuItem englishFormat = new MenuItem("12 - godzinny");
        MenuItem normalFormat = new MenuItem("24 - godzinny");
        changeTimeFormat.getItems().addAll(englishFormat, normalFormat);
        settingsMenu.getItems().addAll(settingsItem);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, settingsMenu, infoMenu, helpMenu);
        HBox menu = new HBox();
        menu.getChildren().add(menuBar);
        wholeGrid.setTop(menu);

        ///////////////////////////////Ustawianie biegów(kontrolki, podpisy)///////////////////////////////////////
        gearsCaption = new Text("Biegi:");
        gearsCaption.setFill(additionalColor);
        gearsCaption.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));

        for(int i = 0, x = 6; i < 7; i++, x--) {
            listOfGearsControls.add(new Circle(0, 0, 8));
            listOfGears.add(Boolean.FALSE);
            if(i != 0) listOfGearsCaption.add(new Text(String.valueOf(i)));
            else listOfGearsCaption.add(new Text("R"));
            listOfGearsCaption.get(i).setFill(additionalColor);
            listOfGearsCaption.get(i).setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        }
       tempomatSpeedText = new Text(tempomatSpeedValue +" km/h");

        ////////////////////////////////////////Ustawianie diód////////////////////////////////////////////////////
        diodesCaption.add(new Text("Światła hamowania"));
        diodesCaption.add(new Text("Przegrzanie silnika"));
        diodesCaption.add(new Text("Zapięte pasy"));
        diodesCaption.add(new Text("Wciśnięte sprzęgło"));
        for(int i = 0; i < 4; i++){
            diodes.add(new Circle(0, 0, 5));
            diodesCaption.get(i).setFill(additionalColor);
            diodesCaption.get(i).setFont(Font.font("Verdana", FontPosture.ITALIC, 10));
        }

        ///////////////////////////////Dodawanie świateł do grupy///////////////////////////////////////////////////
        positionLightsRadio.setToggleGroup(groupOfLights);
        passingLightsRadio.setToggleGroup(groupOfLights);
        headlightsRadio.setToggleGroup(groupOfLights);
        dayLightsRadio.setToggleGroup(groupOfLights);
        noLights.setToggleGroup(groupOfLights);
        images.getChildren().addAll(pozycyjneczarnobiale, mijaniaczarnobiale, dlugieczarnobiale, dzienneczarnobiale, przeciwmgielneczarnobiale, tempomatczarnobiale);
        ////////////////////////Pokazanie wszystkiego w okienku////////////////////////////////////////////////////
        drawAll();
        showVelocity();
        showStatistics();
        if(settings.getMainColor().toString().substring(2).equals("696969ff")) {
            grid.setStyle("-fx-background-color: DIMGRAY");
            images.setStyle("-fx-background-color: DIMGRAY");
        }
        else if(settings.getMainColor().toString().substring(2).equals("f0ffffff")){
            grid.setStyle("-fx-background-color: AZURE");
            images.setStyle("-fx-background-color: AZURE");
        }
        else{
            grid.setStyle("-fx-background-color: CRIMSON");
            images.setStyle("-fx-background-color: CRIMSON");
        }

        ////////////////////////////////////Ustawienie braku skupienia na elementach///////////////////////////////
        noLights.setFocusTraversable(false);
        passingLightsRadio.setFocusTraversable(false);
        dayLightsRadio.setFocusTraversable(false);
        fogLightsRadio.setFocusTraversable(false);
        headlightsRadio.setFocusTraversable(false);
        engineButton.setFocusTraversable(false);
        emergencyLightsButton.setFocusTraversable(false);
        resetUserMileageButton.setFocusTraversable(false);
        tempomatButton.setFocusTraversable(false);
        plusButton.setFocusTraversable(false);
        minusButton.setFocusTraversable(false);
        nextSong.setFocusTraversable(false);
        previousSong.setFocusTraversable(false);
        pauseSong.setFocusTraversable(false);
        startSong.setFocusTraversable(false);
        stopSong.setFocusTraversable(false);
        addSong.setFocusTraversable(false);
        deleteSong.setFocusTraversable(false);

        noLights.setSelected(true); //domyślnie -> światła są wyłączone
        listOfGears.set(1, true);   //domyślnie -> włączony pierwszy bieg
        listOfGearsControls.get(1).setFill(Color.RED); //-------||-----------
        //noLights.setFocusTraversable(false);
        //fogLightsRadio.setFocusTraversable(false);

        /////////////////////////////////Ustawianie w odpowiednim miejscu na siatce////////////////////////////////
        ///////////////////////KIERUNKOWSKAZY I PRĘDKOŚĆ
        GridPane.setConstraints(leftArrow, 0, 0);
        GridPane.setConstraints(rightArrow, 3, 0);
        GridPane.setConstraints(velocity, 1, 0, 2, 1);

        ///////////////////////PRZYCISKI
        HBox buttonsHBox = new HBox();
        buttonsHBox.setSpacing(5);
        buttonsHBox.getChildren().addAll(engineButton, emergencyLightsButton);
        GridPane.setConstraints(buttonsHBox, 1,2);
        GridPane.setConstraints(resetUserMileageButton, 1,5);

        /////////////////////////ŚWIATŁA
        VBox lightsVBox = new VBox();
        lightsVBox.setSpacing(5);
        lightsVBox.getChildren().addAll(noLights, positionLightsRadio, passingLightsRadio, headlightsRadio, dayLightsRadio, fogLightsRadio);
        GridPane.setConstraints(lightsVBox, 0, 3);

        //////////////////////////STATYSTYKI
        VBox statisticVBox = new VBox();
        statisticVBox.setSpacing(5);
        statisticVBox.getChildren().addAll(avgSpeed, maxSpeed, travelTime, travelDistance, avgFuelConsumption);
        GridPane.setConstraints(statisticVBox, 1, 3);

        //////////////////////////PRZEBIEGI
        VBox mileageVBox = new VBox();
        mileageVBox.setSpacing(5);
        mileageVBox.getChildren().addAll(totalMileage, dailyMileage, userMileage);
        GridPane.setConstraints(mileageVBox, 1, 4);

        //////////////////////////BIEGI
        VBox gearsOne = new VBox();
        gearsOne.setSpacing(7.2);
        VBox gearsTwo = new VBox();
        gearsTwo.setSpacing(5);

        for(int i = listOfGearsControls.size() - 1; i >= 0; i--) {
            gearsOne.getChildren().add(listOfGearsControls.get(i));
            gearsTwo.getChildren().add(listOfGearsCaption.get(i));
        }

        HBox gearsThree = new HBox();
        gearsThree.setSpacing(6);
        gearsThree.getChildren().addAll(gearsOne,gearsTwo);
        VBox gearsVBox =  new VBox();
        gearsVBox.setSpacing(5);
        gearsVBox.getChildren().addAll(gearsCaption, gearsThree);

        GridPane.setConstraints(gearsVBox, 3, 3, 4, 4);

        //////////////////////////DIODY
        VBox diodesOne = new VBox();
        diodesOne.setSpacing(7.2);
        VBox diodesTwo = new VBox();
        diodesTwo.setSpacing(5);
        HBox diodesHBox = new HBox();
        diodesHBox.setSpacing(3);

        for(int i = 0; i < diodes.size(); i++){
            diodesOne.getChildren().add(diodes.get(i));
            diodesTwo.getChildren().add(diodesCaption.get(i));
        }

        diodesHBox.getChildren().addAll(diodesOne,diodesTwo);
        GridPane.setConstraints(diodesHBox, 0, 4);

        ////////////////////////ZEGAR
        GridPane.setConstraints(time, 3, 1, 4, 2);
        RunningTime.showTime(time, additionalColor, englishSystem);
        ////////////////////////OBROTOMIERZ
        engineSpeed.setTextAlignment(TextAlignment.CENTER);
        GridPane.setConstraints(engineSpeed, 1, 1);

        ////////////////////////// TEMOPOMAT

        tempomatSpeedText.setFill(additionalColor);
        tempomatSpeedText.setFont(Font.font("Verdana", FontWeight.BOLD,  24));
        HBox tempomatHBox = new HBox();
        tempomatHBox.setSpacing(10);
        tempomatHBox.getChildren().addAll(tempomatButton, minusButton, plusButton, tempomatSpeedText);
        grid.setConstraints(tempomatHBox, 1,6);

        ////////////////////////// MP3
        HBox radioTitleHBox = new HBox();   radioTitleHBox.setSpacing(7.5);
        HBox radio1HBox = new HBox();   radio1HBox.setSpacing(7.5);
        HBox radio2HBox = new HBox();   radio2HBox.setSpacing(7.5);
        HBox radio3HBox = new HBox();   radio3HBox.setSpacing(7.5);
        VBox radioVBox = new VBox();    radioVBox.setSpacing(7.5);
        radioTitleHBox.getChildren().add(radioTitle);
        radio1HBox.getChildren().addAll(previousSong, nextSong);
        radio2HBox.getChildren().add(songText);
        radio3HBox.getChildren().addAll(pauseSong, startSong, stopSong, addSong, deleteSong);
        radioVBox.getChildren().addAll(radioTitleHBox, radio1HBox, radio2HBox, radio3HBox);
        grid.setConstraints(radioVBox, 1, 8);


        /////////////////////////////////Dodawanie elemenntów do siatki///////////////////////////////////////
        grid.getChildren().addAll(leftArrow, rightArrow, velocity, lightsVBox,
                diodesHBox, gearsVBox, statisticVBox, mileageVBox, buttonsHBox,
                resetUserMileageButton, tempomatHBox, radioVBox, time, engineSpeed);


        wholeGrid.setCenter(grid);
        wholeGrid.setLeft(images);
        Scene scene = new Scene(wholeGrid, 800, 650);
        stage.setScene(scene);
        stage.show();
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////

        AtomicBoolean areSeatbeltsPutOn = new AtomicBoolean(false); //zmienna informująca o zapiętych pasach

        /////////////////////////////////Obsługa kontrolki od pasów//////////////////////////////////////////
        Timer beltTimer = new Timer();
        beltTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(!areSeatbeltsPutOn.get() && RunningTime.getIsEngineOn()) {
                    diodes.get(2).setFill(Color.RED);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    diodes.get(2).setFill(mainColor);
                }
                else if(areSeatbeltsPutOn.get() && RunningTime.getIsEngineOn()){
                    diodes.get(2).setFill(Color.RED);
                }

            }
        }, 0, 1000);

        ////////////////////////////////////Obsługa obrotomierza///////////////////////////////////////////
        Timer engineSpeedTimer = new Timer();
        final boolean[] turnOff = {false};
        engineSpeedTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(RunningTime.getIsEngineOn()) {
                    engineSpeed.setText(String.valueOf(Math.round(Gears.calculateEngineSpeed(listOfGears))) + " RPM");

                    if (Math.round(Gears.calculateEngineSpeed(listOfGears)) < 800 && !listOfGears.get(1)) {
                        engineSpeed.setText("800 RPM");
                    }
                }
            }
        }, 0, 200);
        ////////////////////////////////////////////////////KeyEventy//////////////////////////////////////////////
        ArrayList<Timer>tmp1L = new ArrayList<>();  //pomocnicza pętla do przechowywania obiektów typu Timer
        ArrayList<Timer>tmp1R = new ArrayList<>();  // -------------------||----------------------------

        stage.addEventFilter(KeyEvent.KEY_PRESSED, (key) ->{   //Co się dzieje po wciśnięciu danego klawisza
            ////////////////////////Przyspieszenie/////////////////////////////////////////
            if(key.getCode() == KeyCode.UP && !Clutch.getIsOn() && RunningTime.getIsEngineOn() && Gears.canGoFurtherOnGear(listOfGears)) {
                try {
                    if(turnOff[0])
                        engineButton.fire();
                    Gears.checkEngineSpeed(listOfGears, diodes, mainColor);
                    accelerator.pressPedal(1);
                    showVelocity();
                } catch (TooFastException e) {
                    e.printStackTrace();
                }
            }
            //////////////////////Hamowanie//////////////////////////////////
            if(key.getCode() == KeyCode.DOWN && RunningTime.getIsEngineOn()){
                Gears.checkEngineSpeed(listOfGears, diodes, mainColor);
                brake.pressPedal(1);
                diodes.get(0).setFill(Color.RED);
                showVelocity();
            }
            /////////////////////Sprzęgło///////////////////////////////////
            if(key.getCode() == KeyCode.C && RunningTime.getIsEngineOn()){
                if(!Clutch.getIsOn() && !isTempomatOn) {
                    clutch.pressPedal(null);
                    diodes.get(3).setFill(Color.RED);
                }
                else {
                    clutch.releasePedal(null);
                    diodes.get(3).setFill(mainColor);
                }
            }
            //////////////////Bieg w górę/ w dół/////////////////////////////////////
            if(key.getCode() == KeyCode.NUMPAD8 || key.getCode() == KeyCode.NUMPAD2){
                Gears.setGear(key, listOfGears, listOfGearsControls, listOfGearsCaption, mainColor);
                Gears.checkEngineSpeed(listOfGears, diodes, mainColor);
            }
            /////////////////Kierunkowskaz w prawo//////////////////////////////////
            if(key.getCode() == KeyCode.RIGHT && RunningTime.getIsEngineOn()){
                song = new Media(new File("Indicator.mp3").toURI().toString());
                mediaPlayer = new MediaPlayer(song);
                Timer randomTimer = new Timer();
                tmp1R.add(randomTimer);
                if(!right.getIsOn() && !left.getIsOn()){
                    right.turnOn();
                    randomTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mediaPlayer.stop();
                            mediaPlayer.play();
                            drawArrows(Color.LAWNGREEN, Arrows.rightArrow);
                            try {
                                Thread.sleep(300);
                                mediaPlayer.stop();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mediaPlayer.play();
                            drawArrows(mainColor, Arrows.rightArrow);
                        }
                    }, 0,  1000);
                }
                else if(right.getIsOn() && !left.getIsOn()){
                    right.turnOff();
                    drawArrows(mainColor, Arrows.rightArrow);
                    for (Timer timer : tmp1R) timer.cancel();
                }
            }
            ////////////////////Kierunkowskaz w lewo//////////////////////////////////
            if(key.getCode() == KeyCode.LEFT && RunningTime.getIsEngineOn()){
                song = new Media(new File("Indicator.mp3").toURI().toString());
                mediaPlayer = new MediaPlayer(song);
                Timer randomTimer = new Timer();
                tmp1L.add(randomTimer);
                if(!left.getIsOn() && !right.getIsOn()){
                    left.turnOn();
                    randomTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mediaPlayer.stop();
                            mediaPlayer.play();
                            drawArrows(Color.LAWNGREEN, Arrows.leftArrow);
                            try {
                                Thread.sleep(300);
                                mediaPlayer.stop();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mediaPlayer.play();
                            drawArrows(mainColor, Arrows.leftArrow);
                        }
                    }, 0,  1000);
                }
                else if(left.getIsOn() && !right.getIsOn()){
                    left.turnOff();
                    drawArrows(mainColor, Arrows.leftArrow);
                    for (Timer timer : tmp1L) timer.cancel();
                }
            }
            //////////////////////////Pasy////////////////////////////////////////
            if(key.getCode() == KeyCode.P && RunningTime.getIsEngineOn()){
                if(!areSeatbeltsPutOn.get())
                    areSeatbeltsPutOn.set(true);
                else
                    areSeatbeltsPutOn.set(false);
            }
            ////////////////////////Klakson///////////////////////////////////
            if(key.getCode() == KeyCode.K){
                song = new Media(new File("ship.mp3").toURI().toString());
                mediaPlayer = new MediaPlayer(song);
                mediaPlayer.play();
            }
        });

        ArrayList<Timer>tmp2 = new ArrayList<>();
        stage.addEventFilter(KeyEvent.KEY_RELEASED, (key) ->{      //Co się dzieje po puszczeniu danego klawisza
            if(key.getCode() == KeyCode.UP){
                Timer randomTimer = new Timer();
                tmp2.add(randomTimer);
                randomTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (Accelerator.getPower() != 0) {
                            Gears.checkEngineSpeed(listOfGears, diodes, mainColor);
                            accelerator.releasePedal(1);
                            showVelocity();
                            if(tmp2.size() != 1) {
                                for(int i = 1; i < tmp2.size(); i++)
                                    tmp2.get(i).cancel();
                            }
                        }
                    }
                }, 0,  2000);
            }

            if(key.getCode() == KeyCode.DOWN && RunningTime.getIsEngineOn())
            {
                brake.releasePedal(null);
                diodes.get(0).setFill(mainColor);
            }

        });
        ////////////////////////Interakcje z innymi elementami w GUI//////////////////////////////

        ///////////Włączenie silnika////////////////////////
        ArrayList<Timer>tmp4 = new ArrayList<>();
        engineButton.setOnAction(e ->{
            if(!RunningTime.getIsEngineOn()) {
                Statistics.resetAvgAndMaxSpeed();
                Timer statisticTimer = new Timer();
                tmp4.add(statisticTimer);
                statisticTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        showStatistics();
                    }
                }, 0,  100);
                RunningTime.startCountingTimeForEngine();
                RunningTime.setIsEngineOn(true);
                engineButton.setText("Wyłącz silnik");
                engineSpeed.setVisible(true);
                mileage.checkData();
            }
            else {
                if(Accelerator.getPower() == 0) {
                    System.out.println(RunningTime.getRunningEngineTime());
                    RunningTime.setIsEngineOn(false);
                    engineButton.setText("Włącz silnik");
                    engineSpeed.setVisible(false);
                    if(tmp4.size() != 1) {
                        for(int i = 1; i < tmp4.size(); i++)
                            tmp4.get(i).cancel();
                    }
                    Statistics.resetAvgAndMaxSpeed();
                }
                else
                    AlertBox.display("Alert", "Nie możesz wyłączyć silnika pojazdu podczas jazdy!");
            }
        });
        ////////////Resetowanie przebiegu/////////////////
        resetUserMileageButton.setOnAction(e ->{
            mileage.setUserMileage(0.0);
        });

        //////////////////Światła awaryjne/////////////////////////////
        ArrayList<Timer>tmp3 = new ArrayList<>();
        emergencyLightsButton.setOnAction(e ->{
            if(Indicator.getNumberOfTurningSignals() != 2) {
                song = new Media(new File("Indicator.mp3").toURI().toString());
                mediaPlayer = new MediaPlayer(song);
                left.turnOn();
                right.turnOn();
                Timer randomTimer = new Timer();
                tmp3.add(randomTimer);
                randomTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mediaPlayer.stop();
                        mediaPlayer.play();
                        drawArrows(Color.LAWNGREEN, Arrows.leftArrow);
                        drawArrows(Color.LAWNGREEN, Arrows.rightArrow);
                        try {
                            Thread.sleep(300);
                            mediaPlayer.stop();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.play();
                        drawArrows(mainColor, Arrows.leftArrow);
                        drawArrows(mainColor, Arrows.rightArrow);
                    }
                }, 0, 1000);
            }
            else{
                for (Timer timer : tmp3) timer.cancel();
                drawArrows(mainColor, Arrows.leftArrow);
                drawArrows(mainColor, Arrows.rightArrow);
                left.turnOff();
                right.turnOff();
            }
        });

        //////////////Wyłączenie świateł///////////////
        noLights.setOnAction(e->{
            if(passingLights.getIsOn())
                passingLights.turnOff();
            else if(positionLights.getIsOn())
                positionLights.turnOff();
            else if(headlights.getIsOn())
                headlights.turnOff();
            else if(dayLights.getIsOn())
                dayLights.turnOff();
            if(fogLightsRadio.isSelected()) {
                fogLightsRadio.fire();
                fogLights.turnOff();
            }
            whichLightOn = 0;
            showImages(mainColor);
            grid.requestFocus();
        });

        ///////////Światła pozycyjne////////////////////////
        positionLightsRadio.setOnAction(e->{
            if(passingLights.getIsOn()) passingLights.turnOff();
            if(headlights.getIsOn()) headlights.turnOff();
            if(dayLights.getIsOn()) dayLights.turnOff();
            positionLights.turnOn();
            whichLightOn = 1;
            showImages(mainColor);
            grid.requestFocus();
        });

        ///////////Światła mijania////////////////////////
        passingLightsRadio.setOnAction(e->{
            if(positionLights.getIsOn()) positionLights.turnOff();
            if(headlights.getIsOn()) headlights.turnOff();
            if(dayLights.getIsOn()) dayLights.turnOff();
            passingLights.turnOn();
            whichLightOn = 2;
            showImages(mainColor);
            grid.requestFocus();
        });

        //////////////Światłą długie///////////////////////
        headlightsRadio.setOnAction(e->{
            if(positionLights.getIsOn()) positionLights.turnOff();
            if(passingLights.getIsOn()) passingLights.turnOff();
            if(dayLights.getIsOn()) dayLights.turnOff();
            headlights.turnOn();
            whichLightOn = 3;
            showImages(mainColor);
            grid.requestFocus();
        });

        ////////////////Światła dzienne/////////////////////
        dayLightsRadio.setOnAction(e->{
            if(positionLights.getIsOn()) positionLights.turnOff();
            if(passingLights.getIsOn()) passingLights.turnOff();
            if(headlights.getIsOn()) headlights.turnOff();
            dayLights.turnOn();
            whichLightOn = 4;
            showImages(mainColor);
            grid.requestFocus();
        });

        ////////////////Światła przeciwmgielne//////////////
        fogLightsRadio.setOnAction(e -> {
            if(fogLights.getIsOn()) fogLights.turnOff();
            else fogLights.turnOn();
            isFogLightsOn = fogLightsRadio.isSelected();
            showImages(mainColor);
            grid.requestFocus();
        });

        ////////////////Tempomat////////////////////////////
        ArrayList<Timer>tmp5= new ArrayList<>();
        tempomatButton.setOnAction(e -> {
            if(!isTempomatOn && RunningTime.getIsEngineOn()) {
                isTempomatOn = true;
                tempomatButton.setText("Wyłącz tempomat");
                showImages(mainColor);
                tempomatSpeedValue = (Accelerator.getPower() + 4) / 5 * 5;
                for (Timer timer : tmp2) timer.cancel();
                Timer tempomatTimer = new Timer();
                tmp5.add(tempomatTimer);
                tempomatTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (Accelerator.getPower() != tempomatSpeedValue) {
                            if (Accelerator.getPower() < tempomatSpeedValue)
                                try {
                                    accelerator.pressPedal(1);
                                } catch (TooFastException e) {
                                    e.printStackTrace();
                                }
                            else
                                accelerator.releasePedal(1);

                        }
                        showVelocity();
                    }
                }, 0, 100);
            }
            else {
                isTempomatOn = false;
                tempomatButton.setText("Włącz tempomat");
                    for (int i = 0; i < tmp5.size(); i++)
                        tmp5.get(i).cancel();
                Timer tmpTmer = new Timer();
                tmp2.clear();
                tmp2.add(tmpTmer);
                tmpTmer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (Accelerator.getPower() != 0) {
                            Gears.checkEngineSpeed(listOfGears, diodes, mainColor);
                            accelerator.releasePedal(1);
                            showVelocity();
                        }
                    }
                }, 0,  2000);
                showImages(mainColor);
            }
        });

        plusButton.setOnAction(e -> {
            if(isTempomatOn && Gears.canGoFurtherOnGear(listOfGears, tempomatSpeedValue + 5) && Math.round(Gears.calculateEngineSpeed(listOfGears)) >= 800 && !listOfGears.get(1)){
                    tempomatSpeedValue += 5;
            }
//            else {
//                tempomatSpeedValue = (Accelerator.getPower() + 4) / 5 * 5;
//            }


        });

        minusButton.setOnAction(e -> {
            if(isTempomatOn && Gears.canGoFurtherOnGear(listOfGears, tempomatSpeedValue - 5) && Math.round(Gears.calculateEngineSpeed(listOfGears)) > 800 && !listOfGears.get(1))
                tempomatSpeedValue -= 5;
//            else {
//                tempomatSpeedValue = (Accelerator.getPower() + 4) / 5 * 5;
//            }
        });
        //////////////////////////////Odtwarzacz MP3//////////////////////////////////////
        //AtomicInteger i = new AtomicInteger();
        AtomicInteger numberOfSong = new AtomicInteger();
        numberOfSong.set(-1);
        nextSong.setOnAction(e ->{
            if(isMusicPlaying)
                stopSong.fire();
            if(numberOfSong.get() < listOfSongs.getSize()-1)
                numberOfSong.getAndIncrement();
            songText.setText(listOfSongs.getSong(numberOfSong.get()).toStringGUI());
        });
        previousSong.setOnAction(e ->{
            if(isMusicPlaying)
                stopSong.fire();
            if(numberOfSong.get() > 0)
                numberOfSong.getAndDecrement();
            else {
                numberOfSong.set(0);
            }
            songText.setText(listOfSongs.getSong(numberOfSong.get()).toStringGUI());

        });
        startSong.setOnAction(e ->{
            if(!isMusicPlaying) {
                song = new Media(new File(listOfSongs.getSong(Integer.parseInt(numberOfSong.toString())).getTitle()).toURI().toString() + ".mp3");
                isMusicPlaying = true;
                mediaPlayer = new MediaPlayer(song);
            }
            mediaPlayer.play();
        });
        stopSong.setOnAction(e ->{
            mediaPlayer.stop();
            isMusicPlaying = false;
        });
        pauseSong.setOnAction(e ->{
            mediaPlayer.pause();
        });

        addSong.setOnAction(e -> {
            if(isDBworking)
                InsertingBox.displayNewSongStage("Podaj parametry dla nowego utworu", operateOnDataBase, listOfSongs);
            else
                AlertBox.display("Błąd bazy danych", "Baza danych jest niedostępna, nie można dodać piosenki.");
        });
        deleteSong.setOnAction(e -> {
            if(isDBworking)
                InsertingBox.displayDeleteSongStage("Podaj id utworu", operateOnDataBase, listOfSongs);
            else
                AlertBox.display("Błąd bazy danych", "Baza danych jest niedostępna, nie można usunąć piosenki.");
        });
///////////////////////////////////////////////////////Obsługa menu//////////////////////////////////////////////
        save.setOnAction(e->{
            operateOnFiles.saveToXmlFile("Próba.xml", mileage);
            operateOnFiles.saveToXmlFile("Config.xml", settings);
            operateOnFiles.saveToXmlFile("listOfSongs.xml", listOfSongs);
        });
        exit.setOnAction(e->{
            boolean answer = ConfirmBox.display("Alert", "Czy na pewno chcesz zamknąć program?");
            if(answer) {
                operateOnFiles.saveToXmlFile("Próba.xml", mileage);
                operateOnFiles.saveToXmlFile("Config.xml", settings);
                operateOnFiles.saveToXmlFile("listOfSongs.xml", listOfSongs);
                System.exit(0);
            }
        });
        settingsItem.setOnAction(e->{settings.display();});

        programInfo.setOnAction(e->Infos.displayProgramInfo("Info o programie"));

///////////////////////////////Obsługa zamykaniFsea symbolem "X" oraz skrótem ALT+F4/////////////////////////////////////
        stage.setOnCloseRequest(e -> {
            e.consume();
            boolean answer = ConfirmBox.display("Alert", "Czy na pewno chcesz zamknąć program?");
            if(answer) {
                operateOnFiles.saveToXmlFile("Próba.xml", mileage);
                operateOnFiles.saveToXmlFile("Config.xml", settings);
                operateOnFiles.saveToXmlFile("listOfSongs.xml", listOfSongs);
                System.exit(0);
            }
        });
    }

    /**
     *
     *     Rysuje wszystkie elementy kokpitu
     *
     */
    static void drawAll(){
        drawArrows(mainColor, Arrows.leftArrow);
        drawArrows(mainColor, Arrows.rightArrow);
        drawGears(mainColor);
        drawDiodes(mainColor);
    }

    /**
     *
     *     Rysuje kierunkowskazy
     *
     */
    private static void drawArrows(Color color, Arrows arrow){

        if(arrow == Arrows.leftArrow) {
            leftArrow.getPoints().addAll(100.0, 60.0,
                    100.0, 40.0,
                    40.0, 40.0,
                    40.0, 20.0,
                    0.0, 50.0,
                    40.0, 80.0,
                    40.0, 60.0);
            leftArrow.setFill(color);
            leftArrow.setStroke(Color.LAVENDER);
        }
        else {
            rightArrow.getPoints().addAll(100.0, 60.0,
                    100.0, 40.0,
                    160.0, 40.0,
                    160.0, 20.0,
                    200.0, 50.0,
                    160.0, 80.0,
                    160.0, 60.0);
            rightArrow.setFill(color);
            rightArrow.setStroke(Color.LAVENDER);
        }
    }

    /**
     *
     *     Pokazuje prędkość pojazdu
     *
     */
    static void showVelocity(){
        velocity.setText(Accelerator.getPower() + " km/h");
        velocity.setFill(additionalColor);
        velocity.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
    }

    /**
     *
     *     Pokazuje parametry pojazdu
     *
     */
    private void showStatistics() {
        avgSpeed.setText("Prędkość średnia: " + String.format("%.1f", Statistics.calculateAvgSpeed(Accelerator.getPower())) + " km/h");
        avgSpeed.setFill(additionalColor);
        avgSpeed.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        maxSpeed.setText("Prędkość maksymalna: " + Statistics.getMaxSpeed(Accelerator.getPower()) + " km/h");


        travelTime.setText("Czas podróży: " + String.format("%.2f", RunningTime.getRunningEngineTime()) + " minut");


        travelDistance.setText("Przebyty dystans: " + String.format("%.2f", Statistics.getTravelDistance(Accelerator.getPower(), mileage)) + " km");


        avgFuelConsumption.setText("Średnie spalanie: " + String.format("%.2f", Statistics.getAvgFuelConsumption(Statistics.getAvgSpeed())) + " l/100km");


        totalMileage.setText("Przebieg całkowity: " + String.format("%.2f", mileage.getTotalMileage()) + " km");
        totalMileage.setFill(additionalColor);
        totalMileage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

        dailyMileage.setText("Przebieg dzienny: " + String.format("%.2f", mileage.getDailyMileage()) + " km");

        userMileage.setText("Przebieg użytkownika: " + String.format("%.2f", mileage.getUserMileage()) + " km");

        tempomatSpeedText.setText(tempomatSpeedValue + " km/h");

        radioTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        songText.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        engineSpeed.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    }

    /**
     *
     *     Rysuje kontrolki biegów
     *
     */
    private static void drawGears(Color color){
        for(Circle i: listOfGearsControls) {
            i.setFill(color);
            i.setStroke(Color.DARKGREEN);
        }
    }

    /**
     *
     *     Rysuje diody
     *
     */
    private static void drawDiodes(Color color){
        for(Circle i: diodes){
            i.setFill(color);
            i.setStroke(color.BLACK);
        }
    }
    /**
     *
     *     Rysuje obrazki kontrolek
     *
     */
    static void showImages(Color color) {
        VBox tmp = new VBox();
        if(color == Color.DIMGRAY)
            tmp.setStyle("-fx-background-color: DIMGRAY ");
        else if(color == Color.AZURE)
            tmp.setStyle("-fx-background-color: AZURE ");
        else if(color == Color.CRIMSON)
            tmp.setStyle("-fx-background-color: CRIMSON ");
        if(whichLightOn==0)
            tmp.getChildren().addAll(pozycyjneczarnobiale, mijaniaczarnobiale, dlugieczarnobiale, dzienneczarnobiale);
        else if(whichLightOn == 1)
            tmp.getChildren().addAll(pozycyjne, mijaniaczarnobiale, dlugieczarnobiale, dzienneczarnobiale);
        else if(whichLightOn == 2)
            tmp.getChildren().addAll(pozycyjneczarnobiale, mijania, dlugieczarnobiale, dzienneczarnobiale);
        else if(whichLightOn == 3)
            tmp.getChildren().addAll(pozycyjneczarnobiale, mijaniaczarnobiale, dlugie, dzienneczarnobiale);
        else if(whichLightOn == 4)
            tmp.getChildren().addAll(pozycyjneczarnobiale, mijaniaczarnobiale, dlugieczarnobiale, dzienne);
        if(isFogLightsOn)
            tmp.getChildren().add(przeciwmgielne);
        else
            tmp.getChildren().add(przeciwmgielneczarnobiale);
        if(isTempomatOn)
            tmp.getChildren().add(tempomat);
        else
            tmp.getChildren().add(tempomatczarnobiale);
        wholeGrid.setLeft(tmp);

    }
}

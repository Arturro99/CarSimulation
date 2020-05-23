import warstwaDanych.ListOfSongs;
import warstwaDanych.Mileage;
import warstwaDanych.OperateOnFiles;

import warstwaLogiki.pl.exceptions.SuchFileDoesNotExist;

import warstwaDanych.OperateOnDataBase;
import warstwaLogiki.pl.lights.Indicator;
import warstwaLogiki.pl.lights.Side;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Mileage mileage = new Mileage();
    static OperateOnFiles operateOnFiles = new OperateOnFiles();
    static OperateOnDataBase operateOnDataBase = new OperateOnDataBase();
    static ListOfSongs listOfSongs = new ListOfSongs();
    static boolean isDBworking = false;
    static Indicator left = new Indicator(Side.left);
    static Indicator right = new Indicator(Side.right);
    public static void main(String[] args) {

        try {
            mileage = operateOnFiles.loadFromXmlFile("Próba.xml", mileage);
        }
        catch(SuchFileDoesNotExist exc) {
            System.err.println(exc);
            System.out.println("Nie podano pliku do wczytania danych lub podany plik nie istnieje");}
        mileage.checkData();


        char option = 0;
        do {
            showMenu();
            try {
                option = (char) System.in.read();
                System.in.skip(1);
            } catch (IOException e) {
                System.err.println(e);
            }
            switch (option) {
                case '1':
                    System.out.println("Ta opcja jeszcze nie działa");
                    goBack();
                    break;
                case '2':
                    showMileage();
                    goBack();
                    break;
                case '3':
                    showListOfSongs();
                    goBack();
                    break;
                case '4':
                    if(left.getIsOn() && right.getIsOn()){
                        left.turnOff();
                        right.turnOff();
                        System.out.println("Światła awaryjne zostały wyłączone");
                    }
                    else {
                        left.turnOn();
                        right.turnOn();
                        System.out.println("Światła awaryjne zostały włączone");
                    }
                    goBack();
                    break;
                case '5':
                    System.out.println("Wyłączanie programu");
                    operateOnFiles.saveToXmlFile("listOfSongs.xml", listOfSongs);
                    break;
                default:
                    System.out.println("Wybrano złą opcję");
            }
        }while(option != '5');
    }

    public static void goBack(){
        System.out.println("Wciśnij enter by kontynuować");
        try {
            System.in.skip(1);
            System.in.read();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void showMenu(){
        System.out.println("Wybierz opcję i zatwierdź enterem:");
        System.out.println("1 - Włącz samochód");
        System.out.println("2 - Wyświetl statystyki auta");
        System.out.println("3 - Lista piosenek");
        System.out.println("4 - Włącz/wyłącz światła awaryjne");
        System.out.println("5 - Wyłącz program");
        System.out.println("");
    }

    public static void showListOfSongs() {
        try {
            operateOnDataBase.fromDBToListOfSongs(listOfSongs);
            operateOnFiles.saveToXmlFile("listOfSongs.xml", listOfSongs);
            System.out.println("Wczytano piosenki z bazy danych");
            isDBworking = true;
        } catch (SQLException e) {
            System.err.println(e);
            try{
                operateOnFiles.loadFromXmlFile("listOfSongs.xml", listOfSongs);
                System.out.println("Wczytano piosenki z pliku xml");
            } catch (SuchFileDoesNotExist ee) {
                System.err.println(ee);
                System.out.println("Nie udało się wczytać piosenek z bazy danych i pliku xml");
            }
        }
        char option = 0;
        do {
            System.out.println("Wybierz opcję i zatwierdź enterem:");
            System.out.println("1 - Lista piosenek nieposortowana");
            System.out.println("2 - Lista piosenek posortowana według tytułu");
            System.out.println("3 - Lista piosenek posortowana według artysty");
            System.out.println("4 - Lista piosenek posortowana według długości");
            System.out.println("5 - Dodaj nową piosenkę");
            System.out.println("6 - Usuń piosenkę");
            System.out.println("7 - Powrót do menu");
            try {
                option = (char) System.in.read();
            } catch (IOException e) {
                System.err.println(e);
            }
            switch (option) {
                case '1':
                    System.out.println(listOfSongs.toString());
                    goBack();
                    break;
                case '2':
                    listOfSongs.sortByTitle();
                    System.out.println(listOfSongs.toString());
                    goBack();
                    break;
                case '3':
                    listOfSongs.sortByArtist();
                    System.out.println(listOfSongs.toString());
                    goBack();
                    break;
                case '4':
                    listOfSongs.sortByDuration();
                    System.out.println(listOfSongs.toString());
                    goBack();
                    break;
                case '5': //dodawanie piosenki
                    if (isDBworking) {
                        Scanner in = new Scanner(System.in);
                        System.out.println("Podaj nazwę piosenki:");
                        String title = in.next();
                        System.out.println("Podaj artystę:");
                        String artist = in.next();
                        System.out.println("Podaj nazwę albumu:");
                        String album = in.next();
                        System.out.println("Podaj długość piosenki (w formacie HH:MM:SS):");
                        String duration = in.next();
                        System.out.println("Podaj ID piosenki:");
                        Integer index = Integer.parseInt(in.next());
                        try{
                            operateOnDataBase.insert(title, artist, album, duration, index);
                            listOfSongs.addSong(title, artist, album, duration, index);
                        } catch (SQLException e) {
                            System.err.println(e);
                        }
                    }
                    else {
                        System.out.println("Baza danych jest niedostępna, nie można dodać piosenki.");
                    }
                    break;
                case '6': //usuwanie piosenki
                    if (isDBworking) {
                        Scanner in = new Scanner(System.in);
                        System.out.println("Podaj ID piosenki do usunięcia:");
                        Integer index = Integer.parseInt(in.next());
                        try{
                            operateOnDataBase.delete(index);
                            listOfSongs.deleteSongWithID(index);
                        } catch (SQLException e) {
                            System.err.println(e);
                        }
                    }
                    else {
                        System.out.println("Baza danych jest niedostępna, nie można usunąć piosenki.");
                    }
                    break;
                case '7':
                    break;
                default:
                    System.out.println("Wybrano złą opcję");
            }
        } while(option != '7');
    }

    public static void showMileage(){
        System.out.println("Przebieg całkowity: " + mileage.getTotalMileage());
        System.out.println("Przebieg dzienny: " + mileage.getDailyMileage());
        System.out.println("Przebieg użytkownika: " + mileage.getUserMileage());
    }
}


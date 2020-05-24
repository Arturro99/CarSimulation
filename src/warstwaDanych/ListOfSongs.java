package warstwaDanych;

import warstwaLogiki.SortByDuration;
import warstwaLogiki.SortByTitle;

import java.sql.Time;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Klasa przechowuje obiekty klasy Song w liscie i odpowiada za podstawowe operacje:
 * zapisywania, usuwania i sortowania piosenek na liscie.
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class ListOfSongs {
    /**
     * Lista przechowujaca obiekty klasy Song
     */
    private ArrayList<Song> songs = new ArrayList<>();

    /**
     * Metoda dodaje piosenke do listy
     * @param title  Tytul piosenki
     * @param artist  Artysta wykonujacy piosenke
     * @param album  Album piosenki
     * @param duration  Dlugosc piosenki (format HH:MM:SS)
     * @param ID  Unikalne ID piosenki
     */
    public void addSong(String title, String artist, String album, Time duration, int ID)
    {
        songs.add(new Song(title, artist, album, duration, ID));
    }
    /**
     * Metoda dodaje piosenke do listy
     * @param title  Tytul piosenki
     * @param artist  Artysta wykonujacy piosenke
     * @param album  Album piosenki
     * @param duration  Dlugosc piosenki (format HH:MM:SS)
     * @param ID  Unikalne ID piosenki
     */
    public void addSong(String title, String artist, String album, String duration, int ID)
    {
        songs.add(new Song(title, artist, album, duration, ID));
    }

    /**
     * Metoda dodaje piosenke do listy
     * @param song  Obiekt klasy Song
     */
    public void addSong(Song song)
    {
        songs.add(song);
    }
    /**
     * Metoda usuwa piosenke z listy
     * @param song  Obiekt klasy Song
     */
    public void deleteSong(Song song)
    {
        songs.remove(song);
    }
    /**
     * Metoda usuwa piosenke z listy
     * @param id  Unikalne ID piosenki, ktora ma byc usunieta
     */
    public void deleteSongWithID(int id)
    {
        for (Song song : songs) {
            if (song.getID() == id) {
                deleteSong(song);
                break;
            }
        }
    }

    /**
     * Metoda zwracajaca ilosc elementow na liscie
     * @return Ilosc elementow na liscie
     */
    public int getSize() {return songs.size(); }

    /**
     * Metoda zwraca obiekt klasy Song znajdujacy sie na podanym miejscu listy
     * @param index  Indeks piosenki na liscie
     * @return Obiekt klasy Song
     */
    public Song getSong(int index) {
        return songs.get(index);
    }

    /**
     * Metoda wypisuje prowizoryczna tabele zawierajaca wszystkie piosenki znajdujace sie na liscie
     * @return Lancuch znakowy z tabela zawierajaca liste piosenek
     */
    @Override
    public String toString()
    {
        StringBuilder tmp = new StringBuilder();
        tmp.append(String.format("|%-23s|%-23s|%-23s|%-12s|%-5s| \n", "Title", "Artist", "Album", "Duration", "ID"));
        for (Song song : songs) tmp.append(song.toString()).append("\n");
        return tmp.toString();
    }


    /**
     * Metoda segreguje piosenki po nazwie
     */
    public void sortByTitle() {Collections.sort(songs, new SortByTitle());}
    /**
     *  Metoda segreguje piosenki po artyscie
     */
    public void sortByArtist() {Collections.sort(songs, Comparator.comparing(Song::getArtist).reversed());}
    /**
     *  Metoda segreguje po dlugosci piosenki
     */
    public void sortByDuration() {Collections.sort(songs, new SortByDuration());}
}
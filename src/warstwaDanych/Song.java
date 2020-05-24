package warstwaDanych;

import java.sql.Time;



/**
 * Klasa odpowiadajaca za obsluge piosenek
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class Song {
    private String title;
    private String artist;
    private String album;
    private Time duration;
    private int ID;

    /**
     * Metoda dodaje piosenke
     * @param title  Tytul piosenki
     * @param artist  Artysta wykonujacy piosenke
     * @param album  Album piosenki
     * @param duration  Dlugosc piosenki (format HH:MM:SS)
     * @param ID  Unikalne ID piosenki
     */
    public Song(String title, String artist, String album, String duration, int ID)
    {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = Time.valueOf(duration);
        this.ID = ID;
    }
    /**
     * Metoda dodaje piosenke
     * @param title  Tytul piosenki
     * @param artist  Artysta wykonujacy piosenke
     * @param album  Album piosenki
     * @param duration  Dlugosc piosenki (format HH:MM:SS)
     * @param ID  Unikalne ID piosenki
     */
    public Song(String title, String artist, String album, Time duration, int ID)
    {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.ID = ID;
    }

    /**
     * Metoda zwraca tytul piosenki
     * @return Tytul piosenki
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * Metoda zwraca wykonawce piosenki
     * @return Wykonawce piosenki
     */
    public String getArtist()
    {
        return artist;
    }
    /**
     * Metoda zwraca album piosenki
     * @return Album piosenki
     */
    public String getAlbum() { return album; }
    /**
     * Metoda zwraca dlugosc piosenki
     * @return Dlugosc piosenki
     */
    public String getDuration()
    {
        return duration.toString().substring(0, 8);
    }
    /**
     * Metoda zwraca unikalne ID piosenki
     * @return Unikalne ID piosenki
     */
    public int getID() { return ID; }

    /**
     * Metoda zwraca lancuch znakowy piosenki, potrzebny do wyswietlania w GUI
     * @return Lancuch znakowy piosenki
     */
    public String toStringGUI()
    {
        return title  + "\n" + artist + "\n" + album + "\n" + duration.toString().substring(0,8) + " ID:" + ID;
    }

    /**
     * Metoda zwraca lancuch znakowy piosenki
     * @return Lancuch znakowy piosenki
     */
    @Override
    public String toString()
    {
        String tableFormat = "|%-23s|%-23s|%-23s|%-12s|%-5d|";
        return String.format(tableFormat, title, artist, album, duration.toString().substring(0,8), ID);
    }
}

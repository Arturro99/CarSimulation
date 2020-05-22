package warstwaDanych;

import java.sql.Time;

/**
 *
 *  Odpowiada za stworzenie piosenki
 *
 */
public class Song {
    private String title;
    private String artist;
    private String album;
    private Time duration;
    private int ID;

    public Song(String title, String artist, String album, String duration, int ID)
    {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = Time.valueOf(duration);;
        this.ID = ID;
    }

    public Song(String title, String artist, String album, Time duration, int ID)
    {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;;
        this.ID = ID;
    }
    /**
     *
     *  Zwraca tytul
     *
     */
    public String getTitle()
    {
        return title;
    }
    /**
     *
     *  Zwraca artyste
     *
     */
    public String getArtist()
    {
        return artist;
    }
    /**
     *
     *  Zwraca album
     *
     */
    public String getAlbum() { return album; }
    /**
     *
     *  Zwraca długosc piosenki
     *
     */
    public String getDurationInSeconds()
    {
        return duration.toString().substring(0, 8);
    }
    /**
     *
     *  Zwraca ID piosenki
     *
     */
    public int getID() { return ID; }

    /**
     *
     *  Zwraca dane piosenki
     *
     */
    public String toStringGUI()
    {
        return title  + "\n" + artist + "\n" + album + "\n" + duration.toString().substring(0,8) + " ID:" + ID;
    }

    @Override
    public String toString()
    {
        String tableFormat = "|%-23s|%-23s|%-23s|%-12s|%-5d|";
        return String.format(tableFormat, title, artist, album, duration.toString().substring(0,8), ID);
    }




}

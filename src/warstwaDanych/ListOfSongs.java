package warstwaDanych;

import warstwaLogiki.SortByDuration;
import warstwaLogiki.SortByTitle;

import java.sql.Time;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * Operowanie plikami do zapisywania i wczytywania piosenek
 *
 */

public class ListOfSongs {
    private ArrayList<Song> songs = new ArrayList<>();
    /**
     * Porownuje nazwy artystow
     * @param c1 Pierwsza piosenka
     * @param c2 Druga piosenka
     * @return
     */
    public int compare(Song c1, Song c2)
    {
        return (c1.getArtist().compareTo(c2.getArtist()));
    }
    /**
     *
     *  Dodaje piosenke do listy
     *
     */
    public void addSong(String title, String artist, String album, Time duration, int ID)
    {
        songs.add(new Song(title, artist, album, duration, ID));
        System.out.println("Dodano piosenke: " + songs.get(songs.size()-1));
    }
    public void addSong(String title, String artist, String album, String duration, int ID)
    {
        songs.add(new Song(title, artist, album, duration, ID));
        System.out.println("Dodano piosenke: " + songs.get(songs.size()-1));
    }
    /**
     *
     *  Dodaje piosenke do listy
     *
     */
    public void addSong(Song song)
    {
        songs.add(song);
        //System.out.println("Dodano piosenke: " + songs.get(songs.size()-1));
    }
    /**
     *
     *  Usuwa piosenke do listy
     *
     */
    public void deleteSong(Song song)
    {
        songs.remove(song);
        //System.out.println("Usunieto piosenke: " + song.toString());
    }

    public void deleteSongWithID(int id)
    {
        for (int i = 0; i<songs.size(); i++){
            if(songs.get(i).getID() == id) {
                deleteSong(songs.get(i));
                break;
            }
        }
        //System.out.println("Usunieto piosenke: " + song.toString());
    }

    public int getSize() {return songs.size(); }
    public Song getSong(int index) {
        return songs.get(index);
    }

    /**
     *
     *  Zwraca wszystkie piosenki z listy
     *
     */
    @Override
    public String toString()
    {
        String tmp = "";
        tmp += String.format("|%-23s|%-23s|%-23s|%-12s|%-5s| \n", "Title", "Artist", "Album", "Duration", "ID");
        for(int i = 0; i < songs.size(); i++)
            tmp += songs.get(i).toString() + "\n";
        return tmp;
    }




    /**
     *
     *  Segreguje piosenki po nazwie
     *
     */
    public void sortByTitle() {Collections.sort(songs, new SortByTitle());}
    /**
     *
     *  Segreguje piosenki po artyscie
     *
     */
    public void sortByArtist() {Collections.sort(songs, Comparator.comparing(Song::getArtist));}
    /**
     *
     *  Segreguje po dlugosci piosenki
     *
     */
    public void sortByDuration() {Collections.sort(songs, new SortByDuration());}
}
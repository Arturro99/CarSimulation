package warstwaDanych;

import warstwaLogiki.SortByDuration;
import warstwaLogiki.SortByTitle;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class ListOfSongs {
    private ArrayList<Song> songs = new ArrayList<>();

    public int compare(Song c1, Song c2)
    {
        return (c1.getArtist().compareTo(c2.getArtist()));
    }

    public void addSong(String title, String artist, String album, long durationInSeconds)
    {
        songs.add(new Song(title, artist, album, durationInSeconds));
        System.out.println("Dodano piosenke: " + songs.get(songs.size()-1));
    }

    public void addSong(Song song)
    {
        songs.add(song);
        System.out.println("Dodano piosenke: " + songs.get(songs.size()-1));
    }

    public void deleteSong(Song song)
    {
        songs.remove(song);
        System.out.println("Usunieto piosenke: " + song.toString());
    }

    @Override
    public String toString()
    {
        String tmp = "";
        tmp += String.format("|%-23s|%-23s|%-23s|%-12s| \n", "Title", "Artist", "Album", "Duration (s)");
        for(int i = 0; i < songs.size(); i++)
            tmp += songs.get(i).toString() + "\n";
        return tmp;
    }



    

    public void sortByTitle() {Collections.sort(songs, new SortByTitle());}
    public void sortByArtist() {Collections.sort(songs, Comparator.comparing(Song::getArtist));}
    public void sortByDuration() {Collections.sort(songs, new SortByDuration());}
}
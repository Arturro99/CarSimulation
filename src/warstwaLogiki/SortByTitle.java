package warstwaLogiki;

import warstwaDanych.Song;

import java.util.Comparator;

/**
 * Klasa implementuje interfejs "Comparator", sluzy do sortowania piosenek w zaleznosci od tytulu utworu
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class SortByTitle implements Comparator<Song>
{
    public int compare(Song c1, Song c2)
    {
        return c1.getTitle().compareTo(c2.getTitle());
    }

}

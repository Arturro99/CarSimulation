package warstwaLogiki;

import warstwaDanych.Song;

import java.util.Comparator;

/**
 * Klasa implementuje interfejs "Comparator", sluzy do sortowania piosenek w zaleznosci od dlugosci utworu
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class SortByDuration implements Comparator<Song> {
    @Override
    public int compare(Song c1, Song c2)
    {
        return c1.getDuration().compareTo(c2.getDuration());
    }
}

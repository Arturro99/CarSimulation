package warstwaLogiki;

import warstwaDanych.Song;

import java.util.Comparator;

public class SortByDuration implements Comparator<Song> {
    @Override
    public int compare(Song c1, Song c2)
    {
        return c1.getDuration().compareTo(c2.getDuration());
    }
}

package warstwaLogiki;

import warstwaDanych.Song;

import java.util.Comparator;
import java.lang.Long;

public class SortByDuration implements Comparator<Song> {
    @Override
    public int compare(Song c1, Song c2)
    {
        return Long.compare(c1.getDurationInSeconds(), c2.getDurationInSeconds());
    }
}

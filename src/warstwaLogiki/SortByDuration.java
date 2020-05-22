package warstwaLogiki;

import warstwaDanych.Song;

import java.sql.Time;
import java.util.Comparator;
import java.lang.Long;

public class SortByDuration implements Comparator<Song> {
    @Override
    public int compare(Song c1, Song c2)
    {
        return c1.getDurationInSeconds().compareTo(c2.getDurationInSeconds());
    }
}

package warstwaDanych;

public class Song {
    private String title;
    private String artist;
    private String album;
    private long durationInSeconds;

    public Song(String title, String artist, String album,  long durationInSeconds)
    {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.durationInSeconds = durationInSeconds;
    }

    public String getTitle()
    {
        return title;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getAlbum() { return album; }

    public long getDurationInSeconds()
    {
        return durationInSeconds;
    }

    @Override
    public String toString()
    {
        String tableFormat = "|%-23s|%-23s|%-23s|%-12d|";
        //return title + " " + artist + " " + album + " " + durationInSeconds;
        return String.format(tableFormat, title, artist, album, durationInSeconds);
    }

}

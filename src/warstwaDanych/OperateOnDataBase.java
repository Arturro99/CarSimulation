package warstwaDanych;

import java.sql.*;
import java.util.ArrayList;

/**
 * Klasa odpowiada za operacje w bazie danych
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class OperateOnDataBase {

    String dbURL = "jdbc:sqlserver://TWIERDZA\\ARTURROSERVER; databaseName = bazaUtworow";
    String user = "proba";
    String password = "proba1234";
    Connection con;
    Statement statement;
    ArrayList<Integer> listOfDeletedSongs = new ArrayList<>();


    /**
     * Metoda zwroca lancuch znakowy z wszystkimi rekordami z bazy danych
     * @return Lancuch znakowy z wszystkimi rekordami z bazy danych
     * @throws SQLException
     */
    public String selectAll() throws SQLException {
        con = DriverManager.getConnection(dbURL, user, password);
        statement = con.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM piosenki");
        StringBuilder tmp = new StringBuilder();
        while (result.next()) {
            for (int i = 1; i < 6; i++) {
                if (i != 4)
                    tmp.append(result.getString(i).strip()).append(", ");
                else
                    tmp.append(result.getString(i).substring(0, 6)).append(Math.round(Float.parseFloat(result.getString(i).substring(6, 15)))).append(",");
            }
            tmp.append('\n');
        }
        return tmp.toString();
    }

    /**
     * Metoda zapisuje wszystkie rekordy z bazy danych do listy piosenek z klasy ListOfSongs
     * @param list - Objekt klasy ListOfSongs
     * @throws SQLException
     */
    public void fromDBToListOfSongs(ListOfSongs list) throws SQLException {
        con = DriverManager.getConnection(dbURL, user, password);
        statement = con.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM piosenki");
        while(result.next()){
            list.addSong(result.getString(1).strip(), result.getString(2).strip(),
                    result.getString(3).strip(), result.getTime(4), result.getInt(5));
        }
    }

    /**
     * Zwrocenie lancucha znakowego z pojedynczym rekordem z bazy danych
     */
    /**
     * Metoda zwroca lancuch znakowy z pojedynczym rekordem z bazy danych
     * @param index - Numer unikalnego ID rekordu w bazie danych
     * @return Landuch znakowy z pojedynczym rekordem z bazy danych
     * @throws SQLException
     */
    public String selectOne(int index) throws SQLException {
        boolean operationDone = false;
        con = DriverManager.getConnection(dbURL, user, password);
        statement = con.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM piosenki");
        StringBuilder tmp = new StringBuilder();
            while (result.next()) {
                if (result.getString("id").equals(Integer.toString(index))) {
                    operationDone = true;
                    for (int i = 1; i < 6; i++) {
                        if (i != 4)
                            tmp.append(result.getString(i).strip()).append(", ");
                        else
                            tmp.append("\n").append(result.getString(i).substring(0, 6)).append(Math.round(Float.parseFloat(result.getString(i).substring(6, 15)))).append(",");
                    }
                    tmp.append('\n');
                    break;
                }
            }
//        if (!operationDone)
//            throw new SQLException("Nie ma rekordu o takim indeksie");
        return tmp.toString();
    }

    /**
     * Metoda zwraca tytul piosenki, ktora posiada podane unikalne ID - index
     * @param index - Unikalne ID dla piosenki znajdujacej sie w bazie danych
     * @return Tytul piosenki
     * @throws SQLException
     */
    public String getTitle(int index) throws SQLException {
        StringBuilder tmp = new StringBuilder();
        con = DriverManager.getConnection(dbURL, user, password);
        statement = con.createStatement();
        ResultSet result = statement.executeQuery("SELECT Tytul FROM piosenki WHERE id = " + "'" + index + "'");
        while(result.next())
            tmp.append(result.getString("Tytul").strip());
        return tmp.toString();
    }


    /**
     * Metoda dodaje rekord do bazy danych
     * @param title - Tytul piosenki
     * @param artist - Artysta wykonujacy piosenke
     * @param album - Album piosenki
     * @param duration - Dlugosc piosenki (format HH:MM:SS)
     * @param index - Unikalne ID piosenki
     * @throws SQLException
     */
    public void insert(String title, String artist, String album, String duration, int index) throws SQLException {
        con = DriverManager.getConnection(dbURL, user, password);
        statement = con.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM piosenki");
        while(result.next())
        {
            if(result.getString("id").equals(Integer.toString(index)))
                throw new SQLException("Utwór o takim id już istnieje");
        }
        statement.executeUpdate("INSERT INTO piosenki VALUES(" +
                "'" + title + "'," + " " +
                "'" + artist + "'," + " " +
                "'" + album + "'," + " " +
                "'" + duration + "'," + " " +
                "'" + index + "'" + ")");
    }


    /**
     * Metoda usuwa rekord z bazy danych o podanym ID
     * @param id - Unikalne ID dla rekordu w bazie danych
     * @throws SQLException
     */
    public void delete(int id) throws SQLException {
        con = DriverManager.getConnection(dbURL, user, password);
        statement = con.createStatement();
        statement.executeUpdate("DELETE FROM piosenki WHERE id = " + "'" + id + "'");
        listOfDeletedSongs.add(id);
    }

    /**
     * Metoda aktualizuje tytul piosenki, ktora posiada podany unikany ID
     * @param id - Unikalne ID dla rekordu w bazie danych
     * @param newTitle - Nowy tytul dla rekordu w bazie danych
     * @throws SQLException
     */
    public void updateTitle(int id, String newTitle) throws SQLException {
        con = DriverManager.getConnection(dbURL, user, password);
        statement = con.createStatement();
        statement.executeUpdate("UPDATE piosenki SET Tytul = " + "'" + newTitle + "'" +
                "WHERE id = " + "'" + id + "'");
    }

    /**
     * Metoda aktualizuje artyste piosenki, ktora posiada podany unikany ID
     * @param id - Unikalne ID dla rekordu w bazie danych
     * @param newArtist - Nowy artysta dla rekordu w bazie danych
     * @throws SQLException
     */
    public void updatePerformer(int id, String newArtist) throws SQLException {
        con = DriverManager.getConnection(dbURL, user, password);
        statement = con.createStatement();
        statement.executeUpdate("UPDATE piosenki SET Wykonawca = " + "'" + newArtist + "'" +
                "WHERE id = " + "'" + id + "'");
    }

    /**
     * Metoda aktualizuje album piosenki, ktora posiada podany unikany ID
     * @param id - Unikalne ID dla rekordu w bazie danych
     * @param newAlbum - Nowy album dla rekordu w bazie danych
     * @throws SQLException
     */
    public void updateAlbum(int id, String newAlbum) throws SQLException {
        con = DriverManager.getConnection(dbURL, user, password);
        statement = con.createStatement();
        statement.executeUpdate("UPDATE piosenki SET Album = " + "'" + newAlbum + "'" +
                "WHERE id = " + "'" + id + "'");
    }
}

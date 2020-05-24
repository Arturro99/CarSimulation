package warstwaDanych;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import warstwaInterfejsu.Settings;
import warstwaLogiki.pl.exceptions.SuchFileDoesNotExist;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
/**
 *  Klasa pozwala na zapisywanie i odczytywanie z plików xml/txt
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class OperateOnFiles {
    /**
     * Metoda zapisuje do pliku XML pola podanego objektu
     * @param nazwa  Nazwa pliku XML do ktorego zapisujemy
     * @param obj  Objekt, ktory chcemy zapisac do pliku XML
     */
    public void saveToXmlFile(String nazwa, Object obj)
    {
        XStream xstream = new XStream(new DomDriver());
        File file = new File(nazwa);
        String xml = xstream.toXML(obj);

        try	{
            file.createNewFile();					// Utworzenie pliku pod sciezka zapisana w plik
            FileWriter xmlStream = new FileWriter(file);	// Konstrukcja i otwarcie strumienia
            xmlStream.write(xml.toString());		// Zapis do pliku liter od 0 do 7 z txt
            xmlStream.close(); 				// Zamkniecie strumienia
        }

        // Instrukcje lapiace wyjatki
        catch (IOException io)
        {System.out.println(io.getMessage());}

        catch (Exception se)
        {System.err.println("blad sec");}
    }

    /**
     * Metoda wczytuje dane z pliku XML do obiektu klasy Mileage
     * @param nazwa  Nazwa pliku XML z ktorego wczytujemy
     * @param obj  Objekt, do ktorego chcemy wczytac dane z XMLa
     * @return Obiekt klasy Mileage
     * @throws SuchFileDoesNotExist Wyjatek zostanie rzucony w przypadku braku mozliwosci otworzenia podanego pliku
     */
    public Mileage loadFromXmlFile(String nazwa, Mileage obj) throws SuchFileDoesNotExist {
        XStream xstream = new XStream(new DomDriver());
        File file = new File(nazwa);
        if(file.exists()) {
            obj = (Mileage)xstream.fromXML(file);
        }
        else
            throw new SuchFileDoesNotExist("Plik o podanej nazwie nie istnieje, nie wczytano danych");
        return obj;
    }
    /**
     * Metoda wczytuje dane z pliku XML do obiektu klasy Settings
     * @param nazwa  Nazwa pliku XML z ktorego wczytujemy
     * @return Obiekt klasy Settings
     * @throws SuchFileDoesNotExist Wyjatek zostanie rzucony w przypadku braku mozliwosci otworzenia podanego pliku
     */
    public Settings loadFromXmlFile(String nazwa) throws SuchFileDoesNotExist {
        XStream xstream = new XStream(new DomDriver());
        File file = new File(nazwa);
        Settings obj;
        if(file.exists()) {
            obj = (Settings)xstream.fromXML(file);
        }
        else
            throw new SuchFileDoesNotExist("Plik o podanej nazwie nie istnieje, nie wczytano danych");
        return obj;
    }
    /**
     * Metoda wczytuje dane z pliku XML do obiektu klasy ListOfSongs
     * @param nazwa  Nazwa pliku XML z ktorego wczytujemy
     * @param obj  Objekt, do ktorego chcemy wczytac dane z XMLa
     * @return Obiekt klasy ListOfSongs
     * @throws SuchFileDoesNotExist Wyjatek zostanie rzucony w przypadku braku mozliwosci otworzenia podanego pliku
     */
    public ListOfSongs loadFromXmlFile(String nazwa, ListOfSongs obj) throws SuchFileDoesNotExist {
        XStream xstream = new XStream(new DomDriver());
        File file = new File(nazwa);
        if(file.exists()) {
            obj = (ListOfSongs) xstream.fromXML(file);
        }
        else
            throw new SuchFileDoesNotExist("Plik o podanej nazwie nie istnieje, nie wczytano danych");
        return obj;
    }


    /**
     * Metoda zapisuje liste piosenek do pliku txt
     * @param nazwa  Nazwa pliku, do ktorego chcemy zapisac dane
     * @param obj  Objekt klasy ListOfSongs
     */
    public void saveToTxtFile(String nazwa, ListOfSongs obj)
    {
        File file = new File(nazwa);

        try{
            file.createNewFile();
            FileWriter streamOut = new FileWriter(file);
            streamOut.write(obj.toString());
            streamOut.close();
        }
        catch(IOException io){System.out.println(io.getMessage());}
        catch(Exception se){System.err.println("blad sec");}
    }
    /**
     * Metoda wczytuje liste piosenek z pliku txt
     * @param nazwa  Nazwa pliku, z ktorego chcemy wczytac dane
     * @param obj  Objekt klasy ListOfSongs
     */
    public void readFromTxtFile(String nazwa, ListOfSongs obj)
    {
        ArrayList<Character> readedSongs = new ArrayList<>();
        File file = new File(nazwa);

        try{
            FileReader streamIn = new FileReader(file);
            while(streamIn.ready())
            {
                readedSongs.add((char)streamIn.read());
            }
        }
        catch (FileNotFoundException io)
        {System.out.println(io.getMessage());}

        catch (IOException io)
        {System.out.println(io.getMessage());}


        StringBuilder builder = new StringBuilder();
        for(char it : readedSongs)
            builder.append(it);
        System.out.println(builder);

        String[] rozdzielone = builder.toString().split("\\n|(\\|)|\\s{2,}");
        System.out.println(rozdzielone[35]);

        for(int i = 10; i < rozdzielone.length-1; i += 10)
        {
            obj.addSong(rozdzielone[i], rozdzielone[i+2], rozdzielone[i+4], Time.valueOf(rozdzielone[i + 6]), Integer.parseInt(rozdzielone[i+8]));
        }
    }
}

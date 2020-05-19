package warstwaDanych;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import warstwaLogiki.pl.exceptions.SuchFileDoesNotExist;

import java.io.*;
import java.util.ArrayList;
/**
 *
 *  Pozwala na zapisywanie i odczytywanie z pliku
 *
 */
public class OperateOnFiles {
    private static boolean wasLoaded = false;
    /**
     *
     *  Zapisywanie do pliku XML
     *
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
     *
     *  Wczytywanie z pliku XML do Mileage
     *
     */
    public Mileage loadFromXmlFile(String nazwa, Mileage obj) throws SuchFileDoesNotExist {
        XStream xstream = new XStream(new DomDriver());
        File file = new File(nazwa);
        if(file.exists()) {
            wasLoaded = true;
            obj = (Mileage)xstream.fromXML(file);
        }
        else
            throw new SuchFileDoesNotExist("Plik o podanej nazwie nie istnieje, nie wczytano danych");
        return obj;
    }
    /**
     *
     *  Wczytywanie z pliku XML do ListOfSongs
     *
     */
    public ListOfSongs loadFromXmlFile(String nazwa, ListOfSongs obj) throws SuchFileDoesNotExist {
        XStream xstream = new XStream(new DomDriver());
        File file = new File(nazwa);
        if(file.exists()) {
            wasLoaded = true;
            obj = (ListOfSongs) xstream.fromXML(file);
        }
        else
            throw new SuchFileDoesNotExist("Plik o podanej nazwie nie istnieje, nie wczytano danych");
        return obj;
    }

    /**
     *
     *  Zapisuje piosenki do pliku tekstowego
     *
     */
    public void saveToTxtFile(String nazwa)
    {
        File file = new File(nazwa);

        try{
            file.createNewFile();
            FileWriter streamOut = new FileWriter(file);
            streamOut.write(this.toString());
            streamOut.close();
        }
        catch(IOException io){System.out.println(io.getMessage());}
        catch(Exception se){System.err.println("blad sec");}
    }
    /**
     *
     *  Wczytuje piosenki z pliku tekstowego
     *
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
            obj.addSong(rozdzielone[i], rozdzielone[i+2], rozdzielone[i+4], Long.parseLong(rozdzielone[i+6]));
        }
    }
}

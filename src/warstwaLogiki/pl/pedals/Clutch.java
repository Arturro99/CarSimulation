package warstwaLogiki.pl.pedals;

/**
 * Klasa odpowiadajaca za dzialanie pedalu sprzegla
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class Clutch implements Pedals {
    static boolean isOn = false;   //zmienna pokazuje, czy sprzęgło zostało wciśnięte -> możliwa ewentualna zmiana biegu

    /**
     * Metoda odpowiadajaca za wywolywanie nacisku na pedal
     * @param powerInPercentage  Sila, z jaka bedzie sie oddzialywac na pedal
     */
    @Override
    public void pressPedal(Integer powerInPercentage)
    {
        if(!isOn) {
            System.out.println("Wciśnięto pedał sprzęgła");
            isOn = true;
        }
            else
                System.out.println("Pedał sprzęgła jest już wciśnięty.");
        }

    /**
     * Metoda odpowiadajaca za zmniejszenie nacisku na pedal
     * @param powerInPercentage  Ilosc sily nacisku zdjetej z pedalu gazu
     */
    @Override
    public void releasePedal(Integer powerInPercentage)
    {
        if(!isOn)
            System.out.println("Na pedał sprzęgła nie działa żadna siła nacisku.");
        else {
            System.out.println("Puszczono pedał sprzęgła.");
            isOn = false;
        }
    }

    /**
     * Metoda pobierajaca informacje o stanie sprzegla (wlaczone/wylaczone)
     * @return  Stan sprzegla
     */
    public static boolean getIsOn() {return isOn;}
    }

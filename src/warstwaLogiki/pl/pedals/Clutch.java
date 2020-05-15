package warstwaLogiki.pl.pedals;

public class Clutch implements Pedals {
    static boolean isOn = false;   //zmienna pokazuje, czy sprzęgło zostało wciśnięte -> możliwa ewentualna zmiana biegu

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

    public static boolean getIsOn() {return isOn;}
    }

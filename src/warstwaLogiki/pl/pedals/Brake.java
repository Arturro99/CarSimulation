package warstwaLogiki.pl.pedals;

/**
 *  Klasa odpowiadajaca za dzialanie pedalu hamulca
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class Brake implements Pedals {

    /**
     * Metoda odpowiadajaca za wywolywanie nacisku na pedal
     * @param powerInPercentage - sila, z jaka bedzie sie oddzialywac na pedal
     */
    @Override
    public void pressPedal(Integer powerInPercentage){
        if(Accelerator.getPower() != 0) {       //warunek jeśli samochód jedzie
            if(Accelerator.getPower() - powerInPercentage > 0)  //jeśli zahamujemy z mniejszą mocą niż jedziemy
                Accelerator.setPower(-powerInPercentage);
            else              //jeśli zahamujemy z większą mocą niż jedziemy
                Accelerator.setPower(-Accelerator.getPower());
            //System.out.println("Zahamowano, aktualna prędkość to: " + Accelerator.getPower() + "% maksymalnej prędkości samochodu.");
        }
            else if(Accelerator.getPower() == 0)
                System.out.println("Samochód stoi w miejscu.");
    }

    /**
     * Metoda odpowiadajaca za zmniejszenie nacisku na pedal
     * @param powerInPercentage - ilosc sily nacisku zdjetej z pedalu hamulca
     */
    @Override
    public void releasePedal(Integer powerInPercentage){
        System.out.println("Puszczono pedał hamowania");
    }
}

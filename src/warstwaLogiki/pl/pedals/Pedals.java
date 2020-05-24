package warstwaLogiki.pl.pedals;

/**
 * Interfejs samochodowych pedalow
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public interface Pedals {

    /**
     * Wcisniecie pedalu
     * @param powerInPercentage  Sila, z jaka oddzialuje sie na pedal
     * @throws Exception  Wyjatek zostanie rzucony w chwili przekroczenia pewnej okreslonej predkosci
     */
    void pressPedal(Integer powerInPercentage) throws Exception;

    /**
     * Zmniejszenie sily nacisku na pedal
     * @param powerInPercentage  Ilosc sily nacisku zdjetej z pedalu
     */
    void releasePedal(Integer powerInPercentage);
}

package warstwaLogiki.pl.pedals;

/**
 * Interfejs samochodowych pedalow
 */
public interface Pedals {

    /**
     * Wcisniecie pedalu
     * @param powerInPercentage - sila, z jaka oddzialuje sie na pedal
     * @throws Exception - wyjatek zostanie rzucony w chwili przekroczenia pewnej okreslonej predkosci
     */
    public void pressPedal(Integer powerInPercentage) throws Exception;

    /**
     * Zmniejszenie sily nacisku na pedal
     * @param powerInPercentage - ilosc sily nacisku zdjetej z pedalu
     */
    public void releasePedal(Integer powerInPercentage);
}

package warstwaLogiki.pl.pedals;
/**
 *
 *  Interfejs samochodowych pedalow
 *
 */
public interface Pedals {
    /**
     *
     *  Nacisniecie pedalu samochodowego
     *
     */
    public void pressPedal(Integer powerInPercentage) throws Exception;
    /**
     *
     *  Zwolnienie pedalu samochodowego
     *
     */
    public void releasePedal(Integer powerInPercentage);
}

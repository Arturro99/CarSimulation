package warstwaLogiki.pl.exceptions;
/**
 *
 *  Wyjatek nie pozwalajacy na predkosc poza przedzialem
 *
 */
public class TooFastException extends Exception{
    public TooFastException(String message) {super(message);}
}

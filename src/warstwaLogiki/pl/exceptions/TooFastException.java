package warstwaLogiki.pl.exceptions;
/**
 *
 *  Wyjatek, ktory nie pozwala na predkosc poza przedzialem
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class TooFastException extends Exception{
    public TooFastException(String message) {super(message);}
}

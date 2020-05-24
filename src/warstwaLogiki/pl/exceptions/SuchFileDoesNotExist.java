package warstwaLogiki.pl.exceptions;
/**
 *
 *  Wyjatek, ktory nie pozwala otworzyć nieistniejącego pliku
 *  @author Artur Madaj
 *  @author Wojciech Sowa
 */
public class SuchFileDoesNotExist extends Exception {
    public SuchFileDoesNotExist(String message) {super(message);}
}

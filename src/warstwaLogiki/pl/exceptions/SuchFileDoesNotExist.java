package warstwaLogiki.pl.exceptions;
/**
 *
 *  Wyjatek, ktory nie pozwala otworzyć nieistniejącego pliku
 *
 */
public class SuchFileDoesNotExist extends Exception {
    public SuchFileDoesNotExist(String message) {super(message);}
}

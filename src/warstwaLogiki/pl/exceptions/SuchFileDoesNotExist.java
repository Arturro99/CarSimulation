package warstwaLogiki.pl.exceptions;
/**
 *
 *  Wyjatek nie pozwalajacy otworzyć nieistniejącego pliku
 *
 */
public class SuchFileDoesNotExist extends Exception {
    public SuchFileDoesNotExist(String message) {super(message);}
}

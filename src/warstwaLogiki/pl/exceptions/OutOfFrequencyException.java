package warstwaLogiki.pl.exceptions;
/**
 *
 *  Wyjatek nie pozwalajacy wychodzic poza czestotliwosci radia
 *
 */
public class OutOfFrequencyException extends Exception {
    public OutOfFrequencyException(String message) {super(message);}
}

package warstwaInterfejsu;

import warstwaLogiki.pl.exceptions.OutOfFrequencyException;

public class Radio {
    private double frequency;

    public void adjustFrequency(double value) throws OutOfFrequencyException{
        if(value <= 108 && value > 88)
            frequency = value;
            else
                throw new OutOfFrequencyException("Wybrano częstotliwość spoza dopuszczalnego zakresu (88 - 108 MHz): " + value +" MHz");
    }
}

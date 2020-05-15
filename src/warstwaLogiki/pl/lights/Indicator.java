package warstwaLogiki.pl.lights;

public class Indicator extends LightingSystem implements Lights {
    Side side;

    public Indicator(Side side) {this.side = side; }

    public void turnOn() {
        if(!isOn) { super.isOn = true;
        LightingSystem.incrementNumberOfTurningSignals();
            if (side == Side.left) {
                System.out.println("Włączono lewy kierunkowskaz.");
            } else {
                System.out.println("Włączono prawy kierunkowskaz.");
            }
        }
        else
            System.out.println("Kierunkowskaz jest już włączony.");
    }
    public void turnOff() {
    if(isOn) {
        super.isOn = false;
        LightingSystem.decrementNumberOfTurningSignals();
        System.out.println("Wyłączono kierunkowskaz.");
    }
    else
        System.out.println("Żaden kierunkowskaz nie jest włączony");
    }


    public String toString()
    {
        if (side == Side.left) {
            return "Lewy kierunkowskaz";
        } else {
            return "Prawy kierunkowskaz";
        }
    }

    public boolean getIsOn() {return isOn;}

}

package warstwaLogiki.pl.lights;

public class Headlights extends LightingSystem implements Lights {
    public Headlights() {}

    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
            System.out.println("Włączono światła długie.");
        }
        else
            System.out.println("Swiatła długie są już włączone.");
    }

    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła długie.");
        }
        else
            System.out.println("Swiatła długie nie są włączone");
    }

    public String toString()
    {
        return "Swiatła długie";
    }
    public boolean getIsOn() {return isOn;}
}

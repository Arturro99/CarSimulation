package warstwaLogiki.pl.lights;

public class PositionLights extends LightingSystem implements Lights{

    public PositionLights() {}

    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
            System.out.println("Włączono światła pozycyjne.");
        }
        else
            System.out.println("Swiatła pozycyjne są już włączone.");
    }

    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła pozycyjne.");
        }
        else
            System.out.println("Swiatła pozycyjne nie są włączone");
    }

    public String toString()
    {
        return "Swiatła pozycyjne";
    }
    public boolean getIsOn() {return isOn;}
}

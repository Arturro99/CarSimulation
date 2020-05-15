package warstwaLogiki.pl.lights;

public class FogLights extends LightingSystem implements Lights {
    public FogLights() {}

    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
            System.out.println("Włączono światła przeciwmgielne.");
        }
        else
            System.out.println("Swiatła przeciwmgielne są już włączone.");
    }

    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła przeciwmgielne.");
        }
        else
            System.out.println("Swiatła przeciwmgielne nie są włączone");
    }

    public String toString()
    {
        return "Swiatła przeciwmgielne";
    }
    public boolean getIsOn() {return isOn;}
}
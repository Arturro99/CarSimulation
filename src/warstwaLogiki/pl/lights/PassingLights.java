package warstwaLogiki.pl.lights;

public class PassingLights extends LightingSystem implements Lights {

    public PassingLights() {}

    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
                System.out.println("Włączono światła mijania.");
            }
        else
            System.out.println("Swiatła mijania są już włączone.");
    }

    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła mijania.");
        }
        else
            System.out.println("Swiatła mijania nie są włączone");
    }

    public String toString()
    {
            return "Swiatła mijania";
    }
    public boolean getIsOn() {return isOn;}

}

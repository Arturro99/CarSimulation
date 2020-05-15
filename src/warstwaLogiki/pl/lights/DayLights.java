package warstwaLogiki.pl.lights;

public class DayLights extends LightingSystem implements Lights{
    public DayLights() {}

    @Override
    public void turnOn()
    {
        if(!isOn) { super.isOn = true;
            System.out.println("Włączono światła dzienne.");
        }
        else
            System.out.println("Swiatła dzienne są już włączone.");
    }

    @Override
    public void turnOff()
    {
        if(super.isOn) {
            super.isOn = false;
            System.out.println("Wyłączono światła dzienne.");
        }
        else
            System.out.println("Swiatła dzienne nie są włączone");
    }

    public String toString()
    {
        return "Swiatła dzienne";
    }
    public boolean getIsOn() {return isOn;}
}

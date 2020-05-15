package warstwaLogiki.pl.pedals;

public class Brake implements Pedals {

    @Override
    public void pressPedal(Integer powerInPercentage){
        if(Accelerator.getPower() != 0) {       //warunek jeśli samochód jedzie
            if(Accelerator.getPower() - powerInPercentage > 0)  //jeśli zahamujemy z mniejszą mocą niż jedziemy
                Accelerator.setPower(-powerInPercentage);
            else              //jeśli zahamujemy z większą mocą niż jedziemy
                Accelerator.setPower(-Accelerator.getPower());
            //System.out.println("Zahamowano, aktualna prędkość to: " + Accelerator.getPower() + "% maksymalnej prędkości samochodu.");
        }
            else if(Accelerator.getPower() == 0)
                System.out.println("Samochód stoi w miejscu.");
    }

    @Override
    public void releasePedal(Integer powerInPercentage){
        System.out.println("Puszczono pedał hamowania");
    }
}

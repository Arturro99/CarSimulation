package warstwaLogiki.pl.pedals;

public interface Pedals {
    public void pressPedal(Integer powerInPercentage) throws Exception;
    public void releasePedal(Integer powerInPercentage);
}

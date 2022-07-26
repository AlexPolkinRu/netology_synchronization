/**
 * @author Aleksandr Polochkin
 * 26.07.2022
 */

public class Buyer implements Runnable {
    CarDealer carDealer;

    Buyer(CarDealer carDealer) {
        this.carDealer = carDealer;
    }

    @Override
    public void run() {
        carDealer.sellCar();
    }


}

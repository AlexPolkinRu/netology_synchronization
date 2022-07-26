import static java.lang.Thread.sleep;

/**
 * @author Aleksandr Polochkin
 * 24.07.2022
 */

public class CarFactory implements Runnable {

    // Количество выпускаемых автомобилей
    private final int NUMBER_OF_CAR_PRODUCED = 10;

    // Время на изготовление одного автомобиля
    private final int TIME_TO_PRODUCE_CAR = 3000;

    private final CarDealer carDealer;
    private static int carFactoryCounter = 0;
    public final String CAR_FACTORY_NAME;

    public CarFactory(CarDealer carDealer) {
        this.carDealer = carDealer;
        carFactoryCounter++;
        CAR_FACTORY_NAME = "Производитель" + carFactoryCounter;
    }

    public void run() {

        Thread.currentThread().setName(CAR_FACTORY_NAME);

        Car car;

        // Выпустим заданное количество автомобилей
        for (int i = 0; i < NUMBER_OF_CAR_PRODUCED; i++) {
            car = releaseCar();

            // И отправим их в автосалон
            carDealer.addCarToGarage(car);
        }

    }

    public Car releaseCar() {

        String carFactoryName = Thread.currentThread().getName();
        System.out.println(carFactoryName + " начал изготовление автомобиля");

        try {
            sleep(TIME_TO_PRODUCE_CAR);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Car car = new Car();
        System.out.println(carFactoryName + " выпустил " + car);

        return car;
    }
}


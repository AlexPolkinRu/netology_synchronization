import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * @author Aleksandr Polochkin
 * 24.07.2022
 */

public class CarDealer {

    private final int TIME_TO_SALE_CAR = 1000;
    private final int CAPACITY_OF_GARAGE = 10;

    // работник автосалона (пусть он будет один)
    final Manager manager = new Manager(this);

    // Гараж автосалона
    private final List<Car> garageDealer = new ArrayList<>(CAPACITY_OF_GARAGE);

    // Счётчик покупателей
    private static int buyerCounter = 0;

    // Работник автосалона принимает новый автомобиль
    public void addCarToGarage(Car car) {

        synchronized (manager) {
            garageDealer.add(car);
            System.out.println("В автосалон поступил новый " + car);
            System.out.println("В гараже автомобилей: " + garageDealer.size());

            // Оповещаем ожидающего покупателя о поступлении
            manager.notify();
        }

    }

    public void sellCar() {

        synchronized (manager) {
            buyerCounter++;
            final String buyerName = "Покупатель" + buyerCounter;
            Thread.currentThread().setName(buyerName);

            System.out.println(buyerName + " зашёл в автосалон");

            while (garageDealer.isEmpty()) {
                System.out.println("Машин нет");

                // Если гараж автосалона пуст, покупатель ждёт поступления автомобиля
                try {
                    manager.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Car car = garageDealer.remove(garageDealer.size() - 1);

            System.out.println("Автосалон оформляет документы на " + car);

            try {
                sleep(TIME_TO_SALE_CAR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " уехал на " + car);

        }

    }

}

import java.util.concurrent.*;

/**
 * @author Aleksandr Polochkin
 * 24.07.2022
 */

public class Main {

    public static void main(String[] args) {

        final int NUMBER_OF_BUYERS = 5;
        final int NUMBER_OF_CARFACTORIES = 1;

        // Создадим тредпул для автопроизводителей и покупателей
        final ExecutorService threadPool = Executors.newFixedThreadPool(
                NUMBER_OF_BUYERS + NUMBER_OF_CARFACTORIES
        );

        // Создадим объект для автосалона
        CarDealer carDealer = new CarDealer();

        // Создадим объекты для покупателей
        Buyer[] buyers = new Buyer[NUMBER_OF_BUYERS];
        for (int i = 0; i < NUMBER_OF_BUYERS; i++) {
            buyers[i] = new Buyer(carDealer);
            // И здесь же пригласим их в тредпул
            threadPool.execute(buyers[i]);
        }

        // Создадим объекты для автопроизводителей
        CarFactory[] carFactories = new CarFactory[NUMBER_OF_CARFACTORIES];
        for (int i = 0; i < NUMBER_OF_CARFACTORIES; i++) {
            carFactories[i] = new CarFactory(carDealer);
            // И здесь же включим их в работу
            threadPool.execute(carFactories[i]);
        }

        // После окончания работы потоков выключаем тредпул
        threadPool.shutdown();

    }

}
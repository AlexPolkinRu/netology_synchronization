/**
 * @author Aleksandr Polochkin
 * 24.07.2022
 */

public class Car {
    final public int CAR_SERIAL_NUMBER;
    private static int counterSerialNumber = 0;

    public Car() {
        counterSerialNumber++;
        CAR_SERIAL_NUMBER = counterSerialNumber;
    }

    @Override
    public String toString() {
        return "автомобиль" + CAR_SERIAL_NUMBER;
    }
}

package a6;

import java.util.Iterator;

public class ExpandingProximityIterator implements Iterator<Driver> {

    private Iterable<Driver> driverPool;
    private Position clientPosition;
    private int expansionStep;
    private Driver driver;
    private int counter;

    public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
        this.driverPool = driver_pool;
        this.clientPosition = client_position;
        this.expansionStep = expansion_step;
        this.driver = null;
        this.counter = 0;
    }

    @Override
    public boolean hasNext() {
        if (driver == null) {
            return false;
        } else {
            this.next();
            if (driver == null) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public Driver next() {
        for (Driver tempDriver : driverPool) {
            if (tempDriver == null) {
                return null;
            }
            if (tempDriver.getVehicle().getPosition().getManhattanDistanceTo(clientPosition) <= 1) {
                driver = tempDriver;
                tempDriver = null;
                return driver;
            }
        }
        counter += 1;
        for (Driver tempDriver : driverPool) {
            if (tempDriver == null) {
                return null;
            }
            if (tempDriver.getVehicle().getPosition().getManhattanDistanceTo(clientPosition) > 1 &&
                    tempDriver.getVehicle().getPosition().getManhattanDistanceTo(clientPosition) <= 1 + (counter * expansionStep)) {
                driver = tempDriver;
                tempDriver = null;
                return driver;
            }
        }
        return null;
    }
}

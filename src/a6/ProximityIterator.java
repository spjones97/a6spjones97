package a6;

import java.util.*;

public class ProximityIterator implements Iterator<Driver> {

    private Iterable<Driver> driver_pool;
    private Position client_position;
    private int proximity_limit;
    private Driver driver;

    public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
        this.driver_pool = driver_pool;
        this.client_position = client_position;
        this.proximity_limit = proximity_limit;
        this.driver = null;
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
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (Driver tempDriver : driver_pool) {
            if (tempDriver.getVehicle().getPosition().getManhattanDistanceTo(client_position) <= proximity_limit) {
                driver = tempDriver;
                tempDriver = null;
                return driver;
            }
        }
        return null;
    }
}

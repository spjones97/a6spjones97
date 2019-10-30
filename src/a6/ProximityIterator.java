package a6;

import java.util.*;

public class ProximityIterator implements Iterator<Driver> {

    private Iterable<Driver> driver_pool;
    private Iterator<Driver> drivers;
    private Position client_position;
    private int proximity_limit;

    public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
        this.driver_pool = driver_pool;
        this.drivers = driver_pool.iterator();
        this.client_position = client_position;
        this.proximity_limit = proximity_limit;
    }


    @Override
    public boolean hasNext() {
        if (drivers.hasNext()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Driver next() {
        if (drivers.hasNext()) {
            Driver tempDriver = drivers.next();
            return tempDriver;
        } else {
            throw new NoSuchElementException();
        }
    }
}

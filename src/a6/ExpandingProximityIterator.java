package a6;

import java.util.Iterator;

public class ExpandingProximityIterator implements Iterator<Driver> {

    private Iterable<Driver> driverPool;
    private Iterator<Driver> drivers;
    private Position clientPosition;
    private int expansionStep;

    public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
        this.driverPool = driver_pool;
        this.drivers = driver_pool.iterator();
        this.clientPosition = client_position;
        this.expansionStep = expansion_step;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Driver next() {
        return null;
    }
}

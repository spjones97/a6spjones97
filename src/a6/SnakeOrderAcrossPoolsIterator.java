package a6;

import java.util.*;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
    private List<Iterable<Driver>> driver_pools;
    private Iterable<Driver> driverPool;
    private Driver driver;
    private int index;

    public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
        this.driver_pools = driver_pools;
        this.driver_pools = null;
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
        for (Iterable<Driver> tempPool : driver_pools) {
            for (Driver tempDriver : tempPool) {
                driver = tempDriver;
                tempDriver = null;
                return driver;
            }
        }
        return null;
    }
}

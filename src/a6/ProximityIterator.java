package a6;

import java.util.*;

public class ProximityIterator implements Iterator<Driver> {

    private Iterable<Driver> _driverPool;
    private Position _clientPosition;
    private int _proximityLimit;
    private Driver _nextDriver;
    private Iterator<Driver> _driverIterator;

    public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
        if (driver_pool == null || client_position == null || proximity_limit < 0) {
            throw new IllegalArgumentException();
        }
        this._driverPool = driver_pool;
        this._clientPosition = client_position;
        this._proximityLimit = proximity_limit;
        this._nextDriver = null;
        this._driverIterator = driver_pool.iterator();
    }


    @Override
    public boolean hasNext() {
        if (_nextDriver != null && _nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= _proximityLimit) {
            return true;
        }
        while (_driverIterator.hasNext()) {
            _nextDriver = _driverIterator.next();
            if (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= _proximityLimit) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Driver next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Driver tempDriver = _nextDriver;
        _nextDriver = null;
        return tempDriver;
    }
}

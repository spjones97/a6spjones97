package a6;

import java.util.*;

public class ExpandingProximityIterator implements Iterator<Driver> {

    private Iterable<Driver> _driverPool;
    private Iterator<Driver> _driverIterator;
    private Position _clientPosition;
    private int _expansionStep;
    private Driver _nextDriver;
    private int counter;
    private int numOne;
    private int numTwo;
    private int size;

    public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
        if (driver_pool == null || client_position == null || expansion_step < 0) {
            throw new IllegalArgumentException();
        }
        this._driverPool =  driver_pool;
        this._driverIterator = driver_pool.iterator();
        this._clientPosition = client_position;
        this._expansionStep = expansion_step;
        this._nextDriver = null;
        this.numOne = 0;
        this.numTwo = 0;
        this.counter = 0;
        this.size = 0;
        for (Driver i : _driverPool) {
            size += 1;
        }
    }

    @Override
    public boolean hasNext() {
        if (_nextDriver != null) {
            return true;
        }
        while (counter < size) {
            while (_driverIterator.hasNext()) {
                _nextDriver = _driverIterator.next();
                if (numOne == 0) {
                    if (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= 1) {
                        return true;
                    }
                } else if (numOne == 1) {
                    if (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) > 1 &&
                            _nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= (1 + _expansionStep)) {
                        return true;
                    }
                } else if (numOne > 1) {
                    if (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) > (1 + ((numTwo - 1) * _expansionStep)) &&
                    _nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= (1 + (numTwo * _expansionStep))) {
                        return true;
                    }
                }
            }
            numOne++;
            numTwo++;
            _driverIterator = _driverPool.iterator();
        }
        return false;
    }

    @Override
    public Driver next() {
        counter += 1;
        if (!this.hasNext()) {
            throw new NoSuchElementException("No element exists");
        }
        Driver tempDriver = _nextDriver;
        _nextDriver = null;
        return tempDriver;
    }
}

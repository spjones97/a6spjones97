package a6;

import java.util.*;

public class ExpandingProximityIterator implements Iterator<Driver> {

    private Iterable<Driver> _driverPool;
    private Iterator<Driver> _driverIterator;
    private Iterator<Driver> oneIterator;
    private Iterator<Driver> twoIterator;
    private Position _clientPosition;
    private int _expansionStep;
    private Driver _nextDriver;
    private int counter;

    public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
        if (driver_pool == null || client_position == null || expansion_step < 0) {
            throw new IllegalArgumentException();
        }
        this._driverPool =  driver_pool;
        this.oneIterator = driver_pool.iterator();
        this.twoIterator = driver_pool.iterator();
        this._driverIterator = driver_pool.iterator();
        this._clientPosition = client_position;
        this._expansionStep = expansion_step;
        this._nextDriver = null;
        this.counter = 0;
    }

    @Override
    public boolean hasNext() {

        if (_nextDriver != null && _nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= 1) {
            return true;
        }
        while (_driverIterator.hasNext()) {
            _nextDriver = _driverIterator.next();
            if (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= 1) {
                return true;
            }
        }
        if (_nextDriver != null && (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) > (1 + (counter*_expansionStep)) &&
                _nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= (1 + (counter + 1)*_expansionStep))) {
            return true;
        }
        while (oneIterator.hasNext()) {
            _nextDriver = oneIterator.next();
            if (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) > (1 + (counter*_expansionStep)) &&
                    _nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= (1 + (counter + 1)*_expansionStep)) {
                return true;
            }
        }
        counter += 1;
        if (_nextDriver != null && (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) > (1 + (counter*_expansionStep)) &&
                _nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= (1 + (counter + 1)*_expansionStep))) {
            return true;
        }
        while (twoIterator.hasNext()) {
            _nextDriver = twoIterator.next();
            if (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) > (1 + (counter*_expansionStep)) &&
                    _nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_clientPosition) <= (1 + (counter + 1)*_expansionStep)) {
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

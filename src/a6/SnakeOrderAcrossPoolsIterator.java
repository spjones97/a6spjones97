package a6;

import java.util.*;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
    private List<Iterable<Driver>> _listOfPools;
    private List<Iterator<Driver>> _listOfIterators;
    private Driver _nextDriver;
    private int index = 1;
    private boolean forwards = true;
    private boolean backwards = false;

    public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
        if (driver_pools == null) {
            throw new IllegalArgumentException("Input value is null");
        }
        this._listOfPools = driver_pools;
        this._listOfIterators = new ArrayList<Iterator<Driver>>();
        for (Iterable<Driver> i : _listOfPools) {
            _listOfIterators.add(i.iterator());
        }
        this._nextDriver = null;
    }

    @Override
    public boolean hasNext() {
        boolean start = false;
        for (int i = 0; i < _listOfIterators.size(); i++) {
            if (_listOfIterators.get(i).hasNext()) {
                start = true;
            }
        }
        while (start) {
            if (forwards) {
                for (int i = index; i < _listOfIterators.size(); i++) {
                    if (_listOfIterators.get(i).hasNext()) {
                        _nextDriver = _listOfIterators.get(i).next();
                        return true;
                    }
                }
            }
            if (backwards) {
                for (int i = index; i >= 0; i--) {
                    if (_listOfIterators.get(i).hasNext()) {
                        _nextDriver = _listOfIterators.get(i).next();
                        return true;
                    }
                }
            }
            forwards = !forwards;
            backwards = !backwards;
            for (Iterable<Driver> i : _listOfPools) {
                _listOfIterators.add(i.iterator());
            }
        }
        return false;
    }

    @Override
    public Driver next() {
        if (forwards) {
            index++;
        }
        if (backwards) {
            index--;
        }
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Driver tempDriver = _nextDriver;
        _nextDriver = null;
        return tempDriver;
    }
}

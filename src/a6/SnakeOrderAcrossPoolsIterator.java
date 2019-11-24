package a6;

import java.util.*;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
    private List<Iterable<Driver>> _listOfPools;
    private List<Iterator<Driver>> _listOfIterators;
    private Driver _nextDriver;
    private int index;
    private boolean forwards;

    public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
        this.forwards = true;
        this.index = 0;
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
        boolean start = true;
        while (start) {
            if (forwards) {
                for (int i = index; i < _listOfIterators.size(); i++) {
                    if (_listOfIterators.get(i).hasNext()) {
                        _nextDriver = _listOfIterators.get(i).next();
                        return true;
                    }
                }
            } else {
                for (int i = index; i > 0; i--) {
                    if (_listOfIterators.get(i).hasNext()) {
                        _nextDriver = _listOfIterators.get(i).next();
                        return true;
                    }
                }
            }
            start = false;
            forwards = !forwards;
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
        } else {
            index--;
        }
        if (!this.hasNext()) {
            throw new NoSuchElementException("No Element Exists");
        }
        Driver tempDriver = _nextDriver;
        _nextDriver = null;
        return tempDriver;
    }
}

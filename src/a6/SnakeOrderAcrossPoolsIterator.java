package a6;

import java.util.*;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
    private List<Iterable<Driver>> _listOfPools;
    private List<Iterator<Driver>> _listOfIterators;
    private Driver _nextDriver;
    private int listIndex;
    private boolean forward;

    public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
        this.forward = true;
        this.listIndex = 0;
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
        if (_nextDriver != null) {
            return true;
        }
        while (listIndex < _listOfIterators.size() && listIndex >= 0) {
            if (forward) {
                for (int i = listIndex; i < _listOfIterators.size(); i++) {
                    if (_listOfIterators.get(i).hasNext()) {
                        _nextDriver = _listOfIterators.get(i).next();
                        return true;
                    }
                }
            } else {
                for (int i = listIndex; i >= 0; i--) {
                    if (_listOfIterators.get(i).hasNext()) {
                        _nextDriver = _listOfIterators.get(i).next();
                        return true;
                    }
                }
            }
            forward = !forward;
        }
        return false;
    }

    @Override
    public Driver next() {
        if (forward) {
            listIndex++;
        } else {
            listIndex--;
        }
        if (!this.hasNext()) {
            throw new NoSuchElementException("No Element Exists");
        }
        Driver tempDriver = _nextDriver;
        _nextDriver = null;
        return tempDriver;
    }
}

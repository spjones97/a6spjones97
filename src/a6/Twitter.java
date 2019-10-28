package a6;

import java.util.*;

public class Twitter {

    public long carParkingRoof(List<Long> cars, int k) {
        // Write your code here

        // Order cars list
        List<Long> newCars = new ArrayList<Long>();
        for (int i = 0; i < cars.size(); i++) {
            long min = Long.MAX_VALUE;
            for (int j = 0; i < cars.size(); j++) {
                if (cars.get(j) < min) {
                    min = cars.get(i);
                }
            }
            newCars.add(min);
            for (int m = 0; m < cars.size(); m++) {
                if (min == cars.get(m)) {
                    cars.remove(m);
                }
            }
        }

        // Create distances list
        List<Long> lengths = new ArrayList<Long>();
        for (int i = 0; i < (newCars.size() - k); i++) {
            long car = 1;
            long length = 0;
            for (int j = i; (j < newCars.size()) && (car < k); j++) {
                length = newCars.get(j + 1) - newCars.get(j);
                car += 1;
            }
            if (car == k) {
                lengths.add(length);
            }
        }

        // Find minimum length
        long minLength = Long.MAX_VALUE;
        for (int i = 0; i < lengths.size(); i++) {
            if (lengths.get(i) < minLength) {
                minLength = lengths.get(i);
            }
        }

        return minLength;
    }
}

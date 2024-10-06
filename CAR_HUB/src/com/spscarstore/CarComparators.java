package com.spscarstore;

import java.util.Comparator;

public class CarComparators {
    // Comparator to sort cars by brand
    public static Comparator<Car> sortByBrand = new Comparator<Car>() {
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getBrand().compareToIgnoreCase(car2.getBrand());
        }
    };

    // Comparator to sort cars by year
    public static Comparator<Car> sortByYear = new Comparator<Car>() {
        @Override
        public int compare(Car car1, Car car2) {
            return Integer.compare(car1.getYear(), car2.getYear());
        }
    };
}

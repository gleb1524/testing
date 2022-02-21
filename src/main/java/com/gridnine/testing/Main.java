package com.gridnine.testing;

public class Main {
    private static final FlightsFilter filter = new FlightsFilterImpl();
    public static void main(String[] args) {
        System.out.println(filter.filterDepartsBeforeArrives(FlightBuilder.createFlights()) );
        System.out.println(filter.filterDepartingInThePast(FlightBuilder.createFlights()));
        System.out.println(filter.filterMoreThanTwoHoursGroundTime(FlightBuilder.createFlights()));
    }
}

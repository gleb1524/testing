package com.gridnine.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class FlightsFilterImplTest {

    private final FlightsFilter filter = new FlightsFilterImpl();
    private final List<Flight> flights = FlightBuilder.createFlights();
    @Test
    void testFilterDepartingInThePast() {

        List<Flight> departingInThePast = filter.filterDepartingInThePast(flights);
        Assertions.assertEquals(Arrays.asList(flights.get(2)), departingInThePast);
    }



    @Test
    void testFilterDepartsBeforeArrives() {
        List<Flight> departsBeforeArrives = filter.filterDepartsBeforeArrives(flights);
        Assertions.assertEquals(1, departsBeforeArrives.size());
        Assertions.assertTrue(departsBeforeArrives.get(0).getSegments().stream().anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())));

    }

    @Test
    void testFilterMoreThanTwoHoursGroundTime() {
        List<Flight> moreThanTwoHoursGroundTime = filter.filterMoreThanTwoHoursGroundTime(flights);
        Assertions.assertEquals(2, moreThanTwoHoursGroundTime.size());
        Assertions.assertTrue(moreThanTwoHoursGroundTime.containsAll(Arrays.asList(flights.get(4), flights.get(5))));
    }

    @Test
    void testFilterByFunction(){
        List<Flight> filteredFlights = this.filter.filterByFunction(flights, flightList ->
                flightList.stream().filter(flight -> flight.getSegments().size() > 1).collect(Collectors.toList()));
        Assertions.assertEquals(Arrays.asList(flights.get(1), flights.get(4),flights.get(5)), filteredFlights);

    }
}
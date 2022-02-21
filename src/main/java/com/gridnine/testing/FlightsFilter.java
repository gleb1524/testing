package com.gridnine.testing;


import java.util.List;
import java.util.function.UnaryOperator;

public interface FlightsFilter {

    List<Flight> filterDepartingInThePast(List<Flight> flights);
    List<Flight> filterDepartsBeforeArrives(List<Flight> flights);
    List<Flight> filterMoreThanTwoHoursGroundTime(List<Flight> flights);
    List<Flight> filterByFunction(List<Flight> flights, UnaryOperator<List<Flight>> operator);

}

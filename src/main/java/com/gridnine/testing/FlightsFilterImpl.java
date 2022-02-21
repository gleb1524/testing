package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class FlightsFilterImpl implements FlightsFilter {

    @Override
    public List<Flight> filterDepartingInThePast(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream().filter(flight ->
                        flight.getSegments().stream().anyMatch(segment -> segment.getDepartureDate().isBefore(now)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterDepartsBeforeArrives(List<Flight> flights) {
        return flights.stream().filter(flight ->
                        flight.getSegments().stream().anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterMoreThanTwoHoursGroundTime(List<Flight> flights) {

        return flights.stream().filter(flight -> {
            long totalGroundTime = 0;
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                long groundTime = ChronoUnit.MINUTES.between(flight.getSegments().get(i).getArrivalDate(), flight.getSegments().get(i + 1).getDepartureDate());
                totalGroundTime += groundTime;
                if (totalGroundTime > 120) {
                    return true;
                }
            }

            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Flight> filterByFunction(List<Flight> flights, UnaryOperator<List<Flight>> operator) {
        return operator.apply(flights);
    }
}

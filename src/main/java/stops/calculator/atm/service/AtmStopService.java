package stops.calculator.atm.service;

import stops.calculator.atm.AtmStop;
import stops.calculator.geocode.GeoPoint;

import java.util.List;

public interface AtmStopService {
    List<AtmStop> stopsNearAddress(String address);

    List<AtmStop> nearGeoPoint(GeoPoint point);

    List<AtmStop> loadStops();
}

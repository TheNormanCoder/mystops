package stops.calculator.geocode.service;

import stops.calculator.geocode.GeoPoint;

public interface GeocodingService {
    GeoPoint geocodeAddress(String address);

    double harvesineDistance(GeoPoint point1, GeoPoint point2);
}

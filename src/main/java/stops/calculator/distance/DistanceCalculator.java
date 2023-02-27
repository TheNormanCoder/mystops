package stops.calculator.distance;

import stops.calculator.geocode.GeoPoint;

public interface DistanceCalculator {
    public double haversine(GeoPoint initialPoint, GeoPoint finalPoint);
}

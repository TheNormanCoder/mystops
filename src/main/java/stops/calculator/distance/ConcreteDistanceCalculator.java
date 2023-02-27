package stops.calculator.distance;

import stops.calculator.geocode.GeoPoint;

public class ConcreteDistanceCalculator implements DistanceCalculator{
    @Override
    public double haversine(GeoPoint initialPoint, GeoPoint finalPoint) {

        double R = 6371000; // radius of Earth in meters
        double phi1 = Math.toRadians(initialPoint.getLat());
        double phi2 = Math.toRadians(finalPoint.getLat());

        double deltaPhi = Math.toRadians(finalPoint.getLat() - initialPoint.getLat());
        double deltaLon = Math.toRadians(finalPoint.getLng() - initialPoint.getLng());

        double a = Math.sin(deltaPhi / 2.0) * Math.sin(deltaPhi / 2.0)
                + Math.cos(phi1) * Math.cos(phi2)
                * Math.sin(deltaLon / 2.0) * Math.sin(deltaLon / 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double meters = R * c; // output distance in meters
        double km = meters / 1000.0; // output distance in kilometers
        meters = Math.round(meters * 1000.0) / 1000.0; // round to 3 decimal places
        km = Math.round(km * 1000.0) / 1000.0; // round to 3 decimal places

        System.out.println("Distance: " + meters + " m");
        System.out.println("Distance: " + km + " km");

        return meters;

    }
}

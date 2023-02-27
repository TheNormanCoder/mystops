package stops.calculator.geocode;

public class GeoPoint {

    private final double lat;
    private final double lng;

    public GeoPoint(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "(" + lat + ", " + lng + ")";
    }
}

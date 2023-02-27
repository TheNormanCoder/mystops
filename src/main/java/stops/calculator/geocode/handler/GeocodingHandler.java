package stops.calculator.geocode.handler;

import stops.calculator.geocode.GeoPoint;

public interface GeocodingHandler {
    public GeoPoint retriveGeocodeFrom(String endpoint, String apiKey, String address);
}

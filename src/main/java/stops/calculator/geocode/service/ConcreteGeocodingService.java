package stops.calculator.geocode.service;

import stops.calculator.geocode.GeoPoint;
import stops.calculator.distance.DistanceCalculator;
import stops.calculator.geocode.handler.GeocodingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcreteGeocodingService implements GeocodingService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConcreteGeocodingService.class);

    private final String apiKey;
    private final String url;

    private final GeocodingHandler geocodingHandler;

    private final DistanceCalculator distanceCalculator;

    public ConcreteGeocodingService(String serviceUrl, String serviceKey, GeocodingHandler geocodingHandler, DistanceCalculator distanceCalculator) {
        this.apiKey = serviceKey;
        this.url = serviceUrl;
        this.geocodingHandler = geocodingHandler;
        this.distanceCalculator = distanceCalculator;

        LOGGER.debug("Initializing HERE Geocoding service -- Url:{}, API-Key: {}", url, apiKey);
    }

    /**
     * Get latitude and longitude for a given address
     *
     * @param address address to be geocoded
     * @return latitude and longitude coordinates
     */
    @Override
    public GeoPoint geocodeAddress(String address) {
        GeoPoint coordinates = geocodingHandler.retriveGeocodeFrom(this.url,this.apiKey,address);
        return coordinates;
    }

    /**
     * Return the distance in meters between two points, using the harvesine formula.
     *
     * @param point1 a {@see GeoPoint}
     * @param point2 a {@see GeoPoint}
     * @return the distance in metter
     */
    @Override
    public  double harvesineDistance(GeoPoint point1, GeoPoint point2) {
        double distance = 0;
        distance =distanceCalculator.haversine(point1,point2);
        LOGGER.debug("Distance between {} and {} is {} meters", point1, point2, distance);
        return distance;
    }




}

package stops.calculator.atm.service;

import stops.calculator.atm.AtmStop;
import stops.calculator.geocode.GeoPoint;
import stops.calculator.atm.reader.StopsReader;
import stops.calculator.geocode.service.ConcreteGeocodingService;
import stops.calculator.geocode.service.GeocodingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConcreteAtmStopService implements AtmStopService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConcreteGeocodingService.class);

    private static final long MAX_DISTANCE = 200;  // in meters

    private final List<AtmStop> availableStops;
    private final GeocodingService geocodingService;

    private final StopsReader stopsReader;

    public ConcreteAtmStopService(GeocodingService geo, StopsReader stopsReader) {
        this.geocodingService = geo;
        this.stopsReader = stopsReader;
        availableStops = loadStops();
    }

    /**
     * Find ATM stops near a given address
     *
     * @param address a street address, eg. "via Larga 26, Milano"
     * @return max 10 nearest {@see AtmStop}
     */
    @Override
    public List<AtmStop> stopsNearAddress(String address) {
        GeoPoint position = geocodingService.geocodeAddress(address);
        LOGGER.debug("Address '{}' geolocated at {}", address, position);
        return nearGeoPoint(position);
    }

    /**
     * Find ATM stops near a given {@see GeoPoint}
     *
     * @param point a {@see GeoPoint} instance
     * @return max 10 nearest {@see AtmStop}
     */
    @Override
    public List<AtmStop> nearGeoPoint(GeoPoint point) {

        // TODO: think about a better solution for "near me"
        List<AtmStop> filteredStops = availableStops.stream()
                                        .filter(s -> geocodingService.harvesineDistance(s.getPosition(), point) < MAX_DISTANCE)
                                        .collect(toList());
        LOGGER.debug("Found #{} stops near {}", filteredStops.size(), point);

        return filteredStops;
    }

    /**
     * List all available ATM stops in Milan
     *
     * @return all available ATM stops in Milan
     */
    @Override
    public List<AtmStop> loadStops() {
        List<AtmStop> metro = stopsReader.fileReader("atm/tpl_metrofermate.csv");
        List<AtmStop> surface = stopsReader.fileReader("atm/tpl_fermate.csv");
        List<AtmStop> fullStops = new ArrayList<>();
        fullStops.addAll(metro);
        fullStops.addAll(surface);
        return fullStops;
    }
}

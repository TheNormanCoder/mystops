package stops.calculator.atm.service;

import stops.calculator.atm.reader.StopsReader;
import stops.calculator.geocode.service.GeocodingService;

public final class ConcreteAtmStopServiceBuilder {
    private StopsReader stopsReader;

    private  GeocodingService geocodingService;
    private ConcreteAtmStopServiceBuilder() {
    }

    public static ConcreteAtmStopServiceBuilder aConcreteAtmStopService() {
        return new ConcreteAtmStopServiceBuilder();
    }

    public ConcreteAtmStopServiceBuilder geocodingService(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
        return this;
    }

    public ConcreteAtmStopServiceBuilder stopsReader(StopsReader stopsReader) {
        this.stopsReader = stopsReader;
        return this;
    }
    public ConcreteAtmStopService build() {
        return new ConcreteAtmStopService(geocodingService, stopsReader);
    }
}

package stops.calculator;

import stops.calculator.atm.reader.StopsReader;
import stops.calculator.atm.reader.ConcreteStopsReader;
import stops.calculator.atm.AtmStop;
import stops.calculator.atm.service.AtmStopService;
import stops.calculator.atm.service.ConcreteAtmStopServiceBuilder;
import stops.calculator.geocode.GeoPoint;
import stops.calculator.geocode.service.GeocodingService;
import stops.calculator.geocode.service.GeocodingServiceBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AtmStopsTest {

    GeocodingService geocodingService = GeocodingServiceBuilder.geocodingService();
    StopsReader stopsReader = new ConcreteStopsReader();
    AtmStopService sut = ConcreteAtmStopServiceBuilder
            .aConcreteAtmStopService()
            .geocodingService(geocodingService)
            .stopsReader(stopsReader)
            .build();


    @Test
    public void whenLoadingStopsFromfilesTheStructureIsCreated(){
        List<AtmStop> stops = sut.loadStops();
        Assert.assertNotNull(stops);
        Assert.assertEquals(4840,stops.size());
    }

    @Test
    public void withExistingCoordinatesTheListIsCreated(){
        GeoPoint points = new GeoPoint(45.4609,9.19091);
        List<AtmStop> stops = sut.nearGeoPoint(points);
        Assert.assertNotNull(stops);
        Assert.assertTrue(stops.size() >0);
    }


    @Test
    public void withRealAddressTheListOfStopsIsCreated(){
        String street = "via larga 26 Milano";
        List<AtmStop> stops = sut.stopsNearAddress(street);
        Assert.assertNotNull(stops);
        Assert.assertTrue(stops.size() >0);
    }
}

package stops.calculator;

import stops.calculator.geocode.GeoPoint;
import stops.calculator.geocode.handler.GeocodingHandler;
import stops.calculator.geocode.handler.ConcreteGeocodingHandler;
import org.junit.Assert;
import org.junit.Test;

public class GeocodingHandlerTest {

    private GeocodingHandler sut = new ConcreteGeocodingHandler();

    @Test
    public void withExisitingAddressGeopintIsCreated(){
        String endpoint = "https://geocode.search.hereapi.com/v1/geocode";
        String apiKey = "JqbNIC56SUTR7xbz0Sp4BigeRAkLMUyCCN7DVsQ8MPI";
        String street = "via larga 26 Milano";
        GeoPoint point= sut.retriveGeocodeFrom(endpoint,apiKey,street);
        Assert.assertNotNull(point);
        Assert.assertEquals(45.4609,point.getLat(),0);
        Assert.assertEquals(9.19091,point.getLng(),0);
    }
}

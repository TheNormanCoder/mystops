package stops.calculator;

import stops.calculator.geocode.GeoPoint;
import stops.calculator.geocode.service.GeocodingService;
import stops.calculator.geocode.service.GeocodingServiceBuilder;
import org.junit.Assert;
import org.junit.Test;

public class GeocodingServiceTest {

    GeocodingService sut = GeocodingServiceBuilder.geocodingService();

    @Test
    public void withFirstAddressGeopintIsCreatedCorrectly(){
        String street = "via larga 26 Milano";
        GeoPoint point= sut.geocodeAddress(street);
        Assert.assertNotNull(point);
        Assert.assertEquals(45.4609,point.getLat(),0);
        Assert.assertEquals(9.19091,point.getLng(),0);
    }


    @Test
    public void withSecondAddressGeopintIsCreatedCorrectly(){
        String street = "via spadolini 6 Milano";
        GeoPoint point= sut.geocodeAddress(street);
        Assert.assertNotNull(point);
        Assert.assertEquals(45.44291,point.getLat(),0);
        Assert.assertEquals(9.19046,point.getLng(),0);
    }
}

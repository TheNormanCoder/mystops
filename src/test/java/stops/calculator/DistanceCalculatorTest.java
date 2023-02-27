package stops.calculator;

import stops.calculator.distance.DistanceCalculator;
import stops.calculator.distance.ConcreteDistanceCalculator;
import stops.calculator.geocode.GeoPoint;
import org.junit.Assert;
import org.junit.Test;

public class DistanceCalculatorTest {

    private DistanceCalculator sut = new ConcreteDistanceCalculator();

    @Test
    public void withSameAddressTheDistanceIsZero(){
        GeoPoint first = new GeoPoint(45.462712945354,9.19420965647107);
        GeoPoint second = new GeoPoint(45.462712945354,9.19420965647107);
        double result = sut.haversine(first,second);
        Assert.assertEquals(0.0,result,0.0);
    }

    @Test
    public void withDifferentPointsTheDistanceIsNotZero(){
        GeoPoint first = new GeoPoint(45.44291,9.19046);
        GeoPoint second = new GeoPoint(45.4609,9.19091);
        double result = sut.haversine(first,second);
        Assert.assertTrue(result > 0.0);
    }

}

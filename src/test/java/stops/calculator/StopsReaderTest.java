package stops.calculator;

import stops.calculator.atm.reader.StopsReader;
import stops.calculator.atm.reader.ConcreteStopsReader;
import stops.calculator.atm.AtmStop;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StopsReaderTest {
    private StopsReader sut = new ConcreteStopsReader();

    @Test
    public void withFermateCsvTheListOfStopsIsCreated(){
        List<AtmStop>  stops = sut.fileReader("atm/tpl_fermate.csv");
        Assert.assertNotNull(stops);
        Assert.assertTrue(stops.size() > 0);
        Assert.assertTrue(stops.size()  == 4730);
    }

    @Test
    public void withMetroFermateCsvTheListOfStopsIsCreated(){
        List<AtmStop>  stops = sut.fileReader("atm/tpl_metrofermate.csv");
        Assert.assertNotNull(stops);
        Assert.assertTrue(stops.size() > 0);
        Assert.assertTrue(stops.size()  == 110);
    }
}

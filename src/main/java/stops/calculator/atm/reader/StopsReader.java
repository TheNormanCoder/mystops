package stops.calculator.atm.reader;

import stops.calculator.atm.AtmStop;

import java.util.List;

public interface StopsReader {
    public List<AtmStop> fileReader(String fileName);
}

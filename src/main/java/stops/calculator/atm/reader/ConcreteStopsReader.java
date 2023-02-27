package stops.calculator.atm.reader;

import stops.calculator.atm.AtmStop;
import stops.calculator.geocode.GeoPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConcreteStopsReader implements  StopsReader{
    private final static Logger LOGGER = LoggerFactory.getLogger(ConcreteStopsReader.class);
    @Override
    public List<AtmStop> fileReader(String fileName) {
        List<AtmStop> atmStops = new ArrayList<>();
        int rowCounter = 0;
        try (InputStream is = ConcreteStopsReader.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (rowCounter == 0) {
                    rowCounter++;
                    continue;
                }
                String[] values = line.split(";");
                LineDescriptor descriptor =  modifyAttributes(values,fileName);
                String code=descriptor.getCode();
                String description=descriptor.getDescription();
                List<String> availableLines=descriptor.getAvailableLines();
                double longitude = Double.parseDouble(values[3]);
                double latitude = Double.parseDouble(values[4]);
                GeoPoint position = new GeoPoint(latitude, longitude);
                AtmStop atmStop = new AtmStop();
                atmStop.setCode(code);
                atmStop.setDescription(description);
                atmStop.setAvailableLines(availableLines);
                atmStop.setPosition(position);
                atmStops.add(atmStop);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info(atmStops.toString());
        return atmStops;
    }

    private LineDescriptor modifyAttributes(String[] values, String fileName) {
       LineDescriptor descriptor = new LineDescriptor();
        if(fileName.contains("metro")){
            descriptor.code = values[1].toLowerCase();
            descriptor.description = values[1];
            descriptor.availableLines = Arrays.asList(values[2].split(","));
            descriptor.availableLines = descriptor.availableLines.stream()
                    .map(s -> "M" + s)
                    .map(s -> s.replaceAll("^M\"", "\"M"))
                    .collect(Collectors.toList());
        }else {
            descriptor.code = values[0];
            descriptor.description = values[1];
            descriptor.availableLines = Arrays.asList(values[2].split(","));
        }
        return descriptor;
    }
}

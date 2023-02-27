package stops.calculator;

/**
 * Hello world!
 *
 */
import com.google.gson.Gson;
import stops.calculator.atm.reader.StopsReader;
import stops.calculator.atm.reader.ConcreteStopsReader;
import stops.calculator.atm.AtmStop;
import stops.calculator.atm.service.AtmStopService;
import stops.calculator.atm.service.ConcreteAtmStopServiceBuilder;
import stops.calculator.geocode.service.GeocodingService;
import stops.calculator.geocode.service.GeocodingServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static spark.Spark.*;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private static final Gson gson = new Gson();


    public static void main(String[] args) {
        GeocodingService geocodingService = GeocodingServiceBuilder.geocodingService();
        StopsReader stopsReader = new ConcreteStopsReader();
        AtmStopService service = ConcreteAtmStopServiceBuilder
                .aConcreteAtmStopService()
                .geocodingService(geocodingService)
                .stopsReader(stopsReader)
                .build();
        // main application endpoint
        get("/atm/stops", (req, res) -> {
            try {
            String address = req.queryParams("address");
            List<AtmStop> stops = service.stopsNearAddress(address);
                return response(stops);
            } catch (NullPointerException | IllegalArgumentException | NoSuchElementException e) {
                res.status(400);
                return Map.of("error", e.getMessage());
            } catch (Exception e) {
                res.status(500);
                return Map.of("error", e.getMessage());
            }
        }, gson::toJson);

        LOGGER.info("Service available at http://localhost:4567/atm/stops");
    }

    private static Map<String, ?> response(List<AtmStop> stops) {
        if(stops == null || stops.isEmpty() || stops.size()==0)
            return Map.of("stops", 0);
        else
            return Map.of("stops", stops);
    }


}

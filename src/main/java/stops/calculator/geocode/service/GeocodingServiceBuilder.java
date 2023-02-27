package stops.calculator.geocode.service;

import stops.calculator.helper.IOHelper;
import stops.calculator.distance.DistanceCalculator;
import stops.calculator.geocode.handler.GeocodingHandler;
import stops.calculator.geocode.handler.ConcreteGeocodingHandler;
import stops.calculator.distance.ConcreteDistanceCalculator;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class GeocodingServiceBuilder {
    @SuppressWarnings("unchecked")

    private static final Yaml yaml = new Yaml();
    public static GeocodingService geocodingService() {
        Map<String, Object> config = loadConfiguration();
        Map<String, Object> appConfig = ((Map<String, Object>) config.get("app"));
        String geocodingServiceUrl = appConfig.get("geocodingServiceUrl").toString();
        String apiKey = appConfig.get("geocodingApiKey").toString();
        GeocodingHandler geocodingHandler = new ConcreteGeocodingHandler();
        DistanceCalculator distanceCalculator = new ConcreteDistanceCalculator();
        return new ConcreteGeocodingService(geocodingServiceUrl, apiKey, geocodingHandler, distanceCalculator);
    }

    /**
     * Load application configuration from file: src/main/resources/config.yml
     *
     * @return configuration values as a Map
     */
    public static Map<String, Object> loadConfiguration() {
        InputStream configFile = IOHelper.loadClasspathResource("config.yml");
        return yaml.load(configFile);
    }
}
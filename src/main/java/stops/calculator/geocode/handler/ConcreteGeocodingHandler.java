package stops.calculator.geocode.handler;

import stops.calculator.geocode.GeoPoint;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ConcreteGeocodingHandler implements GeocodingHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConcreteGeocodingHandler.class);
    @Override
    public GeoPoint retriveGeocodeFrom(String endpoint, String apiKey, String address) {
        try {

            String encodedQuery = URLEncoder.encode(address, "UTF-8");
            String urlStr = endpoint + "?apiKey=" + apiKey + "&q=" + encodedQuery;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                LOGGER.error("Failed : HTTP error code : " + conn.getResponseCode());
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            String response = sb.toString();
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray items = jsonResponse.getJSONArray("items");
            JSONObject item = items.getJSONObject(0);
            JSONObject position = item.getJSONObject("position");
            LOGGER.info(position.get("lat") +"-"+position.get("lng"));
            String lat = position.get("lat").toString();
            String lng = position.get("lng").toString();
            conn.disconnect();
            GeoPoint point =  new GeoPoint(Double.parseDouble(lat),Double.parseDouble(lng));
            return point;

        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage(),e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        } catch (JSONException e) {
            LOGGER.error(e.getMessage(),e);
        }
        return null;
    }
}

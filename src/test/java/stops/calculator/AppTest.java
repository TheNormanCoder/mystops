package stops.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import stops.calculator.App;

class AppTest {

    private static final int PORT = 4567;
    private static final String BASE_URL = "http://localhost:" + PORT;
    private static Thread appThread;

    @BeforeAll
    static void startApp() throws InterruptedException {
        appThread = new Thread(() -> {
            App.main(new String[] {});
        });
        appThread.start();
        Thread.sleep(1000);
    }

    @AfterAll
    static void stopApp() {
        appThread.interrupt();
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    void whenCallingApplicationResponseShouldBe200()  {
        // Make an HTTP GET request to the App's endpoint
        try {
        String address = "via%20larga%2026%20Milano";
        URL url = null;
        url = new URL(BASE_URL + "/atm/stops?address=" + address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        String responseBody = null;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8")) {
                responseBody = scanner.useDelimiter("\\A").next();
            }
        }
        assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        assertNotNull(responseBody);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package web;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


//Start after starting server
public class MainExchangeControllerTest {
    @Test
    public void buy() throws Exception {
        System.out.println(printUrlContents(new URL("http://localhost:8080/buy?sum=78")));
    }

    @Test
    public void sell() throws Exception {
        System.out.println(printUrlContents(new URL("http://localhost:8080/sell?sum=78")));
    }

    private static String printUrlContents(URL url) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStream stream = url.openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

            while ((reader.ready())) {
                sb.append(reader.readLine());
            }

        }
        return sb.toString();
    }


}
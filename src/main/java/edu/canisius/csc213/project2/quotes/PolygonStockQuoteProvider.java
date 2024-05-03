package edu.canisius.csc213.project2.quotes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import edu.canisius.csc213.project2.util.*;

public class PolygonStockQuoteProvider implements StockQuoteProvider{

    @Override
    public String getEndpointUrl(String symbolName, String date, String apiKey) {
        try {
            // Attempt to parse the date
            java.time.LocalDate.parse(date);
        } catch (java.time.format.DateTimeParseException e) {
            // If parsing fails, throw IllegalArgumentException
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
        try {
            return new URL("https", "api.polygon.io", "/v2/aggs/ticker/" + symbolName + "/range/1/day/" + date + "/" + date)
                    .toURI()
                    .toString() + "?apiKey=" + apiKey;
        } catch (URISyntaxException | IOException e) {
            throw new IllegalArgumentException("Invalid URL components: " + e.getMessage());
        }
    }
    public StockQuote getStockQuote(String stockQuoteEndpoint) throws IOException {
        String json = sendGetRequest(stockQuoteEndpoint);
        PolygonJsonReplyTranslator jft = new PolygonJsonReplyTranslator();
        return jft.translateJsonToFinancialInstrument(json);

    }

    public static String sendGetRequest(String endpointUrl) throws IOException {
        URL url = new URL(endpointUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            return response.toString();
        }
    }
    

}

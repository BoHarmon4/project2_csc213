package edu.canisius.csc213.project2.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.canisius.csc213.project2.quotes.*;


import java.io.IOException;

public class PolygonJsonReplyTranslator {

    public StockQuote translateJsonToFinancialInstrument(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        String symbol = rootNode.path("ticker").asText();
        Double openPrice = rootNode.path("open").asDouble();
        Double highestPrice = rootNode.path("highest").asDouble();
        Double lowestPrice = rootNode.path("lowest").asDouble();
        int numberOfTransactions = rootNode.path("Number Of Transactions").asInt();
        Double closePrice = rootNode.path("close").asDouble();
        long timeStamp = rootNode.path("timestamp").asLong();
        Double tradingVolume = rootNode.path("trading volume").asDouble();

        return new StockQuote(symbol, closePrice, highestPrice, lowestPrice, numberOfTransactions, openPrice, timeStamp, tradingVolume);
    }
}

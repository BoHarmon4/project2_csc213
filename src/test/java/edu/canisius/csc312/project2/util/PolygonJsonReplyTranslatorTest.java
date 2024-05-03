package edu.canisius.csc312.project2.util;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.canisius.csc213.project2.quotes.StockQuote;
import edu.canisius.csc213.project2.util.PolygonJsonReplyTranslator;

public class PolygonJsonReplyTranslatorTest {

    private PolygonJsonReplyTranslator translator;

    @Before
    public void setUp() {
        translator = new PolygonJsonReplyTranslator();
    }

    @Test
    public void testTranslateJsonToFinancialInstrument() throws IOException {
        String json = "{\"ticker\":\"AAPL\",\"queryCount\":1,\"resultsCount\":1,\"adjusted\":true,\"results\":[{\"v\":7.1583932e+07,\"vw\":170.0693,\"o\":171.75,\"c\":169.38,\"h\":173.76,\"l\":168.27,\"t\":1713240000000,\"n\":834229}],\"status\":\"OK\",\"request_id\":\"cea40d0bad68033d12e7225feff8dd54\",\"count\":1}";
        StockQuote instrument = translator.translateJsonToFinancialInstrument(json);

        assertEquals("AAPL", instrument.getSymbol());
        assertEquals(169.38, instrument.getClosePrice(), 0.001);
        assertEquals(173.76, instrument.getHighestPrice(), 0.001);
        assertEquals(168.27, instrument.getLowestPrice(), 0.001);
        assertEquals(834229, instrument.getNumberOfTransactions());
        assertEquals(171.75, instrument.getOpenPrice(), 0.001);
        assertEquals(1713240000000L, instrument.getTimestamp());
        assertEquals(71583932.0, instrument.getTradingVolume(), 0.001);
    }
    public StockQuote translateJsonToFinancialInstrument(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        String symbol = rootNode.path("ticker").asText();
        JsonNode resultsNode = rootNode.path("results").path(0);
        double closePrice = resultsNode.path("c").asDouble();
        double highestPrice = resultsNode.path("h").asDouble();
        double lowestPrice = resultsNode.path("l").asDouble();
        int numberOfTransactions = resultsNode.path("n").asInt();
        double openPrice = resultsNode.path("o").asDouble();
        long timestamp = resultsNode.path("t").asLong();
        double tradingVolume = resultsNode.path("v").asDouble();

        return new StockQuote(symbol, closePrice, highestPrice, lowestPrice, numberOfTransactions, openPrice, timestamp, tradingVolume);
    }
}


package edu.canisius.csc213.project2.quotes;

public class StockQuote {
    String symbol;
    double closePrice;
    double highestPrice;
    double lowestPrice;
    int numberOfTransactions;
    double openPrice;
    long timeStamp;
    double tradingVolume;
    public StockQuote(String stock, double one, double two, double three, int num, double four, long bigNum, double five){
        symbol = stock;
        closePrice = one;
        highestPrice = two;
        lowestPrice = three;
        numberOfTransactions = num;
        openPrice = four;
        timeStamp = bigNum;
        tradingVolume = five;
    }
    public String getSymbol(){
        return symbol;
    }
    public double getClosePrice(){
        return closePrice;
    }
    public double getHighestPrice(){
        return highestPrice;
    }
    public double getLowestPrice(){
        return lowestPrice;
    }
    public int getNumberOfTransactions(){
        return numberOfTransactions;
    }
    public double getOpenPrice(){
        return openPrice;
    }
    public long getTimestamp(){
        return timeStamp;
    }
    public double getTradingVolume(){
        return tradingVolume;
    }
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("Symbol: ").append(symbol).append("\n");
        sb.append("Close Price: ").append(closePrice).append("\n");
        sb.append("Highest Price: ").append(highestPrice).append("\n");
        sb.append("Lowest Price: ").append(lowestPrice).append("\n");
        sb.append("Number of Transactions: ").append(numberOfTransactions).append("\n");
        sb.append("Open Price: ").append(openPrice).append("\n");
        sb.append("Trading Volume: ").append(tradingVolume).append("\n");
        return sb.toString();
    }
}

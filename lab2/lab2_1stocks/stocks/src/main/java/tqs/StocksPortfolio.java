package tqs;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio implements IStockmarketService {
    private final IStockmarketService stockMarket;
    private final List<Stock> stocks;

    public StocksPortfolio(IStockmarketService stockMarket) {
        this.stockMarket = stockMarket;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public double totalValue() {
        double value = 0.0;
        for (Stock s : stocks) {
            value += stockMarket.lookUpPrice(s.getLabel()) * s.getQuantity();
        }
        return value;
    }

    public double lookUpPrice(String label) {
        return stockMarket.lookUpPrice(label);
    }

    public List<Stock> mostValuableStocks(int topN) {
        return stocks.stream()
                .sorted((s1, s2) -> Double.compare(
                        stockMarket.lookUpPrice(s2.getLabel()) * s2.getQuantity(),
                        stockMarket.lookUpPrice(s1.getLabel()) * s1.getQuantity()))
                .limit(topN)
                .toList();
    }
}

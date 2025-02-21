package tqs;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT) //Evita warnings
public class StocksPortfolioTest {
    @Mock
    IStockmarketService stockMarketService;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void calcTotalValueAnnotations() {
        // Prepare a mock to substitute the remote stockmarket service (@Mock annotation)
        IStockmarketService market = mock(IStockmarketService.class);

        // Create an instance of the subject under test (SuT) and use the mock to set the
        //(remote) service instance.
        StocksPortfolio portfolio = new StocksPortfolio(market);

        // Load the mock with the proper expectations (when...thenReturn)
        when(market.lookUpPrice("EBAY")).thenReturn(4.0);
        when(market.lookUpPrice("MSFT")).thenReturn(1.5);

        // Execute the test (use the service in the SuT)
        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 4));
        double result = portfolio.totalValue();

        // Verify the result (assert) and the use of the mock (verify)
        assertEquals(14.0, result);
        assertThat(result, is(14.0));
        verify(market, times(2)).lookUpPrice(anyString());
    }

    @Test
    void testMostValuableStocksWithFewerStocksThanRequested() {
        portfolio.addStock(new Stock("AAPL", 2));
        portfolio.addStock(new Stock("GOOGL", 3));

        List<Stock> result = portfolio.mostValuableStocks(5);
        assertEquals(2, result.size());
        assertEquals("AAPL", result.get(0).getLabel());
        assertEquals("GOOGL", result.get(1).getLabel());
    }

    @Test
    void testMostValuableStocksWithMoreStocksThanRequested() {
        when(stockMarketService.lookUpPrice("AAPL")).thenReturn(150.0);
        when(stockMarketService.lookUpPrice("GOOGL")).thenReturn(100.0);
        when(stockMarketService.lookUpPrice("MSFT")).thenReturn(200.0);

        portfolio.addStock(new Stock("AAPL", 2));
        portfolio.addStock(new Stock("GOOGL", 3));
        portfolio.addStock(new Stock("MSFT", 1));

        List<Stock> result = portfolio.mostValuableStocks(2);
        assertEquals(2, result.size());
        assertEquals("AAPL", result.get(0).getLabel());
        assertEquals("GOOGL", result.get(1).getLabel());
    }

    @Test
    void testMostValuableStocksWithExactNumberOfStocksRequested() {
        when(stockMarketService.lookUpPrice("AAPL")).thenReturn(150.0);
        when(stockMarketService.lookUpPrice("GOOGL")).thenReturn(100.0);
        when(stockMarketService.lookUpPrice("MSFT")).thenReturn(200.0);

        portfolio.addStock(new Stock("AAPL", 2));
        portfolio.addStock(new Stock("GOOGL", 3));
        portfolio.addStock(new Stock("MSFT", 1));

        List<Stock> result = portfolio.mostValuableStocks(3);
        assertEquals(3, result.size());
        assertEquals("AAPL", result.get(0).getLabel());
        assertEquals("GOOGL", result.get(1).getLabel());
        assertEquals("MSFT", result.get(2).getLabel());
    }
}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//test the Stock Class
public class StockTest {
    Stock stockBreakEven1;
    Stock stockBreakEven2;
    Stock stockGain;
    Stock stockLoss;

    @BeforeEach
    void setup() {
        stockBreakEven1 = new Stock("a",1, 500, 500, 0, 0, 0, 0);
        stockBreakEven2 = new Stock("b", 10, 850, 800, 1000, 250, 250, 0);
        stockGain = new Stock("c",0.5, 100, 1100, 0, 10, 10, 0.1);
        stockLoss = new Stock("d",0.1, 1000, 1100, 0, 10, 10, 0.1);
    }

    @Test
    void stockEarningTest() {
        assertEquals(0,stockBreakEven1.stockEarning());
        assertEquals(0,stockBreakEven2.stockEarning());
        assertEquals(432,stockGain.stockEarning());
        assertEquals(-10,stockLoss.stockEarning());
    }

    @Test
    void profitOrLossTest() {
        assertEquals("Break even",stockBreakEven1.profitOrLoss());
        assertEquals("Profit",stockGain.profitOrLoss());
        assertEquals("Loss",stockLoss.profitOrLoss());

    }
    @Test
    void getNameTest() {
        assertEquals("a",stockBreakEven1.getName());
        assertEquals("b",stockBreakEven2.getName());
        assertEquals("c",stockGain.getName());
        assertEquals("d",stockLoss.getName());
    }

    @Test
    void breakEvenSellPriceTest(){
        assertEquals(500,stockBreakEven1.breakEvenSellPrice());
        assertEquals(800,stockBreakEven2.breakEvenSellPrice());
        assertEquals(140,stockGain.breakEvenSellPrice());
        assertEquals(1200,stockLoss.breakEvenSellPrice());

    }

}
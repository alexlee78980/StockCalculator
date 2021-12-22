package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
//test the StockList Class
public class StockListTest {
    private StockList stockList1;
    private StockList stockList2;
    private StockList stockList3;
    private Stock stockBreakEven1;
    private Stock stockBreakEven2;
    private Stock stockGain;
    private Stock stockLoss1;
    private Stock stockLoss2;


    @BeforeEach
    void setup() {
        stockBreakEven1 = new Stock("a", 1, 500, 500, 0, 0, 0, 0);
        stockBreakEven2 = new Stock("b", 10, 850, 800, 1000, 250, 250, 0);
        stockGain = new Stock("c", 0.5, 100, 1100, 0, 10, 10, 0.1);
        stockLoss1 = new Stock("d", 0.1, 1000, 1100, 0, 10, 10, 0.1);
        stockLoss2 = new Stock("e", 100, 10, 0, 0, 10, 10, 0.9);
        stockList1 = new StockList();
        stockList2 = new StockList();
        stockList3 = new StockList();
        stockList2.addStock(stockBreakEven1);
        stockList2.addStock(stockBreakEven2);
        stockList2.addStock(stockGain);
        stockList2.addStock(stockLoss1);
        stockList3.addStock(stockBreakEven1);
        stockList3.addStock(stockBreakEven2);
        stockList3.addStock(stockGain);
        stockList3.addStock(stockLoss1);
        stockList3.addStock(stockLoss2);
    }

    @Test
    void totalProfitTest() {
        assertEquals(0, stockList1.totalProfit());
        assertEquals(422, stockList2.totalProfit());
        assertEquals(-598, stockList3.totalProfit());
    }

    @Test
    void nameOfStringBoughtTest() {
        ArrayList answerStockListTwoName = new ArrayList<String>();
        answerStockListTwoName.add("a");
        answerStockListTwoName.add("b");
        answerStockListTwoName.add("c");
        answerStockListTwoName.add("d");
        ArrayList answerStockListThreeName = new ArrayList<String>();
        answerStockListThreeName.add("a");
        answerStockListThreeName.add("b");
        answerStockListThreeName.add("c");
        answerStockListThreeName.add("d");
        answerStockListThreeName.add("e");
        assertEquals(new ArrayList<>(), stockList1.nameOfStocksBought());
        assertEquals(answerStockListTwoName, stockList2.nameOfStocksBought());
        assertEquals(answerStockListThreeName, stockList3.nameOfStocksBought());
    }

    @Test
    void profitEachStockTest() {
        ArrayList answerStockListTwoProfit = new ArrayList<String>();
        answerStockListTwoProfit.add("a : 0.0");
        answerStockListTwoProfit.add("b : 0.0");
        answerStockListTwoProfit.add("c : 432.0");
        answerStockListTwoProfit.add("d : -10.0");
        ArrayList answerStockListThreeProfit = new ArrayList<String>();
        answerStockListThreeProfit.add("a : 0.0");
        answerStockListThreeProfit.add("b : 0.0");
        answerStockListThreeProfit.add("c : 432.0");
        answerStockListThreeProfit.add("d : -10.0");
        answerStockListThreeProfit.add("e : -1020.0");
        assertEquals(new ArrayList<>(), stockList1.profitEachStock());
        assertEquals(answerStockListTwoProfit, stockList2.profitEachStock());
        assertEquals(answerStockListThreeProfit, stockList3.profitEachStock());
    }

    @Test
    void profitOrLossListTest() {
        ArrayList answerStockListTwoProfit = new ArrayList<String>();
        answerStockListTwoProfit.add("a : Break even");
        answerStockListTwoProfit.add("b : Break even");
        answerStockListTwoProfit.add("c : Profit");
        answerStockListTwoProfit.add("d : Loss");
        ArrayList answerStockListThreeProfit = new ArrayList<String>();
        answerStockListThreeProfit.add("a : Break even");
        answerStockListThreeProfit.add("b : Break even");
        answerStockListThreeProfit.add("c : Profit");
        answerStockListThreeProfit.add("d : Loss");
        answerStockListThreeProfit.add("e : Loss");
        assertEquals(new ArrayList<>(), stockList1.profitOrLossList());
        assertEquals(answerStockListTwoProfit, stockList2.profitOrLossList());
        assertEquals(answerStockListThreeProfit, stockList3.profitOrLossList());

    }

    @Test
    void breakEvenSellPriceEachStockTest() {

        ArrayList answerStockListTwoProfit = new ArrayList<String>();
        answerStockListTwoProfit.add("a : 500.0");
        answerStockListTwoProfit.add("b : 800.0");
        answerStockListTwoProfit.add("c : 140.0");
        answerStockListTwoProfit.add("d : 1200.0");
        ArrayList answerStockListThreeProfit = new ArrayList<String>();
        answerStockListThreeProfit.add("a : 500.0");
        answerStockListThreeProfit.add("b : 800.0");
        answerStockListThreeProfit.add("c : 140.0");
        answerStockListThreeProfit.add("d : 1200.0");
        answerStockListThreeProfit.add("e : 10.2");
        assertEquals(new ArrayList<>(), stockList1.breakEvenSellPriceEachStock());
        assertEquals(answerStockListTwoProfit, stockList2.breakEvenSellPriceEachStock());
        assertEquals(answerStockListThreeProfit, stockList3.breakEvenSellPriceEachStock());

    }
}



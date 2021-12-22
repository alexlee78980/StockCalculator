package persistence;

import model.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
// code inspired from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkStock(String name, double shares, double purchasePrice, double salePrice, double dividend,
                              double buyCommission, double sellCommission, double cgt, Stock stock) {
        assertEquals(name, stock.getName());
        assertEquals(shares, stock.getShares());
        assertEquals(purchasePrice, stock.getPurchasePrice());
        assertEquals(salePrice, stock.getSalePrice());
        assertEquals(dividend, stock.getDividend());
        assertEquals(buyCommission, stock.getBuyCommission());
        assertEquals(sellCommission, stock.getSellCommission());
        assertEquals(cgt, stock.getCgt());

    }
}
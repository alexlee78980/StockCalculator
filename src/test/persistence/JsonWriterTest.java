package persistence;

import model.Stock;
import model.StockList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
//code inspired from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            StockList sl = new StockList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStockList() {
        try {
            StockList sl = new StockList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStockList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStockList.json");
            sl = reader.read();
            assertEquals(0, sl.sizeOfList());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStockList() {
        try {
            StockList sl = new StockList();
            sl.addStock(new Stock("tesla", 10, 1000, 1000, 1000, 1000,
                    1000, 0.1));
            sl.addStock(new Stock("apple", 5, 90, 91, 910, 1000,
                    6000, 0.1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStockList.json");
            writer.open();
            writer.write(sl);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralStockList.json");
            sl = reader.read();
            List<Stock> stocks = sl.getStock();
            assertEquals(2, stocks.size());
            checkStock("tesla", 10, 1000, 1000, 1000, 1000,
                    1000, 0.1, stocks.get(0));
            checkStock("apple", 5, 90, 91, 910, 1000,
                    6000, 0.1, stocks.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

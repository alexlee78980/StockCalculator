package persistence;

import model.Stock;
import model.StockList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
// code inspired from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            StockList sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStockList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStockList.json");
        try {
            StockList sl = reader.read();
            assertEquals(0, sl.sizeOfList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStockList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStockList.json");
        try {
            StockList sl = reader.read();
            List<Stock> stocks = sl.getStock();
            assertEquals(2, sl.sizeOfList());
            checkStock("tesla", 10, 1000, 1000, 1000, 1000,
                    1000, 0.1, stocks.get(0));
            checkStock("apple", 5, 90, 91, 910, 1000,
                    6000, 0.1, stocks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

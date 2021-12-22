package persistence;


import model.Stock;
import model.StockList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
// code inspired from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads stockList from JSON data stored in file

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads stockList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public StockList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStockList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses stockList from JSON object and returns it
    private StockList parseStockList(JSONObject jsonObject) {
        StockList sl = new StockList();
        addStocks(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses stocks from JSON object and adds them to stockList
    private void addStocks(StockList sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("stocks");
        for (Object json : jsonArray) {
            JSONObject nextStock = (JSONObject) json;
            addStock(sl, nextStock);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses stock from JSON object and adds it to Stocklist
    private void addStock(StockList sl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double share = jsonObject.getDouble("shares");
        double purchasePrice = jsonObject.getDouble("purchase price");
        double salePrice = jsonObject.getDouble("sale price");
        double dividend = jsonObject.getDouble("dividend");
        double buyCommission = jsonObject.getDouble("buy commission");
        double sellCommission = jsonObject.getDouble("sale commission");
        double cgt = jsonObject.getDouble("cgt");
        Stock stock = new Stock(name, share, purchasePrice, salePrice, dividend, buyCommission, sellCommission, cgt);
        sl.addStock(stock);
    }
}


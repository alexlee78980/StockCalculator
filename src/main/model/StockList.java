package model;
//a stock transaction list

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// code json inspired from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class StockList implements Writable {
    private List<Stock> stockList;

    //EFFECT: construct an empty list of stock
    public StockList() {
        stockList = new ArrayList<Stock>();
    }


    //MODIFIES: this
    //EFFECT: add stock s to the stockList and log the event
    public void addStock(Stock s) {
        EventLog.getInstance().logEvent(new Event("Added stock: " + s.getName()));
        stockList.add(s);
    }

    //EFFECT: return the total profit or loss of all stocks entered and log the event
    public double totalProfit() {
        EventLog.getInstance().logEvent(new Event("Calculated totalProfit for: " + this.nameOfStocksBought()));
        double total = 0;
        for (Stock s : stockList) {
            total += s.stockEarning();
        }
        return total;
    }

    //EFFECT: return a list stating the name of the stocks entered and log the event
    public List<String> nameOfStocksBought() {
        EventLog.getInstance().logEvent(new Event("Display names of stocks bought"));

        ArrayList allNames = new ArrayList<String>();
        for (Stock s : stockList) {
            allNames.add(s.getName());
        }
        return allNames;
    }

    //EFFECT: return a list stating the profit or loss of each stock entered and log the event
    public List<String> profitEachStock() {
        EventLog.getInstance().logEvent(new Event("Calculated profit for each stock for: "
                + this.nameOfStocksBought()));
        ArrayList profit = new ArrayList<String>();
        for (Stock s : stockList) {
            profit.add(s.getName() + " : " + s.stockEarning());
        }
        return profit;
    }

    //EFFECT: return a list stating the sellPrices needed to break even for each stock entered and log the event
    public List<String> breakEvenSellPriceEachStock() {
        EventLog.getInstance().logEvent(new Event("Calculated break even sell price of each stock for: "
                + this.nameOfStocksBought()));
        ArrayList breakEven = new ArrayList<String>();
        for (Stock s : stockList) {
            breakEven.add(s.getName() + " : " + s.breakEvenSellPrice());
        }
        return breakEven;
    }

    //EFFECT: return a list stating if each stock entered is profit, loss or break even and log the event
    public List<String> profitOrLossList() {
        EventLog.getInstance().logEvent(new Event("display profit or loss of each stock for: "
                + this.nameOfStocksBought()));
        ArrayList gainOrLose = new ArrayList<String>();
        for (Stock s : stockList) {
            gainOrLose.add(s.getName() + " : " + s.profitOrLoss());
        }
        return gainOrLose;
    }

    public int sizeOfList() {
        return stockList.size();
    }

    public List<Stock> getStock() {
        return Collections.unmodifiableList(stockList);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("stocks", stockToJson());
        return json;
    }

    // EFFECTS: returns stocks in this stockList as a JSON array
    private JSONArray stockToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Stock t : stockList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}

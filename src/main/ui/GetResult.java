package ui;

import model.StockList;

import javax.swing.*;
import java.awt.*;

//inspired by part of https://www.youtube.com/watch?v=Kmgo00avvEw&t=1s
//display the result the user selected
public class GetResult extends FrameLayout {
    String result;
    StockList stockList;

    public GetResult(String result, StockList stockList) {
        super();
        this.result = result;
        this.stockList = stockList;
        this.setLayout(new GridLayout(0, 1));
        getResultByString();
    }

    //MODIFIES: this
    //EFFECT: display on JFrame results of stockList according to the result selected
    public void getResultByString() {
        String[] list;
        if (result.equals("a")) {
            list = stockList.nameOfStocksBought().toArray(new String[0]);
            JList<String> listName = new JList<>(list);
            this.add(new JScrollPane(listName));
        } else if (result.equals("b")) {
            JLabel total = new JLabel(String.valueOf(stockList.totalProfit()));
            this.add(total);
        } else if (result.equals("c")) {
            list = stockList.profitEachStock().toArray(new String[0]);
            JList<String> listProfit = new JList<>(list);
            this.add(new JScrollPane(listProfit));
        } else if (result.equals("d")) {
            list = stockList.profitOrLossList().toArray(new String[0]);
            JList<String> listIdentify = new JList<>(list);
            this.add(new JScrollPane(listIdentify));
        } else if (result.equals("e")) {
            list = stockList.breakEvenSellPriceEachStock().toArray(new String[0]);
            JList<String> listBreakEven = new JList<>(list);
            this.add(new JScrollPane(listBreakEven));
        } else if (result.equals("f")) {
            resultAll();
        }
    }

    //MODIFIES: this
    //EFFECT: display on JFrame all results of the stock
    //- Name of Each Stock
    //-Total Profit of All Stocks
    //- Profit Each Stock
    //- "Profit Or Loss For Each Stock"
    //- "Break Even Sale Price Each Stock"
    private void resultAll() {
        String[] list;
        createLabel("Name of Each Stock");
        list = stockList.nameOfStocksBought().toArray(new String[0]);
        JList<String> listName = new JList<>(list);
        this.add(new JScrollPane(listName));
        createLabel("Total Profit of All Stocks");
        JLabel total = new JLabel(String.valueOf(stockList.totalProfit()));
        this.add(total);
        createLabel("Profit Each Stock");
        list = stockList.profitEachStock().toArray(new String[0]);
        JList<String> listProfit = new JList<>(list);
        this.add(new JScrollPane(listProfit));
        createLabel("Profit Or Loss For Each Stock");
        list = stockList.profitOrLossList().toArray(new String[0]);
        JList<String> listIdentify = new JList<>(list);
        this.add(new JScrollPane(listIdentify));
        createLabel("Break Even Sale Price Each Stock");
        list = stockList.breakEvenSellPriceEachStock().toArray(new String[0]);
        JList<String> listBreakEven = new JList<>(list);
        this.add(new JScrollPane(listBreakEven));
    }

    //MODIFIES:this
    //EFFECT: create a new JLabel with String parameter, change the font and add it to the JFrame
    public void createLabel(String label) {
        JLabel label1 = new JLabel(label);
        label1.setFont(new Font("Serif", Font.BOLD, 30));
        this.add(label1);
    }
}

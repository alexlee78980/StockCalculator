package ui;

import model.Stock;
import model.StockList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//inspired by part of https://www.youtube.com/watch?v=Kmgo00avvEw&t=1s
//Lets user enter stocks, save or load stocks, and get results of stocks.
public class EnterStock extends FrameLayout implements ActionListener {
    private String jsonStore;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextField stockNameField;
    private JTextField stockSharesField;
    private JTextField stockPurchasePriceField;
    private JTextField stockSalePriceField;
    private JTextField stockDividendField;
    private JTextField stockBuyCommissionField;
    private JTextField stockSellCommissionField;
    private JTextField stockCgtField;
    private JLabel stockNameLabel;
    private JLabel stockSharesLabel;
    private JLabel stockPurchasePriceLabel;
    private JLabel stockSalePriceLabel;
    private JLabel stockDividendLabel;
    private JLabel stockBuyCommissionLabel;
    private JLabel stockSellCommissionLabel;
    private JLabel stockCgtLabel;
    private JButton buttonAddStock;
    private JButton buttonGetResult;
    private JButton buttonSaveStock;
    private JButton buttonLoadStock;
    private StockList stockList;
    private String dataName;

    public EnterStock(String dataName) {
        super();
        this.dataName = dataName;
        this.setLayout(new GridLayout(0, 1));
        stockList = new StockList();
        jsonStore = "./data/" + dataName + ".json";
        jsonWriter = new JsonWriter(jsonStore);
        jsonReader = new JsonReader(jsonStore);
        enterStock();
        buttons();
    }

    //MODIFIES:this
    //EFFECT: create new JLabel and JTextFields for each input needed and add it to the JFrame
    private void enterStock() {
        stockNameLabel = new JLabel("Enter name of stock:");
        stockNameField = newField();
        stockSharesLabel = new JLabel("Enter the number of shares owned of this stock: ");
        stockSharesField = newField();
        stockPurchasePriceLabel = new JLabel("Enter purchase price of one stock:");
        stockPurchasePriceField = newField();
        stockSalePriceLabel = new JLabel("Enter sale price of one stock:");
        stockSalePriceField = newField();
        stockDividendLabel = new JLabel("Enter total dividend received from all shares of stock held "
                + "(enter 0 if you do not wish to have dividend in calculations: ");
        stockDividendField = newField();
        stockBuyCommissionLabel = new JLabel("Enter the buy commission when you bought the stocks: ");
        stockBuyCommissionField = newField();
        stockSellCommissionLabel = new JLabel("Enter the sell commission when you are selling the stocks: ");
        stockSellCommissionField = newField();
        stockCgtLabel = new JLabel("Enter cgt (capital gain tax) when stock is"
                + "sold in decimals (eg.10% = 0.1): ");
        stockCgtField = newField();
        addLabelsAndFields();
    }

    //MODIFIES: this
    //EFFECT: add the labels and text fields to the JFrame
    private void addLabelsAndFields() {
        this.add(stockNameLabel);
        this.add(stockNameField);
        this.add(stockSharesLabel);
        this.add(stockSharesField);
        this.add(stockPurchasePriceLabel);
        this.add(stockPurchasePriceField);
        this.add(stockSalePriceLabel);
        this.add(stockSalePriceField);
        this.add(stockDividendLabel);
        this.add(stockDividendField);
        this.add(stockBuyCommissionLabel);
        this.add(stockBuyCommissionField);
        this.add(stockSellCommissionLabel);
        this.add(stockSellCommissionField);
        this.add(stockCgtLabel);
        this.add(stockCgtField);
    }

    //EFFECT: return a JTextField with modified dimensions
    private JTextField newField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(250, 40));
        return field;


    }

    //MODIFIES: this
    //EFFECT: create buttons with action listeners and add the buttons to the JFrame
    private void buttons() {
        buttonAddStock = new JButton("Add Stock");
        buttonAddStock.addActionListener(this);
        buttonGetResult = new JButton("Get Result");
        buttonGetResult.addActionListener(this);
        buttonSaveStock = new JButton("Save Stock List");
        buttonSaveStock.addActionListener(this);
        buttonLoadStock = new JButton("Load Stock List");
        buttonLoadStock.addActionListener(this);
        this.add(buttonAddStock);
        this.add(buttonGetResult);
        this.add(buttonSaveStock);
        this.add(buttonLoadStock);
    }

    //MODIFIES: stockList, JSON file
    //EFFECT: perform certain actions according to button clicked
    //  if button clicked is buttonAddStock create a new stock from the input received from the
    // text box then add it to stockList
    //  else if button clicked is buttonGetResult create a new SelectResult object
    //  else if button clicked is buttonSaveStock save the current stockList in JSON file
    //  else if button clicked is buttonLoadStock load the current stockList in JSON file
    //clear all JText box no matter which button is clicked

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAddStock) {
            String name = stockNameField.getText();
            double share = Double.parseDouble(stockSharesField.getText());
            double purchasePrice = Double.parseDouble(stockPurchasePriceField.getText());
            double salePrice = Double.parseDouble(stockSalePriceField.getText());
            double dividend = Double.parseDouble(stockDividendField.getText());
            double buyCommission = Double.parseDouble(stockBuyCommissionField.getText());
            double saleCommission = Double.parseDouble(stockSellCommissionField.getText());
            double cgt = Double.parseDouble(stockCgtField.getText());
            Stock stock = new Stock(name, share, purchasePrice, salePrice, dividend, buyCommission, saleCommission,
                    +cgt);
            stockList.addStock(stock);
        } else if (e.getSource() == buttonGetResult) {
            new SelectResult(stockList);
        } else if (e.getSource() == buttonSaveStock) {
            this.saveStockList();
        } else if (e.getSource() == buttonLoadStock) {
            this.loadStockList();
        }
        clearJText();
    }

    //MODIFIES: JSON file
    //EFFECT: save stockList in file
    private void saveStockList() {
        try {
            jsonWriter.open();
            jsonWriter.write(stockList);
            jsonWriter.close();
            System.out.println("Saved " + stockList.nameOfStocksBought() + " to " + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        }
    }

    // MODIFIES: this
    // EFFECTS: load stockList from file
    private void loadStockList() {
        try {
            stockList = jsonReader.read();
            System.out.println("Loaded " + stockList.nameOfStocksBought() + " from " + jsonStore);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonStore);
        }
    }

    //EFFECT: Clear all JText Boxes
    private void clearJText() {
        stockNameField.setText("");
        stockSharesField.setText("");
        stockPurchasePriceField.setText("");
        stockSalePriceField.setText("");
        stockDividendField.setText("");
        stockBuyCommissionField.setText("");
        stockSellCommissionField.setText("");
        stockCgtField.setText("");
    }
}

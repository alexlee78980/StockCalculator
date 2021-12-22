package ui;

import model.StockList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//inspired by part of https://www.youtube.com/watch?v=Kmgo00avvEw&t=1s
//user select result output they want
public class SelectResult extends FrameLayout implements ActionListener {
    private JButton buttonName;
    private JButton buttonTotalProfit;
    private JButton buttonEachProfit;
    private JButton buttonIdentify;
    private JButton buttonBreakEven;
    private JButton buttonAllResult;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private StockList stockList;

    public SelectResult(StockList stocklist) {
        super();
        this.setLayout(new GridLayout(0, 1));
        this.stockList = stocklist;
        chooseResult();
    }

    //MODIFIES: this
    //EFFECT: make buttons for each result type and add on click listeners to them and display them on JFrame
    private void chooseResult() {
        buttonName = new JButton("get name of all stocks entered");
        buttonName.addActionListener(this);
        buttonTotalProfit = new JButton("get total amount profit or loss from all stocks entered");
        buttonTotalProfit.addActionListener(this);
        buttonEachProfit = new JButton("get amount of profit or loss for each stock entered");
        buttonEachProfit.addActionListener(this);
        buttonIdentify = new JButton("identify if profit, loss, or break even for each stock entered");
        buttonIdentify.addActionListener(this);
        buttonBreakEven = new JButton("get break even sale price for each stock entered");
        buttonBreakEven.addActionListener(this);
        buttonAllResult = new JButton("get all the results stated above");
        buttonAllResult.addActionListener(this);
        this.add(buttonName);
        this.add(buttonTotalProfit);
        this.add(buttonEachProfit);
        this.add(buttonIdentify);
        this.add(buttonBreakEven);
        this.add(buttonAllResult);

    }

    //EFFECT: create new getResult object with parameter depending on the button clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonName) {
            //System.out.println(stockList.nameOfStocksBought());
            new GetResult("a", stockList);
        } else if (e.getSource() == buttonTotalProfit) {
            //System.out.println(stockList.totalProfit());
            new GetResult("b", stockList);
        } else if (e.getSource() == buttonEachProfit) {
            //System.out.println(stockList.profitEachStock());
            new GetResult("c", stockList);
        } else if (e.getSource() == buttonIdentify) {
            //System.out.println(stockList.profitOrLossList());
            new GetResult("d", stockList);
        } else if (e.getSource() == buttonBreakEven) {
            //System.out.println(stockList.breakEvenSellPriceEachStock());
            new GetResult("e", stockList);
        } else if (e.getSource() == buttonAllResult) {
//            System.out.println("name : " + stockList.nameOfStocksBought());
//            System.out.println("total profit : " + stockList.totalProfit());
//            System.out.println("profit each : " + stockList.profitEachStock());
//            System.out.println("identify profit/loss/break even: " + stockList.profitOrLossList());
//            System.out.println("break even sale prices : " + stockList.breakEvenSellPriceEachStock());
            GetResult getResult = new GetResult("f", stockList);
        }


    }
}

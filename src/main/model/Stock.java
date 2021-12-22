package model;

import org.json.JSONObject;
import persistence.Writable;

import java.text.DecimalFormat;

//a stock transaction having name, shares, purchase price, sale price, dividend, buy commission,
// sell commission ,and cgt.
// code json inspired from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Stock implements Writable {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    private String name; //name of the stock that is being entered
    private double shares; //the number of shares that was bought/sold for this stock
    private double purchasePrice; //the price in dollars of one of this stock when bought
    private double salePrice;  // the price in dollars of one of this stock when sold
    private double dividend; // the dividend received when the stock was held
    private double buyCommission; // the amount in dollars that was paid as commission when buying the stock
    private double sellCommission; // the amount in dollars that was paid as commission when selling the stock
    private double cgt; // the capital gain rate (in decimals (eg. 10% = 0.1)) when the stock was sold.


    //REQUIRE: shares > 0 ; purchasePrice >= 0; sellPrice >= 0; dividend >= 0; buyCommission >= 0;
    //         sellCommission >= 0; 0 <= cgt <= 1.
    //MODIFIES: this
    //EFFECT:  name of stock is set to name; number of shares is set to shares;
    //         The price of 1 share when bought is set to purchasePrice;
    //         The price of 1 share when sold is set to sellPrice; The dividend received of total shares
    //         while the stock was kept is set to dividend; the commission paid when buying the stocks is set
    //         to buyCommission; the commission paid when selling the stocks is set to sellCommission;
    //         the capital gains tax when stock is sold is set to ctg
    public Stock(String name, double shares, double purchasePrice, double salePrice, double dividend,
                 double buyCommission, double sellCommission, double cgt) {
        this.name = name;
        this.shares = shares;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.dividend = dividend;
        this.buyCommission = buyCommission;
        this.sellCommission = sellCommission;
        this.cgt = cgt;
    }

    //EFFECT: return the earning or loss of the stock
    public double stockEarning() {
        double profitBeforeCgt = shares * salePrice - shares * purchasePrice - sellCommission - buyCommission
                + dividend;
        if (profitBeforeCgt > 0) {
            return Double.parseDouble(DECIMAL_FORMAT.format(profitBeforeCgt * (1 - cgt)));
        } else {
            return Double.parseDouble(DECIMAL_FORMAT.format(profitBeforeCgt));
        }
    }

    //EFFECT: return the sell price of each share of stock to break even (profit of 0)
    public double breakEvenSellPrice() {
        double price = (shares * purchasePrice + sellCommission + buyCommission - dividend) / shares;
        return Double.parseDouble(DECIMAL_FORMAT.format(price));
    }















    //EFFECTS: return if the stock sold at sellPrice is profit, loss or break even
    public String profitOrLoss() {
        if (stockEarning() > 0) {
            return "Profit";
        } else if (stockEarning() < 0) {
            return "Loss";
        } else {
            return "Break even";
        }
    }

    public String getName() {
        return name;
    }

    public double getShares() {
        return shares;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public double getDividend() {
        return dividend;
    }

    public double getBuyCommission() {
        return buyCommission;
    }

    public double getSellCommission() {
        return sellCommission;
    }

    public double getCgt() {
        return cgt;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("shares", shares);
        json.put("purchase price", purchasePrice);
        json.put("sale price", salePrice);
        json.put("dividend", dividend);
        json.put("buy commission", buyCommission);
        json.put("sale commission", sellCommission);
        json.put("cgt", cgt);
        return json;
    }

}

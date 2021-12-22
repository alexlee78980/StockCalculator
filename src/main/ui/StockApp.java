//package ui;
//
//import model.Stock;
//import model.StockList;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Scanner;
////Stock calculator application lets user enter values and give calculation results
////inspired by the class TellerApp in the project TellerApp
//// code json inspired from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//
//public class StockApp {
//    private static final String JSON_STORE = "./data/StockList.json";
//    private StockList stockList;
//    private boolean runProgram = true;
//    private Scanner input;
//    private boolean keepRun = true;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    // EFFECTS: runs the stock application
//    public StockApp() {
//        init();
//        runStockApp();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: processes user input for next action
//    private void runStockApp() {
//        String command1 = null;
//        while (keepRun) {
//            displayMenu();
//            command1 = input.next();
//            command1 = command1.toLowerCase();
//            if (command1.equals("q")) {
//                keepRun = false;
//                runProgram = false;
//            } else if (command1.equals("g")) {
//                getResultOfEnteredStock();
//
//            } else if (command1.equals("e")) {
//                addNewStock();
//            } else if (command1.equals("s")) {
//                saveStockList();
//            } else if (command1.equals("l")) {
//                loadStockList();
//            } else {
//                System.out.println("Selection not valid...");
//            }
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECT: initialize the StockList and Scanner
//    private void init() {
//        input = new Scanner(System.in);
//        stockList = new StockList();
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//    }
//
//    // EFFECTS: processes user command to give result that was stated
//    private void getResultOfEnteredStock() {
//        String command2 = null;
//        while (runProgram) {
//            displayResultOption();
//            command2 = input.next();
//            command2 = command2.toLowerCase();
//            if (command2.equals("q")) {
//                runProgram = false;
//                keepRun = false;
//            } else {
//                displayResult(command2);
//                runStockApp();
//            }
//        }
//    }
//
//    //MODIFIES: this
//    // EFFECTS: get user inputs to make new stock and add it to stock list
//    private void addNewStock() {
//        System.out.println("Enter name of stock:");
//        String name = input.next();
//        System.out.println("Enter the number of shares owned of this stock: ");
//        double shares = input.nextDouble();
//        System.out.println("Enter purchase price of one stock:");
//        double purchasePrice = input.nextDouble();
//        System.out.println("Enter sell price of one stock:");
//        double sellPrice = input.nextDouble();
//        System.out.println("Enter total dividend received from all shares of stock held "
//                + "(enter 0 if you do not wish to have dividend in calculations: ");
//        double dividend = input.nextDouble();
//        System.out.println("Enter the buy commission when you bought the stocks: ");
//        double buyCommission = input.nextDouble();
//        System.out.println("Enter the sell commission when you are selling the stocks: ");
//        double sellCommission = input.nextDouble();
//        System.out.println("Enter cgt (capital gain tax) when stock is sold in decimals (eg.10% = 0.1): ");
//        double cgt = input.nextDouble();
//        Stock stock = new Stock(name, shares, purchasePrice, sellPrice, dividend, buyCommission, sellCommission, cgt);
//        stockList.addStock(stock);
//    }
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\te -> enter new stock");
//        System.out.println("\tg -> get results");
//        System.out.println("\ts -> save stock list to file");
//        System.out.println("\tl -> load stock list from file");
//        System.out.println("\tq -> quit program");
//    }
//
//    // EFFECTS: displays result options to user
//    private void displayResultOption() {
//        System.out.println("\nSelect results from:");
//        System.out.println("\tn -> get name of all stocks entered");
//        System.out.println("\tt -> get total amount profit or loss from all stocks entered");
//        System.out.println("\te -> get amount of profit or loss for each stock entered");
//        System.out.println("\tp -> identify if profit, loss, or break even for each stock entered");
//        System.out.println("\tb -> get break even sale price for each stock entered");
//        System.out.println("\ta -> get all the results stated above");
//        System.out.println("\tq -> quit program");
//    }
//
//    //EFFECT: processes user command and display the result selected
//    private void displayResult(String command) {
//        if (command.equals("n")) {
//            System.out.println(stockList.nameOfStocksBought());
//        } else if (command.equals("t")) {
//            System.out.println(stockList.totalProfit());
//        } else if (command.equals("e")) {
//            System.out.println(stockList.profitEachStock());
//        } else if (command.equals("p")) {
//            System.out.println(stockList.profitOrLossList());
//        } else if (command.equals("b")) {
//            System.out.println(stockList.breakEvenSellPriceEachStock());
//        } else if (command.equals("a")) {
//            System.out.println("name : " + stockList.nameOfStocksBought());
//            System.out.println("total profit : " + stockList.totalProfit());
//            System.out.println("profit each : " + stockList.profitEachStock());
//            System.out.println("identify profit/loss/break even: " + stockList.profitOrLossList());
//            System.out.println("break even sale prices : " + stockList.breakEvenSellPriceEachStock());
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//
//    private void saveStockList() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(stockList);
//            jsonWriter.close();
//            System.out.println("Saved " + stockList.nameOfStocksBought() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads stock List from file
//    private void loadStockList() {
//        try {
//            stockList = jsonReader.read();
//            System.out.println("Loaded " + stockList.nameOfStocksBought() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//}
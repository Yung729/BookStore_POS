package Assignment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
/**
 *
 * @author user
 */

public class Transaction {

    private String transactionID;
    private String paymentMethod;
    private String orderID;
    private String datetime;
    private Order order;

    public Transaction(){
        this.transactionID = initNewTransaction();
    }

    public Transaction(String transactionID) {
        this.transactionID = transactionID;
        initTransaction();
    }

    public Transaction(String method, Order order){
        this.transactionID = initNewTransaction();
        this.paymentMethod = method;
        this.orderID = order.getOrderID();
        this.datetime = Tools.getCurrentDatetime();
        this.order = order;
    }

    public Transaction(String transactionID, String method, String orderID, String datetime){
        this.transactionID = transactionID;
        this.paymentMethod = method;
        this.orderID = orderID;
        this.datetime = datetime;
        this.order = Tools.getOrderObjByID(orderID);
    }

    private String initNewTransaction(){
        int newID = FileHandler.getLastRowID(FileHandler.TRANSACTION_DB);
        newID++;
        return Tools.getIDStrFormat('T', newID);
    }

    private void initTransaction() {
        ArrayList<String> transactionList = FileHandler.getRowByMainID(FileHandler.TRANSACTION_DB, transactionID);
        if (transactionList.isEmpty()) {
            System.out.println("No transaction found!");
            return;
        }
        this.paymentMethod = transactionList.get(1);
        this.orderID = transactionList.get(2);
        this.datetime = transactionList.get(3);
        this.order = Tools.getOrderObjByID(orderID);
    }

    public void setTransactionID(String transactionID){
        this.transactionID = transactionID;
    }

    public void setMethod(String method){
        this.paymentMethod = method;
    }

    public void setOrderID(String orderID){
        this.orderID = orderID;
    }

    public void setDatetime(String datetime){
        this.datetime = datetime;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public String getTransactionID(){
        return transactionID;
    }

    public String getMethod(){
        return paymentMethod;
    }

    public String getOrderID(){
        return orderID;
    }

    public String getDatetime(){
        return datetime;
    }

    public Order getOrder(){
        return order;
    }

    public void printTransaction(){
        System.out.println("Order ID: \t\t" + orderID);
        System.out.println("Transaction ID: " + transactionID  + "\t\t\tPayment Method: " + paymentMethod);
        System.out.println("Datetime: \t\t" + datetime + "\n");
        System.out.println("Order Details:");
        order.printOrderDetails();
    }

    public void storeTransaction(){
        String[] transactionData = {transactionID, paymentMethod, orderID, datetime};
        FileHandler.writeArrToFile(FileHandler.TRANSACTION_DB, transactionData);
        order.saveOrderToDB();
    }

}

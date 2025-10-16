package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ledger {

    public static void showAll(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transaction found");
            return;
        }
        //organization
        System.out.printf("%-20s | %-20s | %-20s | %-10f\n", "Date & time", "Description", "Vendor", "Amount");



        for (Transaction transaction : transactions) {
            //combine date and time into a single string display
            String dateTime = transaction.getDate() + " " + transaction.getTime();
            //format and print transaction details in aligned columns
            System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
        }

    }
    //displays only deposit transactions (positive amount)
    public static void showAllDeposit() {
        List<Transaction> transactions = FileManager.getTransaction();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }


        }


    }
    //displays only payments transactions (negative amounts)
    public static void showAllPayments() {
        List<Transaction> transactions = FileManager.getTransaction();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }


        }

    }
}

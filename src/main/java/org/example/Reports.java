package org.example;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.scanner;

public class Reports {
    //store current date for all report
    static LocalDate today = LocalDate.now();
    //display all transactions from the beginning of the current month to today
    public static void monthToDate() {
        List<Transaction> transactions = FileManager.getTransaction();
        //Getting the start & end of the month
        LocalDate mtdStart = today.withDayOfMonth(1);
        LocalDate mtdEnd = today;

        for (Transaction transaction : transactions) {
            //check if transaction falls within the month-to-date
            if (transaction.getDate().isBefore(mtdEnd) && transaction.getDate().isAfter(mtdStart)) {
                //format and display transaction details
                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

            }

        }

    }
     //Displays all transaction from previous month
    public static void previousMonth() {
        //gets the previous month using YearMonth
        YearMonth pm = YearMonth.from(today).minusMonths(1);
        LocalDate pmStart = pm.atDay(1);
        LocalDate pmEnd = pm.atEndOfMonth();

        List<Transaction> transactions = FileManager.getTransaction();

        for (Transaction transaction : transactions) {
            //check if transaction falls within the previous month
            if (transaction.getDate().isBefore(pmEnd) && transaction.getDate().isAfter(pmStart)) {
                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }

        }


    }
    //
    public static void yearToDate() {
        List<Transaction> transactions = FileManager.getTransaction();
        LocalDate ytdStart = today.withDayOfYear(1);
        //Getting the start of the year
        LocalDate ytdEnd = today;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().isBefore(ytdEnd) && transaction.getDate().isAfter(ytdStart)) {
                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }

        }
    }
    //Displays all transaction from previous years
    public static void previousYear() {
        List<Transaction> transactions = FileManager.getTransaction();
        //calculates previous year dates
        int py = today.getYear() - 1;
        LocalDate pyStart = LocalDate.of(py, 1, 1); //From the beginning of the year to the last
        LocalDate pyEnd = LocalDate.of(py, 12, 31);

        for (Transaction transaction : transactions) {
            //check if transaction falls within the previous year
            if (transaction.getDate().isBefore(pyEnd) && transaction.getDate().isAfter(pyStart)) {
                //format and displays transaction details
                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }

    }

    //searches for and displays transactions matching a vendor keyword
    public static List<Transaction> searchVendor(String keyword) {
        List<Transaction> transactions = FileManager.getTransaction();
        List<Transaction> vendorTransaction = new ArrayList<>();

        for (Transaction transaction : transactions) {
            //Check if vendor naime contains the keyword
            if (transaction.getVendor().toLowerCase().contains(keyword.toLowerCase())) {
                //adds matching transaction details
                vendorTransaction.add(transaction);
                //Formats and displays transaction details
                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }
        return vendorTransaction;

    }

    public static void reportsMenu() {
        System.out.println("*************");
        System.out.println("  Reports Menu  ");
        System.out.println("""
                ****************
                1) Month to date
                2) previous month
                3) Year to date
                4) Previous year
                5) Search by vendor
                6) Back
                ****************
                """);
        List<Transaction> vendorTransaction = new ArrayList<>();
        //Gets user menu choice
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                monthToDate();
                break;
            case 2:
                previousMonth();
                break;
            case 3:
                yearToDate();
                break;
            case 4:
                previousYear();
                break;
            case 5:
                //prompts for vendor name and search
                System.out.println("Enter Vendor:");
                String vendor = scanner.nextLine();
                vendorTransaction = searchVendor(vendor);
                //Display all matching transactions
                for(Transaction transaction : vendorTransaction){
                    String dateTime = transaction.getDate() + " " + transaction.getTime();
                    System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

                }

        }
    }

}




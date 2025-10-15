package org.example;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.example.Main.scanner;

public class Reports {
    static LocalDate today = LocalDate.now();

    public static void monthToDate() {
        List<Transaction> transactions = FileManager.getTransaction();
        //Getting the start & end of the month
        LocalDate mtdStart = today.withDayOfMonth(1);
        LocalDate mtdEnd = today;

        for (Transaction transaction : transactions) {

            if (transaction.getDate().isBefore(mtdEnd) && transaction.getDate().isAfter(mtdStart)) {

                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

            }

        }

    }

    public static void previousMonth() {
        YearMonth pm = YearMonth.from(today).minusMonths(1);
        LocalDate pmStart = pm.atDay(1);
        LocalDate pmEnd = pm.atEndOfMonth();
        //Getting the month & day
        List<Transaction> transactions = FileManager.getTransaction();

        for (Transaction transaction : transactions) {
            if (transaction.getDate().isBefore(pmEnd) && transaction.getDate().isAfter(pmStart)) {
                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }

        }


    }

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

    public static void previousYear() {
        List<Transaction> transactions = FileManager.getTransaction();
        int py = today.getYear() - 1;
        LocalDate pyStart = LocalDate.of(py, 1, 1); //From the beginning of the year to the last
        LocalDate pyEnd = LocalDate.of(py, 12, 31);

        for (Transaction transaction : transactions) {
            if (transaction.getDate().isBefore(pyEnd) && transaction.getDate().isAfter(pyStart)) {
                String dateTime = transaction.getDate() + " " + transaction.getTime();
                System.out.printf("%-20s | %-20s | %-20s | %-10.2f\n", dateTime, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }

    }

    public static List<Transaction> searchVendor(String keyword) {
        List<Transaction> transactions = FileManager.getTransaction();
        List<Transaction> vendorTransaction = new ArrayList<>();
        //
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().toLowerCase().contains(keyword.toLowerCase())) {
                vendorTransaction.add(transaction);
            }
        }
        return vendorTransaction;

    }

    public static void reportsMenu() {
        System.out.println("""
                1) Month to date
                2) previous month
                3) Year to date
                4) Previous year
                5) Search by vendor
                6) Back
                """);
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
                System.out.println("Enter Vendor:");
                String vendor = scanner.nextLine();
                searchVendor(vendor);

        }
    }

}




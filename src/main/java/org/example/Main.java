package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;




//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scanner = new Scanner(System.in);
    //Added static scanner up here, so all my main can have access to it
    public static void main(String[] args) {
       //Adds existing transactions from file
        List<Transaction> transaction = FileManager.getTransaction();
        boolean running = true;
        //main loop continues until user chooses to exite
        while (running) {
            System.out.println("*********************");
            System.out.println("  Home Menu  ");
            System.out.println("""
                    ***********************
                    1)show all transactions
                    2)Add Deposit
                    3)Make Payment
                    4)Ledger
                    5(Exit
                    ***********************
                    """);
            int choice = Integer.parseInt(scanner.nextLine());
            //maybe add try catch

            //displaying my main menu
            switch (choice) {
                case 1:
                    showAllTransaction();
                    break;
                case 2:
                    addDeposit();
                    break;
                case 3:
                     makePayment();
                    break;
                case 4:
                   ledgerMenu();
                    break;
                case 5:
                    System.out.println("Exit");
                    System.exit(0);
                    break;
                default:
                    System.out.println("INVALID CHOICE");
                    break;


            }
        }
        System.out.println("Thank you, have a nice day.");
        scanner.close();


    }

    public static void showAllTransaction() {
        //Gets all transaction from storage
        List<Transaction> transactions = FileManager.getTransaction();
        //Loop through and print each transaction with formatted columns
        for(Transaction transaction: transactions){
            System.out.printf("%-15tD │ %-15tr │ %-30s │ %-30s │ %10.2f$\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
           //
        }


    }
    //Prompts user for deposit information and save to file
    public static void addDeposit() {
        //Gets current date and time for transaction
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        //collects deposit details from user
        System.out.println("Enter Description:");
        String description = scanner.nextLine();
        System.out.println("Enter vendor:");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount:");
        double amount = Double.parseDouble(scanner.nextLine());

        //Created new transaction object and write to file
        Transaction newTransaction = new Transaction(date,time,description,vendor,amount);
        FileManager.fileWriter(newTransaction);



    }
    //prompts user for payment information and saves it as a negative transaction
    public static void makePayment(){

        LocalDate date = LocalDate.now();   //calling date & time
        LocalTime time = LocalTime.now();

        System.out.println("Enter Description:");
        String description = scanner.nextLine();
        System.out.println("Enter vendor:");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount:");
        //converting amount to negative, because it's removing it from the bank.
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -amount;
        //Created new transaction object and write to file.
        Transaction newTransaction = new Transaction(date,time,description,vendor,amount);
        FileManager.fileWriter(newTransaction);

    }
    public static void ledgerMenu(){
        System.out.println("**************");
        System.out.println("  Ledger Menu  ");
        System.out.println("""
                ***************
                1)show All
                2) Show Deposit
                3) ShowPayments
                4) Show Reports
                5)Back
                ***************
                """);
        //gets user's ledger menu selection
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice){
            case 1:
                showAllTransaction();
                break;
            case 2:
                Ledger.showAllDeposit();
                break;
            case 3:
                Ledger.showAllPayments();
                break;
            case 4:
                Reports.reportsMenu();
                break;
            case 5:
                //return to home menu
                System.out.println("Back");
                break;

        }

    }

}







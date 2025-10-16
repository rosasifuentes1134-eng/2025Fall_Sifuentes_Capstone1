package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class FileManager {
    // Reads all transactions from the CSV file and returns them as a list
    public static List<Transaction> getTransaction() {
        // Initialize empty list to store transactions
        List<Transaction> transaction = new ArrayList<>();
        try {
           // Open the transactions CSV file for reading
            FileReader fr = new FileReader("src/main/resources/transactions.csv");
            BufferedReader reader = new BufferedReader(fr);
            String line;
            // Read file line by line until end of file
            while ((line = reader.readLine()) != null) {
                // Split each line by pipe delimiter to get individual fields
                String[] transactionData = line.split("\\|");
                // Create new Transaction object and populate with parsed data
                Transaction newTransaction = new Transaction();
                newTransaction.setDate(LocalDate.parse(transactionData[0]));
                newTransaction.setTime(LocalTime.parse(transactionData[1]));
                newTransaction.setDescription(transactionData[2]);
                newTransaction.setVendor(transactionData[3]);
                newTransaction.setAmount(Double.parseDouble(transactionData[4]));
                // Add the newly created transaction to the list
                transaction.add(newTransaction);

            }

            reader.close();

        } catch (FileNotFoundException ex) {
            // Handle case where the CSV file doesn't exist
            System.out.println("File not found");
        } catch (IOException ex) {
            // Handle any issues reading the file
            System.out.println("File not read properly");

        }

        // Return the list of transactions (will be empty if file not found or error occurred)
        return transaction;

    }

    //writes a new transaction to the CSV file
    public static void fileWriter(Transaction transaction){
        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Format and write transaction data
            // Use DateTimeFormatter to format time as HH:mm:ss ( 14:30:45)

            bufferedWriter.write("\n"+ transaction.getDate().toString()+"|"+ transaction.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString()+"|"+ transaction.getDescription()+"|"+ transaction.getVendor()+"|"+ transaction.getAmount());


            bufferedWriter.close();

        }
        catch (IOException ex){
            // Handle file writing errors
            System.out.println("Error with the file, try again.");

        } catch (Exception e) {
            // Catch any unexpected exceptions and rethrow as RuntimeException
            throw new RuntimeException(e);
        }

    }

}


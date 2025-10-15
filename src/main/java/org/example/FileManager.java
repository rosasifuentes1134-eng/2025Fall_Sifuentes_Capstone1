package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class FileManager {

    public static List<Transaction> getTransaction() {
        List<Transaction> transaction = new ArrayList<>();
        try {
            FileReader fr = new FileReader("src/main/resources/transactions.csv");
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) {
                String[] transactionData = line.split("\\|");
                Transaction newTransaction = new Transaction();
                newTransaction.setDate(LocalDate.parse(transactionData[0]));
                newTransaction.setTime(LocalTime.parse(transactionData[1]));
                newTransaction.setDescription(transactionData[2]);
                newTransaction.setVendor(transactionData[3]);
                newTransaction.setAmount(Double.parseDouble(transactionData[4]));

                transaction.add(newTransaction);

            }

            reader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("File not read properly");

        }


        return transaction;

    }


    public static void fileWriter(Transaction transaction){
        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //!use formatter to be able to print the time without printing too many seconds
            bufferedWriter.write("\n"+ transaction.getDate().toString()+"|"+ transaction.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString()+"|"+ transaction.getDescription()+"|"+ transaction.getVendor()+"|"+ transaction.getAmount());


            bufferedWriter.close();

        }
        catch (IOException ex){
            System.out.println("Error with the file, try again.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}


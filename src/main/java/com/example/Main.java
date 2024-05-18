package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "src\\main\\resources\\supermarket_sales.csv";

        try {
            System.out.println("Loading CSV file...");
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            System.out.println("\nCSV Data:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("\nSummary of CSV file:");
            int rowCount = 0;
            int columnCount = 0;
            reader.close();
            reader = new BufferedReader(new FileReader(csvFilePath));

            if ((line = reader.readLine()) != null) {
                String[] header = line.split(",");
                columnCount = header.length;
                rowCount++;
            }

            while ((line = reader.readLine()) != null) {
                rowCount++;
            }

            System.out.println("Number of rows: " + rowCount);
            System.out.println("Number of columns: " + columnCount);

            reader.close();

            System.out.println("\nCleaning data...");
            reader = new BufferedReader(new FileReader(csvFilePath));
            DataCleaner.CleanedData cleanedData = DataCleaner.cleanData(reader);
            reader.close();

            System.out.println("Number of invalid data entries: " + cleanedData.getInvalidCount());

            if (cleanedData.getInvalidCount() > 0) {
                System.out.println("Data cleaning completed. " + cleanedData.getInvalidCount() + " invalid entries found.");
            } else {
                System.out.println("Data cleaning completed. No invalid entries found.");
            }

            if (cleanedData.getInvalidCount() == 0) {
                if (cleanedData.hasNullValues()) {
                    System.out.println("Data contains null values. Visualization cannot be performed.");
                } else {
                    System.out.println("Data doesn't contain any null values");
                    System.out.println("\nVisualizing cleaned data by different features...");
                    // Visualize using the most effective chart type for each relationship
                    DataVisualizer.visualizeData(cleanedData.getData(), "Product", "Product Sales", "bar");
                    DataVisualizer.visualizeData(cleanedData.getData(), "Region", "Region Sales", "bar");
                    DataVisualizer.visualizeData(cleanedData.getData(), "Gender", "Gender Sales", "pie");
                    DataVisualizer.visualizeData(cleanedData.getData(), "Payment Method", "Payment Method Sales", "pie");
                    DataVisualizer.visualizeData(cleanedData.getData(), "Customer Type", "Customer Type Sales", "pie");
                    DataVisualizer.visualizeData(cleanedData.getData(), "Branch", "Branch Sales", "bar");
                    DataVisualizer.visualizeData(cleanedData.getData(), "Date", "Date Sales", "line");
                    DataVisualizer.visualizeData(cleanedData.getData(), "Time", "Time Sales", "line");
                    DataVisualizer.visualizeData(cleanedData.getData(), "Rating", "Rating Distribution", "pie");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

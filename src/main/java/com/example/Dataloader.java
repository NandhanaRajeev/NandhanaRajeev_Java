package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for loading CSV data from a file.
 */
public class Dataloader {
    /**
     * Loads a CSV file and returns CSVData containing its row and column counts.
     *
     * Parameters:
     * csvFilePath - The path to the CSV file to be loaded
     * Returns: An instance of CSVData containing row and column counts of the
     * loaded CSV file
     * Throws: IOException If an I/O error occurs while reading the CSV file
     */
    public static CSVData loadCSV(String csvFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        String line;
        int rowCount = 0;
        int columnCount = 0;

        // Read the header to determine the number of columns
        if ((line = reader.readLine()) != null) {
            String[] header = line.split(",");
            columnCount = header.length;
            rowCount++;
        }

        // Count the remaining rows
        while (reader.readLine() != null) {
            rowCount++;
        }

        reader.close();

        return new CSVData(rowCount, columnCount);
    }

    /**
     * Creates a BufferedReader for the given CSV file.
     *
     * Parameters:
     * csvFilePath - The path to the CSV file
     * Returns: A BufferedReader for the CSV file
     * Throws: IOException If an I/O error occurs while creating the BufferedReader
     */
    public static BufferedReader getCSVReader(String csvFilePath) throws IOException {
        return new BufferedReader(new FileReader(csvFilePath));
    }

    /**
     * Represents basic information about a CSV data file.
     */
    public static class CSVData {
        private final int rowCount;
        private final int columnCount;

        /**
         * Constructor to initialize CSVData object with the given row and column
         * counts.
         *
         * Parameters:
         * rowCount - The number of rows in the CSV file
         * columnCount - The number of columns in the CSV file
         */
        public CSVData(int rowCount, int columnCount) {
            this.rowCount = rowCount;
            this.columnCount = columnCount;
        }

        /**
         * Getter for the number of rows in the CSV file.
         *
         * Returns: The number of rows in the CSV file
         */
        public int getRowCount() {
            return rowCount;
        }

        /**
         * Getter for the number of columns in the CSV file.
         *
         * Returns: The number of columns in the CSV file
         */
        public int getColumnCount() {
            return columnCount;
        }
    }
}

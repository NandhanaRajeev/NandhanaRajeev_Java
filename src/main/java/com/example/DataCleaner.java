package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for cleaning data read from a BufferedReader.
 */
public class DataCleaner {

    /**
     * Cleans the data read from the provided BufferedReader.
     *
     * Parameters:
     * reader - The BufferedReader containing the data to be cleaned
     * Returns: An instance of CleanedData containing cleaned data, invalid count, and null value status
     * Throws: IOException If an I/O error occurs while reading the data
     */
    public static CleanedData cleanData(BufferedReader reader) throws IOException {
        List<String[]> cleanedData = new ArrayList<>();
        int invalidCount = 0; // Counter for invalid data
        boolean hasNullValues = false; // Flag to track null values
        String line;

        // Skip header
        reader.readLine();

        // Process each line of data
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (isValid(data)) {
                cleanedData.add(data);
            } else {
                System.out.println("Invalid data: " + line);
                invalidCount++; // Increment invalid count
            }

            if (hasNullValues(data)) {
                hasNullValues = true; // Set flag if null values detected
            }
        }

        return new CleanedData(cleanedData, invalidCount, hasNullValues);
    }

    /**
     * Checks if the data is valid according to some criteria.
     *
     * Parameters:
     * data - The data to be validated
     * Returns: True if the data is valid, false otherwise
     */
    private static boolean isValid(String[] data) {
        // Your data validation logic here
        return true; // Dummy return for example
    }

    /**
     * Checks if the data contains any null values.
     *
     * Parameters:
     * data - The data to be checked
     * Returns: True if any null values are found, false otherwise
     */
    private static boolean hasNullValues(String[] data) {
        // Check if any element in the data array is null
        for (String datum : data) {
            if (datum == null || datum.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inner class representing cleaned data along with metadata.
     */
    public static class CleanedData {
        private final List<String[]> data;
        private final int invalidCount;
        private final boolean hasNullValues;

        /**
         * Constructor for CleanedData.
         *
         * Parameters:
         * data - The cleaned data
         * invalidCount - The count of invalid data entries
         * hasNullValues - Whether the data contains any null values
         */
        public CleanedData(List<String[]> data, int invalidCount, boolean hasNullValues) {
            this.data = data;
            this.invalidCount = invalidCount;
            this.hasNullValues = hasNullValues;
        }

        /**
         * Getter for the cleaned data.
         *
         * Returns: The cleaned data
         */
        public List<String[]> getData() {
            return data;
        }

        /**
         * Getter for the count of invalid data entries.
         *
         * Returns: The count of invalid data entries
         */
        public int getInvalidCount() {
            return invalidCount;
        }

        /**
         * Checks if the data contains any null values.
         *
         * Returns: True if the data contains null values, false otherwise
         */
        public boolean hasNullValues() {
            return hasNullValues;
        }
    }
}

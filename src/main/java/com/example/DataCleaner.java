package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataCleaner {
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

    private static boolean isValid(String[] data) {
        // Your data validation logic here
        return true; // Dummy return for example
    }

    private static boolean hasNullValues(String[] data) {
        // Check if any element in the data array is null
        for (String datum : data) {
            if (datum == null || datum.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static class CleanedData {
        private final List<String[]> data;
        private final int invalidCount;
        private final boolean hasNullValues;

        public CleanedData(List<String[]> data, int invalidCount, boolean hasNullValues) {
            this.data = data;
            this.invalidCount = invalidCount;
            this.hasNullValues = hasNullValues;
        }

        public List<String[]> getData() {
            return data;
        }

        public int getInvalidCount() {
            return invalidCount;
        }

        public boolean hasNullValues() {
            return hasNullValues;
        }
    }
}

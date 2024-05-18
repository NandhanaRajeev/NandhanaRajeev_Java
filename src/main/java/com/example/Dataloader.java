package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dataloader {
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

    public static BufferedReader getCSVReader(String csvFilePath) throws IOException {
        return new BufferedReader(new FileReader(csvFilePath));
    }

    public static class CSVData {
        private final int rowCount;
        private final int columnCount;

        public CSVData(int rowCount, int columnCount) {
            this.rowCount = rowCount;
            this.columnCount = columnCount;
        }

        public int getRowCount() {
            return rowCount;
        }

        public int getColumnCount() {
            return columnCount;
        }
    }
}

package com.example;

/**
 * Represents basic information about a CSV (Comma-Separated Values) data file.
 */
public class CSVData {
    // Private instance variables to store the number of rows and columns in the CSV file
    private final int rowCount;
    private final int columnCount;

    /**
     * Constructor to initialize CSVData object with the given row and column counts.
     *
     * rowCount  -  The number of rows in the CSV file
     * columnCount - The number of columns in the CSV file
     */
    public CSVData(int rowCount, int columnCount) {
        // Assign the provided values to the instance variables
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    /**
     * Getter method to retrieve the number of rows in the CSV file.
     *
     *  return the number of rows in the CSV file
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Getter method to retrieve the number of columns in the CSV file.
     *
     * return the number of columns in the CSV file
     */
    public int getColumnCount() {
        return columnCount;
    }
}

package com.example;

public class CSVData {
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

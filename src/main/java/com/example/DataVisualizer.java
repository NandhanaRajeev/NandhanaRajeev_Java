package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DataVisualizer {

    public static void visualizeData(List<String[]> cleanedData, String feature, String frameTitle, String chartType) {
        SwingUtilities.invokeLater(() -> {
            JFreeChart chart = null;
            switch (chartType) {
                case "bar":
                    chart = createBarChart(createDataset(cleanedData, feature), feature);
                    break;
                case "line":
                    chart = createLineChart(createDataset(cleanedData, feature), feature);
                    break;
                case "pie":
                    chart = createPieChart(createPieDataset(cleanedData, feature), feature);
                    break;
                default:
                    System.err.println("Invalid chart type.");
                    break;
            }

            if (chart != null) {
                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new Dimension(800, 600));

                JFrame frame = new JFrame(frameTitle);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(chartPanel);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    private static DefaultCategoryDataset createDataset(List<String[]> cleanedData, String feature) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int featureIndex = getFeatureIndex(feature);

        if (featureIndex != -1) {
            int salesIndex = 15;
            for (String[] data : cleanedData) {
                String featureValue = data[featureIndex];
                double sales = Double.parseDouble(data[salesIndex]);
                dataset.addValue(sales, "Sales", featureValue);
            }
        }

        return dataset;
    }

    private static DefaultPieDataset createPieDataset(List<String[]> cleanedData, String feature) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int featureIndex = getFeatureIndex(feature);

        if (featureIndex != -1) {
            int salesIndex = 15;
            for (String[] data : cleanedData) {
                String featureValue = data[featureIndex];
                double sales = Double.parseDouble(data[salesIndex]);
                dataset.setValue(featureValue, sales);
            }
        }

        return dataset;
    }

    private static int getFeatureIndex(String feature) {
        switch (feature) {
            case "Product":
                return 5;
            case "Region":
                return 2;
            case "Gender":
                return 4;
            case "Payment Method":
                return 12;
            case "Customer Type":
                return 3;
            case "Branch":
                return 1;
            case "Date":
                return 10;
            case "Time":
                return 11;
            case "Rating":
                return 16;
            default:
                System.err.println("Invalid feature.");
                return -1;
        }
    }

    private static JFreeChart createBarChart(DefaultCategoryDataset dataset, String feature) {
        return ChartFactory.createBarChart(
                "Sales Data - " + feature,
                feature,
                "Sales",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    private static JFreeChart createLineChart(DefaultCategoryDataset dataset, String feature) {
        return ChartFactory.createLineChart(
                "Sales Data - " + feature,
                feature,
                "Sales",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }

    private static JFreeChart createPieChart(DefaultPieDataset dataset, String feature) {
        return ChartFactory.createPieChart(
                "Sales Data - " + feature,
                dataset,
                true,
                true,
                false
        );
    }
}

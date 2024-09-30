package runapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileProces {

    public void processFiles(String csvFile, String fastqFile, String outputFile, int maxMismatches) {
        // Load barcode mapping from CSV file
        Map<String, String> barcodeToSampleMap = loadBarcodeMapping(csvFile);
        // Process the FASTQ file and match barcodes
        processFastqFile(fastqFile, barcodeToSampleMap, outputFile, maxMismatches);
    }

    private Map<String, String> loadBarcodeMapping(String csvFile) {
        Map<String, String> mapping = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    mapping.put(parts[0].trim(), parts[1].trim()); // Trim to avoid leading/trailing spaces
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return mapping;
    }

    private void processFastqFile(String fastqFile, Map<String, String> barcodeToSampleMap, String outputFile, int maxMismatches) {
        try (BufferedReader br = new BufferedReader(new FileReader(fastqFile));
             FileWriter writer = new FileWriter(outputFile)) {

            // Write header for output file
            writer.write("ReadID,Sample,QualityMetric\n");

            String line;
            String readId = "";
            String sequence = "";
            String quality = "";

            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                lineCount++;
                if (lineCount % 4 == 1) {
                    readId = line.substring(1).trim(); // Remove '@' and trim
                } else if (lineCount % 4 == 2) {
                    sequence = line.trim(); // Sequence line
                } else if (lineCount % 4 == 0) {
                    quality = line.trim(); // Quality line

                    // Match the read to a barcode and calculate quality metric
                    String sample = findBestMatch(sequence, barcodeToSampleMap, maxMismatches);
                    double qualityMetric = calculateQualityMetric(sequence, sample, barcodeToSampleMap, maxMismatches);

                    writer.write(String.format("%s,%s,%.2f\n", readId, sample, qualityMetric));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading FASTQ file: " + e.getMessage());
        }
    }

    private String findBestMatch(String read, Map<String, String> barcodeToSampleMap, int maxMismatches) {
        String bestMatch = "Unidentified"; // Default if no match found
        int bestMismatchCount = Integer.MAX_VALUE;

        for (Map.Entry<String, String> entry : barcodeToSampleMap.entrySet()) {
            String barcode = entry.getKey();
            int mismatchCount = countMismatches(read, barcode);

            if (mismatchCount <= maxMismatches && mismatchCount < bestMismatchCount) {
                bestMismatchCount = mismatchCount;
                bestMatch = entry.getValue(); // Sample associated with the barcode
            }
        }

        return bestMatch;
    }

    private double calculateQualityMetric(String read, String sample, Map<String, String> barcodeToSampleMap, int maxMismatches) {
        if (sample.equals("Unidentified")) {
            return 0.0; // No confidence if unidentified
        }

        int totalBarcodes = barcodeToSampleMap.size();
        int matchedBarcodes = 0;

        for (String barcode : barcodeToSampleMap.keySet()) {
            if (countMismatches(read, barcode) <= maxMismatches) {
                matchedBarcodes++;
            }
        }

        // Calculate a simple quality score based on the number of matching barcodes
        return (double) matchedBarcodes / totalBarcodes; // Normalized confidence score
    }

    private int countMismatches(String read, String barcode) {
        int mismatches = 0;
        int minLength = Math.min(read.length(), barcode.length());

        for (int i = 0; i < minLength; i++) {
            if (read.charAt(i) != barcode.charAt(i)) {
                mismatches++;
            }
        }

        // Add the difference in lengths as mismatches
        mismatches += Math.abs(read.length() - barcode.length());

        return mismatches;
    }
}

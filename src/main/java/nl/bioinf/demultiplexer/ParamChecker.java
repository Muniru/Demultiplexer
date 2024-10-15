package nl.bioinf.demultiplexer;

import java.io.File;

public class ParamChecker {
    private final File csvFile;
    private final File fastqFile;
    private final String outputFile;
    private int maxMismatches;
    private final int threads;

    public ParamChecker(File csvFile, File fastqFile, String outputFile, int maxMismatches, int threads) {
        if (!isValidCsvFile(csvFile)) {
            throw new IllegalArgumentException("Invalid CSV file. The file must have a .csv extension.");
        }
        if (!isValidFastqFile(fastqFile)) {
            throw new IllegalArgumentException("Invalid FASTQ file. The file must have a .fastq extension.");
        }

        this.csvFile = csvFile;
        this.fastqFile = fastqFile;
        this.outputFile = outputFile;
        this.maxMismatches = maxMismatches;
        this.threads = threads > 0 ? threads : Runtime.getRuntime().availableProcessors();
    }

    private boolean isValidCsvFile(File file) {
        return file != null && file.isFile() && file.getName().toLowerCase().endsWith(".csv");
    }

    private boolean isValidFastqFile(File file) {
        return file != null && file.isFile() && file.getName().toLowerCase().endsWith(".fastq");
    }

    public void checkFiles() {
        checkCsvFile();
        checkFastqFile();
        // Implement further processing for the output file if needed
    }

    private void checkCsvFile() {
        System.out.println("Processing CSV file: " + csvFile.getAbsolutePath());
        // Add specific CSV processing logic here
    }

    private void checkFastqFile() {
        System.out.println("Processing FASTQ file: " + fastqFile.getAbsolutePath());
        // Add specific FASTQ processing logic here
    }

    public void setMaxMismatches(int maxMismatches) {
        this.maxMismatches = maxMismatches;
    }

    public String getCsvFile() {
        return csvFile.toString();
    }

    public int getThreads() {
        return threads;
    }
    public String getFastqFile() {
        return fastqFile.toString();
    }
    public String getOutputFile() {
        return outputFile;
    }
}


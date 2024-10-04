package nl.bioinf.demultiplexer;
import java.io.File;

public class ParamChecker {
    private File csvFile;
    private File fastqFile;
    private File outputFile;
    private int maxMismatches;
    private int threads;

    public ParamChecker(File csvFile, File fastqFile, File outputFile, int maxMismatches, int threads) {
        this.csvFile = csvFile;
        this.fastqFile = fastqFile;
        this.outputFile = outputFile;
        this.maxMismatches = maxMismatches;
        this.threads = (threads==0) ? Runtime.getRuntime().availableProcessors() : threads;
    }

    public void processFiles() {
        processCsvFile(csvFile);
        processFastqFile(fastqFile);
        // Call output method if needed
    }

    public void processCsvFile(File csvFile) {
        System.out.println("Processing CSV file: " + csvFile.getAbsolutePath());
    }

    public void processFastqFile(File fastqFile) {
        System.out.println("Processing FASTQ file: " + fastqFile.getAbsolutePath());
    }

    public void outputFile(File outputFile) {
        // Implementation here
    }

    public void setMaxMismatches(int maxMismatches) {
        this.maxMismatches = maxMismatches;
    }
}
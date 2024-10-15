package nl.bioinf.demultiplexer;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Command(name = "minionProcessor", mixinStandardHelpOptions = true, version = "1.0",
        description = "Processes MinION sequence files and maps reads to samples based on barcodes.")
public class StartApp implements Runnable {

    @Parameters(index = "0", arity = "0..1", description = "The path to the CSV file mapping barcodes to samples.")
    private File csvFile = new File("/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/indices.csv");

    @Parameters(index = "1", arity = "0..1", description = "The output file path.")
    private String outputFile = "/homes/sschoonen/Java_eindopdracht/Demultiplexer/src/test/output/"; // Default output file path

    @Parameters(index = "2", arity = "0..1", description = "The fastq file path.")
    private File fastqFile = new File("/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/nanopore_run_001_small.fastq");

    @Parameters(index = "3", arity = "0..1", description = "The max mismatch.")
    private int maxMismatches = 1;

    @Option(names = {"-t", "--threads"}, description = "Max treads used")
    private int threads;
    //option kwaliteit van scores

    private int batchSize = 1000;

    private ThreadManager threadManager;

    //option indices kan meegeven
    @Override
    public void run() {
        // Call processFiles on the instance of MyFileReader
        ParamChecker paramChecker = new ParamChecker(csvFile, fastqFile, outputFile, maxMismatches, threads);
        // fileReader.processFiles(csvFile, fastqFile, outputFile, maxMismatches);
        CSVReader.read(paramChecker.getCsvFile());
        List<FastQRead> reads = new ArrayList<>();

        threadManager = new ThreadManager(paramChecker.getThreads());
        try (BufferedReader br = new BufferedReader(new FileReader(fastqFile))) {

            String line;
            String readId = "";
            String sequence = "";
            String seperator = "";
            String quality = "";
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                lineCount++;
                if (lineCount % 4 == 1) {
                    readId = line.substring(1).trim(); // Remove '@' and trim
                } else if (lineCount % 4 == 2) {
                    sequence = line.trim(); // Sequence line
                } else if (lineCount % 4 == 3) {
                    seperator = line.trim(); // Separator line
                } else if (lineCount % 4 == 0) {
                    quality = line.trim(); // Quality line
                }
                reads.add(new FastQRead(readId.toCharArray(), sequence.toCharArray(), seperator.toCharArray(), quality.toCharArray()));
//                System.out.println(new FastQRead(readId.toCharArray(), sequence.toCharArray(), seperator.toCharArray(), quality.toCharArray()));

                if (reads.size() >= batchSize) {
                    threadManager.createThread(new ArrayList<>(reads),
                            paramChecker.getThreads(),
                            paramChecker.getOutputFile());
                    reads.clear();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading FASTQ file: " + e.getMessage());
        } finally {
            threadManager.createThread(new ArrayList<>(reads),
                    paramChecker.getThreads(),
                    paramChecker.getOutputFile());
            System.out.println(reads.size());
            reads.clear();
        }

        // Concat all files
    }
}
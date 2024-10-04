package nl.bioinf.demultiplexer;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;


@Command(name = "minionProcessor", mixinStandardHelpOptions = true, version = "1.0",
        description = "Processes MinION sequence files and maps reads to samples based on barcodes.")
public class StartApp implements Runnable {

//    @Parameters(index = "0", arity = "0..1", description = "The path to the HDF5 file.")
//    private File hdf5File = "/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/default.h5"; // Default HDF5 file path

    @Parameters(index = "1", arity = "0..1", description = "The path to the CSV file mapping barcodes to samples.")
    private File csvFile = new File("/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/default.csv");

    @Parameters(index = "2", arity = "0..1", description = "The output file path.")
    private File outputFile = new File("/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/output.txt"); // Default output file path

    @Parameters(index = "3", arity = "0..1", description = "The fastq file path.")
    private File fastqFile = new File("/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/default.fastq");

    @Parameters(index = "4", arity = "0..1", description = "The max mismatch.")
    private int maxMismatches = 1;

    @Option(names = {"-t", "--threads"}, description = "Max treads used")
    private int threads;

    @Override
    public void run() {
        FileProcess fileReader = new FileProcess();
        // Call processFiles on the instance of MyFileReader
        ParamChecker paramChecker = new ParamChecker(csvFile, fastqFile, outputFile, maxMismatches, threads);
        // fileReader.processFiles(csvFile, fastqFile, outputFile, maxMismatches);
    }
}

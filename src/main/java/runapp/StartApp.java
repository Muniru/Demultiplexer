package runapp;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;


@Command(name = "minionProcessor", mixinStandardHelpOptions = true, version = "1.0",
        description = "Processes MinION sequence files and maps reads to samples based on barcodes.")
public class App implements Runnable {

    @Parameters(index = "0", arity = "0..1", description = "The path to the HDF5 file.")
    private String hdf5File = "/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/default.h5"; // Default HDF5 file path

    @Parameters(index = "1", arity = "0..1", description = "The path to the CSV file mapping barcodes to samples.")
    private String csvFile = "/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/default.csv"; // Default CSV file path

    @Parameters(index = "2", arity = "0..1", description = "The output file path.")
    private String outputFile = "/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/output.txt"; // Default output file path

    @Parameters(index = "3", arity = "0..1", description = "The fastq file path.")
    private String fastqFile = "/homes/sschoonen/Java_eindopdracht/Demultiplexer/samples/default.fastq";

    @Parameters(index = "4", arity = "0..1", description = "The max mismatch.")
    private int maxMismatches = 1;

    @Override
    public void run() {
        FileProces fileReader = new FileProces();
        // Call processFiles on the instance of MyFileReader
        fileReader.processFiles(csvFile, fastqFile, outputFile, maxMismatches);
    }
}

package nl.bioinf.demultiplexer;

public class App {
    public void Start(String[] args){

        // Validate arguments
        ParamChecker.check(args);

        // Reading files
        MyFileReader fileReader = new MyFileReader();
        fileReader.processFiles("","","");

        // Remove nanopore barcodes if still present from both sides
        BarcodeRemover barcodeRemover = new BarcodeRemover();
        barcodeRemover.removeNanoporeBarcodes(null);

        // Het identificeren van illumina barcodes en die weer afschrijven naar maps
        SeqeunceComparator dataManipulator = new SeqeunceComparator();
        dataManipulator.parseIllumina(null);

        // output
        FileWriter output = new FileWriter();
        output.write();

    }
}

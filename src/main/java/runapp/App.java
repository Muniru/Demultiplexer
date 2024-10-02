package runapp;

public class App {
    public void Start(String[] args){

        // Validate arguments
        ParamChecker.check(args);

        // Reading files
        MyFileReader fileReader = new MyFileReader();
        fileReader.processFiles("","","");

        // Remove nanopore barcodes if still present from both sides
        DataManipulator dataManipulator = new DataManipulator();
        dataManipulator.removeNanoporeBarcodes(DataManager.getInstance());

        // Het identificeren van illumina barcodes en die weer afschrijven naar maps
        dataManipulator.parseIllumina(DataManager.getInstance());

        // output
        FileWriter output = new FileWriter();
        output.write();

    }
}

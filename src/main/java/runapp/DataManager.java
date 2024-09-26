package runapp;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {

    private static DataManager instance;
    private Map<String, List> sampleReads = new HashMap<>();

    private DataManager() {
    }

    // Returning only instance
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void ProcessRead(String barcode, String readData){
        throw new UnsupportedOperationException("Not supported yet.");
//        Code
//        if barcode not in sampleReads:
//            make new entry in sample sampleReads with barcode
//            sample barcode = new list
//
//        data = new ReadData
//        add data to sampleReads[barcode] = data
    }
}

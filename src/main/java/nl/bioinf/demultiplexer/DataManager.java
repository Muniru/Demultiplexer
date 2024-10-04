package nl.bioinf.demultiplexer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.crypto.Data;
import java.util.*;

public class DataManager {

    private static DataManager instance;
    private List<ReadData> sampleReads = new LinkedList<>(); // Data Type nog te veranderen.

    private DataManager() {
    }

    // Returning only instance
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void processRead(String barcode, String readData){
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

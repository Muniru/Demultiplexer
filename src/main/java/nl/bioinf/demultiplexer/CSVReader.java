package nl.bioinf.demultiplexer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
    public static void main(String[] args) {

        String relativePath = "../Data/indices.csv";
        String line;
        Map<String, SampleIndexes> sampleMap = new HashMap<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(relativePath))) {
            br.readLine();
            while((line = br.readLine()) != null){
                String[] sampleLine = line.split(";");
                SampleIndexes mySample = new SampleIndexes(
                        sampleLine[0],
                        sampleLine[1],
                        sampleLine[2],
                        sampleLine[3],
                        sampleLine[4],
                        sampleLine[5],
                        sampleLine[6],
                        sampleLine[7],
                        sampleLine[8]
                );
                sampleMap.put(sampleLine[0], mySample);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        SampleDataSingleton.getInstance().setSampleIndexes(sampleMap);
        System.out.println(SampleDataSingleton.getInstance().getSampleIndexes().values());
    }

    public static void read(String relativePath){
        String line = null;
        Map<String, SampleIndexes> sampleMap = new HashMap<>();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(relativePath))) {
            br.readLine();
            while((line = br.readLine()) != null){
                String[] sampleLine = line.split(";");
                SampleIndexes mySample = new SampleIndexes(
                        sampleLine[0],
                        sampleLine[1],
                        sampleLine[2],
                        sampleLine[3],
                        sampleLine[4],
                        sampleLine[5],
                        sampleLine[6],
                        sampleLine[7],
                        sampleLine[8]
                );
                sampleMap.put(sampleLine[0], mySample);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        SampleDataSingleton.getInstance().setSampleIndexes(sampleMap);
        System.out.println(SampleDataSingleton.getInstance().getSampleIndexes().values());

    }

}


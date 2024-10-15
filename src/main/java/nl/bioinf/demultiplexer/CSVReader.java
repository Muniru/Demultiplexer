package nl.bioinf.demultiplexer;

import java.io.BufferedReader;
import java.io.FileReader;

public class CSVReader {
    public static void main(String[] args) {

        String relativePath = "../Data/indices.csv";
        String line;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(relativePath))) {
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
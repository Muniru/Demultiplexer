package nl.bioinf.demultiplexer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FastqWriter {

    public static void write(Map<String, List<FastQRead>> data, String outputDir, String subDirName) {
        // Maak directory op basis
        String dir = createDir(outputDir, subDirName);

        // Maak een file voor elke key en in elke key de data ervan
        for (Map.Entry<String, List<FastQRead>> entry : data.entrySet()) {
            String filePath = Paths.get(dir, entry.getKey()).toString();
            writeFastq(filePath, entry.getValue());
        }
    }

    private static void writeFastq(String filePath, List<FastQRead> reads) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (FastQRead read : reads) {
                // Afschrijven van de reads
                writer.write(read.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String createDir (String outputDir, String dirName ) {
        Path dirPath = Paths.get(outputDir, dirName);
        try {
            Files.createDirectories(dirPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create directory: " + dirPath, e);
        }
        return dirPath.toString();
    }

}

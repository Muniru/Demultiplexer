package nl.bioinf.demultiplexer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FastqWriterTest {

    public static Map<String, List<FastQRead>> reads(){
        Map<String, List<FastQRead>> reads = new HashMap<>();
        reads.put("0", FastQReadTest.fastQReads());
        reads.put("1", FastQReadTest.fastQReads());
        reads.put("2", FastQReadTest.fastQReads());
        reads.put("3", FastQReadTest.fastQReads());
        return reads;
    }

    @Test
    void testWrite() {
        FastqWriter fastqWriter = new FastqWriter();
        String outputDir = Paths.get("src","test","output", "tmp").toAbsolutePath().toString();
        fastqWriter.write(reads(),outputDir, "0");
        fastqWriter.write(reads(),outputDir, "1");
        fastqWriter.write(reads(),outputDir, "3");
        fastqWriter.write(reads(),outputDir, "4");
    }

    @Test
    void concat() {
        testWrite();
        String outputDir = Paths.get("src","test","output", "tmp").toAbsolutePath().toString();
        String finalOutputDir = Paths.get("src","test","output").toAbsolutePath().toString();
        String[] filenames = new String[]{ "0", "1", "2", "3" };
        FastqWriter.concat(outputDir,filenames,finalOutputDir);
    }

    @Test
    void removeFilesAndDirectories() {
        concat();
        String outputDir = Paths.get("src","test","output", "tmp").toAbsolutePath().toString();
        try {
            FastqWriter.removeFilesAndDirectories(outputDir);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
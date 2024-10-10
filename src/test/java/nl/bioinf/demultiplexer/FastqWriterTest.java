package nl.bioinf.demultiplexer;

import org.junit.jupiter.api.Test;
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
        String outputDir = Paths.get("src","test","output").toAbsolutePath().toString();
        fastqWriter.write(reads(),outputDir, "1");
        System.out.println(outputDir);
    }
}
package nl.bioinf.demultiplexer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FastqWriterTest {

    private static Map<String, List<FastQRead>> reads(){
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
        //fastqWriter.write(reads(),"");
    }
}
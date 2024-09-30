package nl.bioinf.demultiplexer;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FastqWriterTest {

    @Test
    void write() {
        FastqWriter fastqWriter = new FastqWriter();
        fastqWriter.write(new File(""));
    }
}
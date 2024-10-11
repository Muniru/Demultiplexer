package nl.bioinf.demultiplexer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreadManagerTest {

    @Test
    void createThread() {
        ThreadManager manager = new ThreadManager(0);
        List<FastQRead> data = FastQReadTest.fastQReads();
        String outputDir = Paths.get("src", "test", "output", "tmp").toAbsolutePath().toString();
        manager.createThread(new ArrayList<>(data), 0, outputDir);
        manager.createThread(new ArrayList<>(data), 0, outputDir);
        manager.createThread(new ArrayList<>(data), 0, outputDir);
        manager.createThread(new ArrayList<>(data), 0, outputDir);
        manager.createThread(new ArrayList<>(data), 0, outputDir);
        try {
            FastqWriter.removeFilesAndDirectories(outputDir);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}